import views.app.AppPage
import views.app.AppPresenter

fun main() {
    val appPresenter = AppPresenter()
    val appPage = AppPage(appPresenter)
    appPage.show()
}
