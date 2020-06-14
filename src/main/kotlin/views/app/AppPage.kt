package views.app

import constants.PageType
import models.Project
import models.User
import org.w3c.dom.HTMLDivElement
import utils.*
import views.appBar.AppBarPresenter
import views.appBar.AppBarWidget
import views.chooseProjects.ChooseProjectPage
import views.chooseProjects.ChooseProjectPresenter
import views.createProject.CreateProjectPage
import views.createProject.CreateProjectPresenter
import views.login.LoginPage
import views.login.LoginPresenter
import views.projectDetail.ProjectDetailPage
import views.projectDetail.ProjectDetailPresenter
import views.signup.SignupPage
import views.signup.SignupPresenter
import kotlin.browser.document
import kotlin.dom.clear

class AppPage(private val appPresenter: AppContract.Presenter) : AppContract.View {

    private val toolBar = document.createNav("Hemdaal")
    private val content = document.createPageContainer()

    private val appBarWidget = AppBarWidget(toolBar, AppBarPresenter()) {
        appPresenter.checkState()
    }

    fun show() {
        val appPage = document.getElementById("app") as HTMLDivElement
        appPage.appendChild(toolBar)
        appPage.appendChild(document.createLineBreak())
        appPage.appendChild(content)

        appBarWidget.show()
        appBarWidget.onPageChange(PageType.APP)

        appPresenter.onAttach(this)
        appPresenter.checkState()
    }

    override fun showLoading() {
        content.clear()
        content.append(document.createSpinner())
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

    override fun showProjects(user: User, projects: List<Project>) {
        appBarWidget.onPageChange(PageType.PROJECT)
        ChooseProjectPage(
            content = content,
            chooseProjectPresenter = ChooseProjectPresenter(projects),
            onAddProjectClick = {
                showCreateProjectPage(
                    user = user,
                    cancelCallback = {
                        showProjects(user, projects)
                    }
                )
            },
            onProjectClick = {
                showProjectDetail(it)
            }
        ).show()
    }

    override fun showCreateProject(user: User) {
        showCreateProjectPage(user)
    }

    override fun setUserInNavBar(user: User) {
        appBarWidget.onSignIn(user)
    }

    private fun showCreateProjectPage(user: User, cancelCallback: (() -> Unit)? = null) {
        appBarWidget.onPageChange(PageType.CREATE_PROJECT)
        CreateProjectPage(
            content = content,
            createProjectPresenter = CreateProjectPresenter(user),
            projectCreatedCallback = { project ->
                showProjectDetail(project)
            },
            cancelledCallback = cancelCallback
        ).show()
    }

    private fun showProjectDetail(project: Project) {
        ProjectDetailPage(
            content = content,
            projectDetailPresenter = ProjectDetailPresenter(project)
        ).show()
    }
}