package views.projectDetail

import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.i
import kotlinx.html.id
import kotlinx.html.js.div
import kotlinx.html.js.onClickFunction
import models.Project
import models.SoftwareComponent
import mvp.Page
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import utils.replace
import views.addSoftware.AddSoftwarePage
import views.addSoftware.AddSoftwarePresenter
import kotlin.browser.document
import kotlin.dom.clear

class ProjectDetailPage(
    presenter: ProjectDetailPresenter
) : Page<ProjectDetailContract, ProjectDetailPresenter>(presenter), ProjectDetailContract {

    private val projectDetailDiv by lazy {
        document.getElementById("project_detail_div") as HTMLDivElement
    }

    override fun onCreate(content: HTMLElement) {
        content.append.div {
            id = "project_detail_div"
        }
    }

    override fun getContract() = this

    override fun showProjectDetails(project: Project, softwareComponents: List<SoftwareComponent>) {
        projectDetailDiv.clear()
        projectDetailDiv.append.div() {
            div("card") {
                a(href = "#", classes = "card-body d-flex justify-content-between align-items-center") {
                    +"Add Software Component"
                    i("fas fa-plus-square")
                }
                onClickFunction = {
                    showAddSoftwarePage(project)
                }
            }
        }
    }

    private fun showAddSoftwarePage(project: Project) {
        projectDetailDiv.replace(AddSoftwarePage(
            presenter = AddSoftwarePresenter(project),
            softwareCreatedCallback = {
                getPresenter().softwareAdded(it)
            },
            cancelCallback = {
                getPresenter().addSoftwareCanceled()
            }
        ))
    }
}