package drigor

val r = ("curl '(?<URL>[^']+)'" +
        "( -X (?<METHOD>GET|POST|PUT|HEAD))?" +
        "(?<HEADERS>( -H '[^']+')+)" +
        "( --data-binary '(?<BODY>[^']+)')?" +
        "(.+)").toRegex()

fun parse(curl: String): CurlModel? {
    return r.matchEntire(curl)?.let { model(it.groups) }
}

fun model(groups: MatchGroupCollection): CurlModel {
    return CurlModel(
            url = groups["URL"]!!.value,
            method = groups["METHOD"]?.value ?: "GET",
            headers = (groups["HEADERS"]?.value ?: "")
                    .split(" -H ")
                    .drop(1)
                    .map { it.removeSurrounding("'") }
                    .associateBy({ it.substringBefore(": ") }, { it.substringAfter(": ") }),
            body = groups["BODY"]?.value)
}

fun toIdeaRequest(model: CurlModel): String {
    return """
        |${model.method} ${model.url}
        |${model.headers.entries.joinToString("\n") { "${it.key}: ${it.value}" }}
        |${model.body}
        |###
    """.trimMargin()
}

data class CurlModel(
        val url: String,
        val method: String,
        val headers: Map<String, String>,
        val body: String?)
