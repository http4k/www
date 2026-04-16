{{ $file := .Get "file" }}
{{ $filePath := printf "%s/%s" .Page.Path $file }}

{{ with readFile $filePath }}
```json
{{ . }}
```
{{ else }}
{{ errorf "File not found: %s" $filePath }}
{{ end }}
