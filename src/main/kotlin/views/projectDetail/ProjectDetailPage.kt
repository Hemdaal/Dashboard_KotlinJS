package views.projectDetail

import models.Project
import models.SoftwareComponent
import org.w3c.dom.HTMLDivElement

class ProjectDetailPage(
    private val content: HTMLDivElement,
    private val projectDetailPresenter: ProjectDetailPresenter
) : ProjectDetailContract.View {

    fun show() {
        projectDetailPresenter.attach(this)
    }

    override fun showProjectDetails(project: Project) {

    }

    override fun showSoftwareComponents(softwareComponents: List<SoftwareComponent>) {

    }
}