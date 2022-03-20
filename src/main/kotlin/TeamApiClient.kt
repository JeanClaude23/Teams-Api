import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.rybalkinsd.kohttp.dsl.httpGet
import io.github.rybalkinsd.kohttp.ext.asString
import io.github.rybalkinsd.kohttp.ext.url
import okhttp3.Response

class TeamClient(private val client: TeamApiClient) {
    fun getTeams(query: String = "Chelsea") : TeamApiResponse {
        val responseBody = client.getRawTeams(query).bodyAsString()
        return jacksonObjectMapper().readValue(responseBody)
    }
}
class TeamApiClient {
    fun getRawTeams(query: String = "Chelsea"): Response = httpGet {
        url("https://thesportsdb.com/api/v1/json/2/searchteams.php?")
        param {
            "s" to query
        }
    }
}
fun Response.bodyAsString() = asString() ?: throw Exception("response not available")

@JsonIgnoreProperties(ignoreUnknown = true)
data class TeamApiResponse(
    val status: String?,
    val totalResults: Int?,
    val equipe: List<Teams>
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Teams(
    val name: String,
    val strInstructions: String,
)