package views.dashboard

import views.login.LoginPage
import views.login.LoginPresenter

class DashboardPage(private val dashboardPresenter: DashboardContract.Presenter) : DashboardContract.View {

    fun show() {
        dashboardPresenter.onAttach(this)
        dashboardPresenter.checkState()
    }

    override fun showLoginPage() {
        LoginPage(LoginPresenter()).show()
    }
}