package views.addSoftware

import models.Project
import models.SoftwareComponent

class AddSoftwarePresenter(private val project: Project) : AddSoftwareContract.Presenter {

    private lateinit var view: AddSoftwareContract.View

    override fun attach(view: AddSoftwareContract.View) {
        this.view = view

        view.showForm()
    }

    override fun addSoftware(softwareName: String, gitUrlLink: String) {
        //TODO make api call
        view.softwareCreated(SoftwareComponent(softwareName))
    }


}

class AddSoftwareContract {

    interface View {

        fun showForm()
        fun softwareCreated(softwareComponent: SoftwareComponent)
    }

    interface Presenter {

        fun attach(view: View)
        fun addSoftware(softwareName: String, gitUrlLink: String)
    }
}