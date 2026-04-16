{{ with resources.GetRemote .Params.remote }}
```kotlin
{{ .Content }}
```
{{ else }}
{{ errorf "Remote not found: %s" .Params.remote }}
{{ end }}
