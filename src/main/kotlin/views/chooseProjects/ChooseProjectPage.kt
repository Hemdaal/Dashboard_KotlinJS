package views.chooseProjects

import kotlinx.html.dom.append
import kotlinx.html.i
import kotlinx.html.id
import kotlinx.html.js.div
import kotlinx.html.js.li
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.ul
import kotlinx.html.style
import kotlinx.html.ul
import models.Project
import mvp.Page
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLUListElement
import utils.*
import kotlin.browser.document
import kotlin.dom.clear

class ChooseProjectPage(
    presenter: ChooseProjectPresenter,
    private val onAddProjectClick: () -> Unit,
    private val onProjectClick: (project: Project) -> Unit
) : Page<ChooseProjectContract, ChooseProjectPresenter>(presenter), ChooseProjectContract {

    private val chooseProjectDiv by lazy {
        document.getElementById("choose_project_div") as HTMLUListElement
    }

    override fun onCreate(content: HTMLElement) {
        content.append.div("d-flex justify-content-center") {
            ul("list-group ") {
                id = "choose_project_div"
                style = """
                width: 40%;
            """.trimIndent()
            }
        }
    }

    override fun showProjects(projects: List<Project>) {
        projects.forEach { project ->
            chooseProjectDiv.append.li("list-group-item d-flex justify-content-between align-items-center") {
                +project.name
                i("fas fa-chevron-right") {

                }

                onClickFunction = {
                    onProjectClick(project)
                }
            }
        }
        chooseProjectDiv.append.li("list-group-item d-flex justify-content-between align-items-center") {
            +"Create Project"
            i("fas fa-plus-square") {

            }
            onClickFunction = {
                onAddProjectClick()
            }
        }
    }

    override fun getContract() = this
}