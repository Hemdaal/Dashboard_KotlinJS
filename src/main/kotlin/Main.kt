import views.dashboard.DashboardPage
import views.dashboard.DashboardPresenter

fun main(args: Array<String>) {
    val dashboardPresenter = DashboardPresenter()
    val dashboardPage = DashboardPage(dashboardPresenter)
    dashboardPage.show()
}
