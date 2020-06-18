package views.projectDetail

import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.i
import kotlinx.html.id
import kotlinx.html.js.div
import models.SoftwareComponent
import mvp.Page
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import kotlin.browser.document
import kotlin.dom.clear

class ProjectDetailPage(
    presenter: ProjectDetailPresenter
) : Page<ProjectDetailContract, ProjectDetailPresenter>(presenter), ProjectDetailContract {

    private val softwareListDiv by lazy {
        document.getElementById("software_list_div") as HTMLDivElement
    }

    override fun onCreate(content: HTMLElement) {
        content.append.div {
            id = "software_list_div"
        }
    }

    override fun getContract() = this

    override fun showSoftwareComponents(softwareComponents: List<SoftwareComponent>) {
        softwareListDiv.clear()
        softwareListDiv.append.div("card") {
            a(href = "#", classes = "card-body d-flex justify-content-between align-items-center") {
                +"Add Software Component"
                i("fas fa-plus-square")
            }
        }
    }

}