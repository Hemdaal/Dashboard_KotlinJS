package views.createProject

import models.Project
import mvp.PageContract

interface CreatePageContract : PageContract {
    fun showLoading()
    fun showCreateProjectFailure()
    fun showProjectCreated(project: Project)
}