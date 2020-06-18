package views.app

import constants.PageType
import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.id
import kotlinx.html.nav
import models.Project
import models.User
import mvp.Page
import org.w3c.dom.HTMLElement
import utils.replace
import views.appBar.AppBarPage
import views.appBar.AppBarPresenter
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

class AppPage(presenter: AppPresenter) : Page<AppPageContract, AppPresenter>(presenter), AppPageContract {

    private lateinit var appParPage: AppBarPage

    private val toolBar by lazy {
        document.getElementById("toolbar") as HTMLElement
    }

    private val container by lazy {
        document.getElementById("container") as HTMLElement
    }

    override fun onCreate(content: HTMLElement) {
        content.append.div {
            div {
                id = "toolbar"
            }
            div("container p-3 my-3 bg-white text-black") {
                id = "container"

            }
        }
        appParPage = AppBarPage(AppBarPresenter()) {
            getPresenter().checkState()
        }
        toolBar.replace(appParPage)
    }

    override fun showLoading() {
        container.replace().div("spinner-grow text-primary")
    }

    override fun showLoginPage() {
        appParPage.onPageChange(PageType.LOGIN)
        container.replace(LoginPage(
            presenter = LoginPresenter(),
            loginStateCallback = {
                getPresenter().checkState()
            },
            signupCallback = {
                showSignupPage()
            }
        ))
    }


    override fun showProjects(user: User, projects: List<Project>) {
        appParPage.onPageChange(PageType.PROJECT)
        container.replace(ChooseProjectPage(
            presenter = ChooseProjectPresenter(projects),
            onProjectClick = {
                showProjectDetailPage(it)
            },
            onAddProjectClick = {
                showCreateProject(user)
            }
        ))
    }

    private fun showProjectDetailPage(project: Project) {
        container.replace(
            ProjectDetailPage(
                presenter = ProjectDetailPresenter(project)
            )
        )
    }

    override fun showCreateProject(user: User) {
        container.replace(CreateProjectPage(
            presenter = CreateProjectPresenter(user),
            projectCreatedCallback = {

            },
            cancelledCallback = {
                getPresenter().checkState()
            }
        ))
    }

    override fun setUserInNavBar(user: User) {
        appParPage.onSignIn(user)
    }

    private fun showSignupPage() {
        appParPage.onPageChange(PageType.SIGNUP)
        container.replace(SignupPage(
            presenter = SignupPresenter(),
            loginStateCallback = {
                getPresenter().checkState()
            },
            loginCallback = {
                showLoginPage()
            }
        ))
    }

    override fun getContract() = this
}