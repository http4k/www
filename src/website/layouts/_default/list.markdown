{{ define "main" }}
# {{ .Title }}

{{ .RenderShortcodes }}
{{ range .Pages }}
- [{{ .Title }}]({{ .RelPermalink }})
{{ end }}
{{ end }}
