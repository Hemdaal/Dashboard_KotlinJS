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
import views.login.LoginPage
import views.login.LoginPresenter
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
                showLoginPage()
            }
        ))
    }

    override fun showProjects(user: User, projects: List<Project>) {
        //TODO
    }

    override fun showCreateProject(user: User) {
        //TODO
    }

    override fun setUserInNavBar(user: User) {
        appParPage.onSignIn(user)
    }

    /* fun show() {
         document.getElementById("app")?.apply {
             appendChild(toolBar)
             appendChild(document.createLineBreak())
             appendChild(content)
         }

         appBarWidget.show()
         appBarWidget.onPageChange(PageType.APP)

         getPresenter().checkState()
     }*/

    /*   override fun showLoading() {
           content.clear()
           content.append(document.createSpinner())
       }

       override fun showLoginPage() {
           appBarWidget.onPageChange(PageType.LOGIN)
           LoginPage(
               content = content,
               loginPresenter = LoginPresenter(),
               loginStateCallback = {
                   getPresenter().checkState()
               },
               signupCallback = {
                   showSignupPage()
               }
           )
       }

       private fun showSignupPage() {
           appBarWidget.onPageChange(PageType.SIGNUP)
           SignupPage(
               content = content,
               signupPresenter = SignupPresenter(),
               loginStateCallback = {
                   getPresenter().checkState()
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
   */
    override fun getContract() = this
}