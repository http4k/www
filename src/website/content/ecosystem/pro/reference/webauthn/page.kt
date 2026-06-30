package content.ecosystem.pro.reference.webauthn

val page = """
<!doctype html>
<html><head><meta charset="utf-8"><title>http4k passkeys</title></head>
<body style="font-family:sans-serif;max-width:30rem;margin:4rem auto">
  <h1>http4k passkeys</h1>
  <input id="username" placeholder="username" value="alice">
  <button onclick="register()">Register a passkey</button>
  <button onclick="login()">Sign in with passkey</button>
  <p id="log"></p>
<script>
function log(m){document.getElementById('log').textContent=m}
function b64urlToBuf(s){s=s.replace(/-/g,'+').replace(/_/g,'/');while(s.length%4)s+='=';const b=atob(s),a=new Uint8Array(b.length);for(let i=0;i<b.length;i++)a[i]=b.charCodeAt(i);return a.buffer}
function bufToB64url(buf){const a=new Uint8Array(buf);let s='';for(let i=0;i<a.length;i++)s+=String.fromCharCode(a[i]);return btoa(s).replace(/\+/g,'-').replace(/\//g,'_').replace(/=/g,'')}
function descriptors(a){return (a||[]).map(d=>({type:'public-key',id:b64urlToBuf(d.id),transports:d.transports||[]}))}
async function register(){try{
  const name=document.getElementById('username').value;
  // 1. ask the server for registration options (challenge, rp, user, params)
  const o=await fetch('/passkeys/register/options?username='+encodeURIComponent(name),{method:'POST'}).then(r=>r.json());
  // 2. let the OS authenticator mint a real credential
  const c=await navigator.credentials.create({publicKey:{
    challenge:b64urlToBuf(o.challenge),
    rp:{id:o.rp.id,name:o.rp.name},
    user:{id:b64urlToBuf(o.user.handle),name:o.user.name,displayName:o.user.displayName},
    pubKeyCredParams:o.pubKeyCredParams,
    authenticatorSelection:o.authenticatorSelection,attestation:o.attestation,timeout:o.timeout}});
  // 3. send it back for verification and storage
  const r=await fetch('/passkeys/register',{method:'POST',headers:{'content-type':'application/json'},body:JSON.stringify({
    credentialId:bufToB64url(c.rawId),
    clientDataJSON:bufToB64url(c.response.clientDataJSON),
    attestationObject:bufToB64url(c.response.attestationObject),
    transports:c.response.getTransports?c.response.getTransports():[],
    clientExtensionResults:c.getClientExtensionResults()})});
  log(r.ok?'passkey registered and verified!':'failed: '+r.status+' '+await r.text());
}catch(e){log('error: '+e)}}
async function login(){try{
  // 1. ask for authentication options (challenge); allowCredentials is empty for a discoverable passkey
  const o=await fetch('/passkeys/authenticate/options',{method:'POST'}).then(r=>r.json());
  // 2. let the OS authenticator sign the challenge with the chosen passkey
  const c=await navigator.credentials.get({publicKey:{
    challenge:b64urlToBuf(o.challenge),rpId:o.rp.id,
    allowCredentials:descriptors(o.allowCredentials),
    userVerification:o.userVerification,timeout:o.timeout}});
  // 3. send the signed assertion back for verification - the server establishes a session
  const r=await fetch('/passkeys/authenticate',{method:'POST',headers:{'content-type':'application/json'},body:JSON.stringify({
    credentialId:bufToB64url(c.rawId),
    clientDataJSON:bufToB64url(c.response.clientDataJSON),
    authenticatorData:bufToB64url(c.response.authenticatorData),
    signature:bufToB64url(c.response.signature)})});
  log(r.ok?'signed in as '+(await r.json()).userHandle:'sign-in failed: '+r.status+' '+await r.text());
}catch(e){log('error: '+e)}}
</script>
</body></html>
""".trimIndent()
