package dashboard

import login.LoginPage
import login.LoginPresenter
import signup.SignupPage

class DashboardPage(private val dashboardPresenter: DashboardContract.Presenter) : DashboardContract.View {

    fun show() {
        dashboardPresenter.onAttach(this)
        dashboardPresenter.checkState()
    }

    override fun showLoginPage() {
        LoginPage(LoginPresenter()).show()
    }
}