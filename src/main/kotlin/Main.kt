import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt

class Hello: CliktCommand() {
    val input by option(help="Make your choice").prompt("search team ")
    override fun run() {
        TeamClient(TeamApiClient())
            .getTeams(input)
            .equipe
            .asHtml()
            .storeAsFile()
    }
}

fun main(args: Array<String>) = Hello().main(args)