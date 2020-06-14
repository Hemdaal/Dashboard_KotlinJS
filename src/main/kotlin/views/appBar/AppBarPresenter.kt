package views.appBar

import constants.PageType
import constants.PageType.*
import models.User

class AppBarPresenter : AppBarContract.Presenter {

    private lateinit var view: AppBarContract.View

    override fun attach(view: AppBarContract.View) {
        this.view = view
    }

    override fun setPageType(pageType: PageType) {
        val title = when(pageType) {
            APP -> "Hemdaal"
            LOGIN -> "Login"
            SIGNUP -> "Signup"
            CREATE_PROJECT -> "Create Project"
            PROJECT -> "Project"
        }
        view.showPageTitle(title)
    }

    override fun setUser(user: User) {
        view.showUserAvatar(user)
    }
}

class AppBarContract {

    interface View {
        fun showPageTitle(title: String)
        fun showUserAvatar(user: User)
    }

    interface Presenter {
        fun attach(view: View)
        fun setPageType(pageType: PageType)
        fun setUser(user: User)
    }
}