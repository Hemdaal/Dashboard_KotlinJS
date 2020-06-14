package views.projectDetail

import models.Project
import models.SoftwareComponent

class ProjectDetailPresenter(private val project: Project) : ProjectDetailContract.Presenter {

    private lateinit var view: ProjectDetailContract.View

    override fun attach(view: ProjectDetailContract.View) {
        this.view = view
        view.showProjectDetails(project)
    }

    override fun onSoftwareComponentAdded(softwareComponent: SoftwareComponent) {
        //TODO
    }
}

class ProjectDetailContract {

    interface View {
        fun showProjectDetails(project: Project)

        fun showSoftwareComponents(softwareComponents : List<SoftwareComponent>)
    }

    interface Presenter {
        fun attach(view: View)
        fun onSoftwareComponentAdded(softwareComponent: SoftwareComponent)
    }
}