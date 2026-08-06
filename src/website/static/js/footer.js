// Footer JavaScript functionality

// Copy helper: navigator.clipboard only exists in secure contexts (https /
// localhost). Fall back to execCommand for plain-http origins (e.g. LAN dev).
function copyText(text) {
    if (navigator.clipboard && navigator.clipboard.writeText) {
        return navigator.clipboard.writeText(text);
    }
    return new Promise((resolve, reject) => {
        try {
            const ta = document.createElement('textarea');
            ta.value = text;
            ta.style.position = 'fixed';
            ta.style.opacity = '0';
            document.body.appendChild(ta);
            ta.select();
            document.execCommand('copy');
            document.body.removeChild(ta);
            resolve();
        } catch (e) {
            reject(e);
        }
    });
}

// Inlined rather than pulled from the bootstrap-icons webfont
const ICON_CLIPBOARD = '<svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" aria-hidden="true" fill="currentColor" viewBox="0 0 16 16"><path d="M4 1.5H3a2 2 0 0 0-2 2V14a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V3.5a2 2 0 0 0-2-2h-1v1h1a1 1 0 0 1 1 1V14a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V3.5a1 1 0 0 1 1-1h1v-1z"/><path d="M9.5 1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5h3zm-3-1A1.5 1.5 0 0 0 5 1.5v1A1.5 1.5 0 0 0 6.5 4h3A1.5 1.5 0 0 0 11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3z"/></svg>';
const ICON_COPIED = '<svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" aria-hidden="true" fill="currentColor" viewBox="0 0 16 16"><path d="M6.5 0A1.5 1.5 0 0 0 5 1.5v1A1.5 1.5 0 0 0 6.5 4h3A1.5 1.5 0 0 0 11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3Zm3 1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5h3Z"/><path d="M4 1.5H3a2 2 0 0 0-2 2V14a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V3.5a2 2 0 0 0-2-2h-1v1A2.5 2.5 0 0 1 9.5 5h-3A2.5 2.5 0 0 1 4 2.5v-1Zm6.854 7.354-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 0 1 .708-.708L7.5 10.793l2.646-2.647a.5.5 0 0 1 .708.708Z"/></svg>';

function initCopyButtons() {
    document.querySelectorAll('.highlight').forEach(function (highlight) {
        const pre = highlight.querySelector('pre');
        if (!pre) return;

        const copyButton = document.createElement('i');
        copyButton.className = 'fs-2 copy-button';
        copyButton.setAttribute('role', 'button');
        copyButton.setAttribute('aria-label', 'Copy code');
        copyButton.innerHTML = ICON_CLIPBOARD;
        pre.prepend(copyButton);

        copyButton.addEventListener('click', function () {
            const code = copyButton.parentElement.querySelector('code');
            const codeContent = code ? code.textContent : '';

            copyText(codeContent)
                .then(() => {
                    // Report which code snippets get copied, by language and page.
                    if (typeof gtag === 'function') {
                        gtag('event', 'copy_code', {
                            code_language: (code && code.dataset.lang) || 'unknown',
                            page_path: window.location.pathname,
                        });
                    }

                    copyButton.innerHTML = ICON_COPIED;

                    setTimeout(() => {
                        copyButton.innerHTML = ICON_CLIPBOARD;
                    }, 250);
                })
                .catch(err => {
                    console.error('Could not copy text: ', err);
                });
        });
    });
}

// Run now if the DOM is already parsed (external script can execute after
// DOMContentLoaded has fired), else wait - mirrors jQuery's $(document).ready.
if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', initCopyButtons);
} else {
    initCopyButtons();
}

// HIDE THE PREAMBLE
document.querySelectorAll('span[style*="color:#66d9ef"]').forEach(span => {
        if (span.textContent === 'import' || span.textContent === 'package') {
            span.closest('span[style*="display:flex"]').style.display = 'none';
        }
    }
);
