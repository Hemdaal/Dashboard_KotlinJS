package views.projectDetail

import models.Project
import models.SoftwareComponent
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import utils.createDiv
import utils.createLineBreak
import utils.onClick
import viewComponents.AddSoftwareComponentView
import viewComponents.SoftwareComponentView
import views.addSoftware.AddSoftwarePage
import views.addSoftware.AddSoftwarePresenter
import kotlin.browser.document
import kotlin.dom.appendText
import kotlin.dom.clear

class ProjectDetailPage(
    private val content: HTMLDivElement,
    private val projectDetailPresenter: ProjectDetailPresenter
) : ProjectDetailContract.View {

    private val projectInfoElement = document.createDiv()
    private val softwareComponentsElement = document.createDiv()

    fun show() {
        projectDetailPresenter.attach(this)
    }

    override fun showProjectDetails(project: Project) {

        val titleElement = (document.createElement("h4") as HTMLElement).apply {
            id = "title"
        }
        titleElement.appendText(project.name)
        val addSoftwareComponentView = AddSoftwareComponentView().getView()
        addSoftwareComponentView.onClick {
            AddSoftwarePage(
                content = content,
                addSoftwarePresenter = AddSoftwarePresenter(project),
                cancelCallback = {

                },
                softwareCreatedCallback = {
                    projectDetailPresenter.onSoftwareComponentAdded(it)
                }
            ).show()
        }

        content.clear()
        content.appendChild(projectInfoElement)
        content.appendChild(softwareComponentsElement)
        projectInfoElement.clear()
        projectInfoElement.appendChild(titleElement)
        projectInfoElement.appendChild(document.createLineBreak())
        projectInfoElement.appendChild(addSoftwareComponentView)
    }

    override fun showSoftwareComponents(softwareComponents: List<SoftwareComponent>) {
        softwareComponentsElement.clear()
        softwareComponents.forEach {
            val softwareComponentView = SoftwareComponentView().getView(it.name)
            softwareComponentView.onClick {
                //TODO
            }
        }
    }
}