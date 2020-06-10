package views.createProject

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.Project
import models.User

class CreateProjectPresenter(
    private val user: User
) : CreateProjectContract.Presenter {

    private lateinit var view: CreateProjectContract.View

    override fun attach(view: CreateProjectContract.View) {
        this.view = view
        view.showCreateProjectPage()
    }

    override fun createProject(name: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val project = user.createProject(name)
            if (project != null) {
                view.showProjectCreated(project)
            } else {
                view.showCreateProjectFailure()
            }
        }
    }
}

class CreateProjectContract {

    interface View {
        fun showCreateProjectPage()
        fun showCreateProjectFailure()
        fun showProjectCreated(project: Project)
    }

    interface Presenter {
        fun attach(view: View)
        fun createProject(name: String)
    }
}