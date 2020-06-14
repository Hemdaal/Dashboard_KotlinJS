package views.chooseProjects

import models.Project
import org.w3c.dom.HTMLDivElement
import utils.*
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
        val card = document.createDiv().apply {
            className = "card project text-center mx-auto"
        }
        val list = document.createList()
        card.appendChild(list)
        projects.forEach { project ->
            val item = document.createActionListItem().apply {
                textContent = project.name
                onClick {
                    onProjectClick(project)
                }
            }
            list.appendChild(item)
        }
        val item = document.createActionListItem().apply {
            textContent = "Create Project"
            onClick {
                onAddProjectClick()
            }
        }
        list.appendChild(item)
        content.appendChild(card)
    }
}