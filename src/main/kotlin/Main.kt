import org.w3c.dom.HTMLElement
import utils.replace
import views.app.AppPage
import views.app.AppPresenter
import kotlin.browser.document

fun main() {
    (document.getElementById("app") as? HTMLElement)?.replace(AppPage(AppPresenter()))
}
