import dashboard.DashboardPage
import dashboard.DashboardPresenter

fun main(args: Array<String>) {
    val dashboardPresenter = DashboardPresenter()
    val dashboardPage = DashboardPage(dashboardPresenter)
    dashboardPage.show()
}
