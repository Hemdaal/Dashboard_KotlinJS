package views.projectDetail

import models.Project
import models.SoftwareComponent
import mvp.PageContract

interface ProjectDetailContract : PageContract {
    fun showProjectDetails(project: Project, softwareComponents : List<SoftwareComponent>)
}