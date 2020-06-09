package views.app

import constants.PageType
import models.Project
import org.w3c.dom.HTMLDivElement
import utils.lineBreak
import views.appBar.AppBarPresenter
import views.appBar.AppBarWidget
import views.createProject.CreateProjectPage
import views.createProject.CreateProjectPresenter
import views.login.LoginPage
import views.login.LoginPresenter
import views.signup.SignupPage
import views.signup.SignupPresenter
import kotlin.browser.document

class AppPage(private val appPresenter: AppContract.Presenter) : AppContract.View {

    private val toolBar = (document.createElement("div") as HTMLDivElement).apply {
        id = "header"
        className = "header"
    }
    private val content = (document.createElement("div") as HTMLDivElement).apply {
        id = "content"
        className = "content"
    }

    private val appBarWidget = AppBarWidget(toolBar, AppBarPresenter())

    fun show() {
        val appPage = document.getElementById("app") as HTMLDivElement
        appPage.appendChild(toolBar)
        appPage.appendChild(lineBreak())
        appPage.appendChild(content)

        appBarWidget.show()
        appBarWidget.onPageChange(PageType.APP)

        appPresenter.onAttach(this)
        appPresenter.checkState()
    }

    override fun showLoginPage() {
        appBarWidget.onPageChange(PageType.LOGIN)
        LoginPage(
            content = content,
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
        appBarWidget.onPageChange(PageType.SIGNUP)
        SignupPage(
            content = content,
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

    }

    override fun showCreateProject() {
        CreateProjectPage(
            content = content,
            createProjectPresenter = CreateProjectPresenter()
        ).show()
    }
}