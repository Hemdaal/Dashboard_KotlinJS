package views.chooseProjects

import models.Project
import org.w3c.dom.HTMLDivElement
import utils.createDiv
import utils.createH4
import utils.createIcon
import kotlin.browser.document
import kotlin.dom.clear

class ChooseProjectPage(
    private val content: HTMLDivElement,
    private val chooseProjectPresenter: ChooseProjectPresenter,
    private val onAddProjectClick: () -> Unit,
    private val onProjectClick: (project: Project) -> Unit
) : ChooseProjectContract.View {


    fun show() {
        content.clear()
        chooseProjectPresenter.attach(this)
    }

    override fun showProjects(projects: List<Project>) {
        content.clear()
        projects.forEach { project ->
            val card = document.createDiv().apply {
                className = "card text-center"
                appendChild(document.createDiv().apply {
                    className = "card-body"
                    appendChild(document.createH4(project.name))
                })
            }
            content.appendChild(card)
        }
        val card = document.createDiv().apply {
            className = "card text-center"
            appendChild(document.createDiv().apply {
                className = "card-body"
                appendChild(document.createIcon("plus"))
                appendChild(document.createH4("Create Project"))
            })
        }
        content.appendChild(card)
    }
}