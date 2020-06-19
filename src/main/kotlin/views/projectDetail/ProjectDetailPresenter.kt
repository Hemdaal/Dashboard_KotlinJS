package views.projectDetail

import models.Project
import models.SoftwareComponent
import mvp.Presenter

class ProjectDetailPresenter(private val project: Project) : Presenter<ProjectDetailContract>() {

    private val softwareComponents = mutableListOf<SoftwareComponent>()

    override fun onAttached() {
        super.onAttached()

        //TODO fetch software components
        contract.showProjectDetails(project, softwareComponents)
    }

    fun addSoftwareCanceled() {
        contract.showProjectDetails(project, softwareComponents)
    }

    fun softwareAdded(softwareComponent: SoftwareComponent) {
        softwareComponents.add(softwareComponent)
        contract.showProjectDetails(project, softwareComponents)
    }
}