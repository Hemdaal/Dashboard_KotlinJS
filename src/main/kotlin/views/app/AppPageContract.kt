package views.app

import models.Project
import models.User
import mvp.PageContract

interface AppPageContract : PageContract {

    fun showLoading()
    fun showLoginPage()
    fun showProjects(user: User, projects: List<Project>)
    fun showCreateProject(user: User)
    fun setUserInNavBar(user: User)
}