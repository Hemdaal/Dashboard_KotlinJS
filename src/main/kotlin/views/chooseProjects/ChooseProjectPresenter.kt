package views.chooseProjects

import models.Project
import mvp.Presenter

class ChooseProjectPresenter(private val projects: List<Project>) : Presenter<ChooseProjectContract>() {

    override fun onAttached() {
        super.onAttached()
        contract.showProjects(projects)
    }
}