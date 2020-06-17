package views.appBar

import constants.PageType
import constants.PageType.*
import models.User
import mvp.Presenter

class AppBarPresenter : Presenter<AppBarContract>() {

    override fun onAttached() {
        setPageType(APP)
    }

    fun setPageType(pageType: PageType) {
        val title = when (pageType) {
            APP -> "Hemdaal"
            LOGIN -> "Login"
            SIGNUP -> "Signup"
            CREATE_PROJECT -> "Create Project"
            PROJECT -> "Project"
        }
        contract.showPageTitle(title)
    }

    fun setUser(user: User) {
        contract.showUserAvatar(user)
    }
}