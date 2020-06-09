package views.dashboard

import models.Project
import views.login.LoginPage
import views.login.LoginPresenter

class DashboardPage(private val dashboardPresenter: DashboardContract.Presenter) : DashboardContract.View {

    fun show() {
        dashboardPresenter.onAttach(this)
        dashboardPresenter.checkState()
    }

    override fun showLoginPage() {
        LoginPage(LoginPresenter()) {
            dashboardPresenter.checkState()
        }.show()
    }

    override fun showProjects(projects: List<Project>) {
        TODO("Not yet implemented")
    }

    override fun showCreateProject() {
        TODO("Not yet implemented")
    }
}