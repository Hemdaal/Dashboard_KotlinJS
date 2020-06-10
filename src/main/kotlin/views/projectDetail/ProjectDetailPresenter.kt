package views.projectDetail

import models.Project
import models.SoftwareComponent

class ProjectDetailPresenter : ProjectDetailContract.Presenter {

    private lateinit var view: ProjectDetailContract.View

    override fun attach(view: ProjectDetailContract.View) {
        this.view = view
    }
}

class ProjectDetailContract {

    interface View {
        fun showProjectDetails(project: Project)

        fun showSoftwareComponents(softwareComponents : List<SoftwareComponent>)
    }

    interface Presenter {
        fun attach(view: View)
    }
}