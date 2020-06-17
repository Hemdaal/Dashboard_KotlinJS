package views.app

import constants.PageType
import models.Project
import models.User
import mvp.Page
import utils.*
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
import kotlinx.html.*
import kotlinx.html.dom.*
import org.w3c.dom.HTMLElement
import kotlin.dom.clear

class AppPage(appPresenter: AppPresenter) : Page<AppPageContract, AppPresenter>(appPresenter), AppPageContract {

    private val toolBar by lazy {
        document.getElementById("toobar") as HTMLElement
    }

    private val container by lazy {
        document.getElementById("container") as HTMLElement
    }

    override fun onCreate(content: HTMLElement) {
        content.append.div {
            nav("navbar navbar-expand-sm bg-primary navbar-dark sticky-top") {
                id = "toolbar"
                a(classes = "navbar-brand", href = "#") {
                    +"Hemdaal"
                }
            }
            div("container p-3 my-3 bg-white text-black") {
                id = "container"

            }
        }
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
    override fun showLoading() {
        container.replace().div("spinner-grow text-primary")
    }

    override fun showLoginPage() {
        //TODO
    }

    override fun showProjects(user: User, projects: List<Project>) {
        //TODO
    }

    override fun showCreateProject(user: User) {
        //TODO
    }

    override fun setUserInNavBar(user: User) {

    }
}