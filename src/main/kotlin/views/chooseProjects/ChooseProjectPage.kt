package views.chooseProjects

import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.js.div
import kotlinx.html.js.onClickFunction
import models.Project
import mvp.Page
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import kotlin.browser.document

class ChooseProjectPage(
    presenter: ChooseProjectPresenter,
    private val onAddProjectClick: () -> Unit,
    private val onProjectClick: (project: Project) -> Unit
) : Page<ChooseProjectContract, ChooseProjectPresenter>(presenter), ChooseProjectContract {

    private val chooseProjectDiv by lazy {
        document.getElementById("choose_project_div") as HTMLDivElement
    }

    override fun onCreate(content: HTMLElement) {
        content.append.div("d-flex justify-content-center") {
            div("list-group") {
                id = "choose_project_div"
                style = """
                width: 40%;
            """.trimIndent()
            }
        }
    }

    override fun showProjects(projects: List<Project>) {
        projects.forEach { project ->
            chooseProjectDiv.append.a(
                href = "#",
                classes = "list-group-item list-group-item-action list-group-item-light d-flex justify-content-between align-items-center"
            ) {
                +project.name
                i("fas fa-chevron-right") {

                }

                onClickFunction = {
                    onProjectClick(project)
                }
            }
        }
        chooseProjectDiv.append.a(
            href = "#",
            classes = "list-group-item list-group-item-action list-group-item-light d-flex justify-content-between align-items-center"
        ) {
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