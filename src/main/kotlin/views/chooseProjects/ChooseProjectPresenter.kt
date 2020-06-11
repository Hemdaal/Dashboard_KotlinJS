package views.chooseProjects

import models.Project

class ChooseProjectPresenter(private val projects: List<Project>) : ChooseProjectContract.Presenter {

    private lateinit var view: ChooseProjectContract.View

    override fun attach(view: ChooseProjectContract.View) {
        this.view = view

        view.showProjects(projects)
    }
}

class ChooseProjectContract {

    interface View {
        fun showProjects(projects: List<Project>)
    }

    interface Presenter {
        fun attach(view: View)
    }
}