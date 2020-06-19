package views.addSoftware

import models.Project
import models.SoftwareComponent
import mvp.Presenter

class AddSoftwarePresenter(private val project: Project) : Presenter<AddSoftwareContract>() {

    fun addSoftware(softwareName: String, gitUrlLink: String) {
        //TODO make api call
        contract.softwareCreated(SoftwareComponent(softwareName))
    }


}