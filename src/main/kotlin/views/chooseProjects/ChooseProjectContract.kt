package views.chooseProjects

import models.Project
import mvp.PageContract

interface ChooseProjectContract : PageContract {
    fun showProjects(projects: List<Project>)
}