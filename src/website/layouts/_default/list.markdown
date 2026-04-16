{{ define "main" }}
# {{ .Title }}

{{ .RawContent }}
{{ range .Pages }}
- [{{ .Title }}]({{ .RelPermalink }})
{{ end }}
{{ end }}
