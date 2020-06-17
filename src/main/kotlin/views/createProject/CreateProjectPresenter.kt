package views.createProject

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.Project
import models.User
import mvp.Presenter

class CreateProjectPresenter(
    private val user: User
) : Presenter<CreatePageContract>() {

    fun createProject(name: String) {
        contract.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val project = user.createProject(name)
            if (project != null) {
                contract.showProjectCreated(project)
            } else {
                contract.showCreateProjectFailure()
            }
        }
    }
}