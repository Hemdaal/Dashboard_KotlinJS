package views.app

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.Authenticator
import mvp.Presenter

class AppPresenter : Presenter<AppPageContract>() {

    fun checkState() {
        contract.showLoading()
        contract.showLoading()
        if (Authenticator().getToken().isNullOrEmpty()) {
            contract.showLoginPage()
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            val user = Authenticator().getUser()
            if (user == null) {
                contract.showLoginPage()
            } else {
                contract.setUserInNavBar(user)
                val projects = user.getProjects()
                if (projects.isEmpty()) {
                    contract.showCreateProject(user)
                } else {
                    contract.showProjects(user, projects)
                }
            }
        }
    }

    override fun onAttached() {
        checkState()
    }
}