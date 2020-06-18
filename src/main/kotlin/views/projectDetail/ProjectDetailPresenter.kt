package views.projectDetail

import models.Project
import models.SoftwareComponent
import mvp.Presenter

class ProjectDetailPresenter(private val project: Project) : Presenter<ProjectDetailContract>() {

    override fun onAttached() {
        super.onAttached()

        contract.showSoftwareComponents(emptyList())
    }
}