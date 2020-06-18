package views.projectDetail

import models.SoftwareComponent
import mvp.PageContract

interface ProjectDetailContract : PageContract {
    fun showSoftwareComponents(softwareComponents : List<SoftwareComponent>)
}