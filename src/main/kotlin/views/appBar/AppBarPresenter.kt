package views.appBar

import constants.PageType
import constants.PageType.*

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
        }
        view.showPageTitle(title)
    }
}

class AppBarContract {

    interface View {
        fun showPageTitle(title: String)
        fun showUserAvatar()
    }

    interface Presenter {
        fun attach(view: View)
        fun setPageType(pageType: PageType)
    }
}