package content.news.toolbox

data class JsonRoot(val child: List<String>?, val num: Number?)

data class Base(val jsonRoot: JsonRoot?)
