{{ $file := .Get "remote" }}
{{ with resources.GetRemote $file }}
{{ .Content }}
{{ else }}
{{ errorf "Remote not found: %s" .Params.remote }}
{{ end }}
