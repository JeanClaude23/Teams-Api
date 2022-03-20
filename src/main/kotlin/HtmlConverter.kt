import kotlinx.html.div
import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import java.io.File

fun List<Teams>.asHtml(): String = createHTMLDocument().div {
    h1 { +"Teams :" }
    forEach {
        h2 { +it.name }
        h3 {+ "Team Name is:" }
        br
        h3 {+ "Instructions to select team u know:" }
        br
        div { +it.strInstructions }
        br
    }
}.serialize()

fun String.storeAsFile() {
    File("index.html").writeText(this)
}