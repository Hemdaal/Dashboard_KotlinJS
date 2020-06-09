package views.app

import models.Project
import views.login.LoginPage
import views.login.LoginPresenter
import views.signup.SignupPage
import views.signup.SignupPresenter

class AppPage(private val appPresenter: AppContract.Presenter) : AppContract.View {

    fun show() {
        appPresenter.onAttach(this)
        appPresenter.checkState()
    }

    override fun showLoginPage() {
        LoginPage(
            loginPresenter = LoginPresenter(),
            loginStateCallback = {
                appPresenter.checkState()
            },
            signupCallback = {
                showSignupPage()
            }
        ).show()
    }

    private fun showSignupPage() {
        SignupPage(
            signupPresenter = SignupPresenter(),
            loginStateCallback = {
                appPresenter.checkState()
            },
            loginCallback = {
                showLoginPage()
            }
        ).show()
    }

    override fun showProjects(projects: List<Project>) {
        TODO("Not yet implemented")
    }

    override fun showCreateProject() {
        TODO("Not yet implemented")
    }
}