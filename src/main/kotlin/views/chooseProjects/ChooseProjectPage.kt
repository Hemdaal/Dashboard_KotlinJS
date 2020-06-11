package views.chooseProjects

import models.Project
import org.w3c.dom.HTMLDivElement
import utils.createDiv
import viewComponents.AddProjectView
import viewComponents.ProjectListView
import kotlin.browser.document
import kotlin.dom.clear

class ChooseProjectPage(
    private val content: HTMLDivElement,
    private val chooseProjectPresenter: ChooseProjectPresenter
) : ChooseProjectContract.View {

    private val projectsElement = document.createDiv()

    fun show() {
        content.clear()
        content.appendChild(projectsElement)
        chooseProjectPresenter.attach(this)
    }

    override fun showProjects(projects: List<Project>) {
        projectsElement.clear()
        projectsElement.appendChild(
            AddProjectView().getView()
        )
        projects.forEach {
            projectsElement.appendChild(
                ProjectListView().getView(it.name)
            )
        }
    }
}