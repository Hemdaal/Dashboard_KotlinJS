package views.app

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.Authenticator
import models.Project
import models.User

class AppPresenter : AppContract.Presenter {

    private lateinit var view: AppContract.View

    override fun onAttach(view: AppContract.View) {
        this.view = view
    }

    override fun checkState() {
        view.showLoading()
        view.showLoading()
        if (Authenticator().getToken().isNullOrEmpty()) {
            view.showLoginPage()
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            val user = Authenticator().getUser()
            if (user == null) {
                view.showLoginPage()
            } else {
                view.setUserInNavBar(user)
                val projects = user.getProjects()
                if (projects.isEmpty()) {
                    view.showCreateProject(user)
                } else {
                    view.showProjects(user, projects)
                }
            }
        }
    }
}

class AppContract {
    interface View {
        fun showLoading()
        fun showLoginPage()
        fun showProjects(user: User, projects: List<Project>)
        fun showCreateProject(user: User)
        fun setUserInNavBar(user: User)
    }

    interface Presenter {
        fun onAttach(view: View)
        fun checkState()
    }
}