package views.chooseProjects

import models.Project
import org.w3c.dom.HTMLDivElement
import utils.createDiv
import utils.lineBreak
import utils.onClick
import viewComponents.AddProjectView
import viewComponents.ProjectListView
import kotlin.browser.document
import kotlin.dom.clear

class ChooseProjectPage(
    private val content: HTMLDivElement,
    private val chooseProjectPresenter: ChooseProjectPresenter,
    private val onAddProjectClick: () -> Unit,
    private val onProjectClick: (project: Project) -> Unit
) : ChooseProjectContract.View {

    private val projectsElement = document.createDiv()

    fun show() {
        content.clear()
        content.appendChild(projectsElement)
        chooseProjectPresenter.attach(this)
    }

    override fun showProjects(projects: List<Project>) {
        projectsElement.clear()
        val addProjectView = AddProjectView().getView()
        addProjectView.onClick {
            onAddProjectClick.invoke()
        }
        projectsElement.appendChild(addProjectView)
        projects.forEach {
            projectsElement.appendChild(lineBreak())
            val projectView = ProjectListView().getView(it.name)
            projectView.onClick {
                onProjectClick(it)
            }
            projectsElement.appendChild(projectView)
        }
    }
}