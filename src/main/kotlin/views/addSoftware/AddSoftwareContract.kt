package views.addSoftware

import models.SoftwareComponent
import mvp.PageContract

interface AddSoftwareContract : PageContract {

    fun softwareCreated(softwareComponent: SoftwareComponent)
}