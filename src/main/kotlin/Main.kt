import views.app.AppPage
import views.app.AppPresenter

fun main(args: Array<String>) {
    val dashboardPresenter = AppPresenter()
    val dashboardPage = AppPage(dashboardPresenter)
    dashboardPage.show()
}
