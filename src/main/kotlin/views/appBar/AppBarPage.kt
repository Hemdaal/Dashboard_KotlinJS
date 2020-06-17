package views.appBar

import constants.PageType
import kotlinx.html.*
import kotlinx.html.dom.append
import models.Authenticator
import models.User
import mvp.Page
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLSpanElement
import utils.createH2
import utils.createNavBarText
import utils.onClick
import kotlin.browser.document

class AppBarPage(
    appBarPresenter: AppBarPresenter,
    private val logoutCallback :() -> Unit
) : Page<AppBarContract, AppBarPresenter>(appBarPresenter), AppBarContract {

    private val titleText by lazy {
        document.getElementById("title_text") as HTMLSpanElement
    }

    private val userAvatar by lazy {
        document.getElementById("user_avatar") as HTMLElement
    }

    override fun onCreate(content: HTMLElement) {
        content.append.div {
            span("navbar-text") {
                id = "title_text"
            }
            h2("badge badge-pill badge-light navbar-nav ml-auto") {
                id = "user_avatar"
            }
        }

        userAvatar.onClick {
            Authenticator().logout()
            logoutCallback()
        }
    }

    fun onPageChange(pageType: PageType) {
        getPresenter().setPageType(pageType)
    }

    fun onSignIn(user: User) {
        getPresenter().setUser(user)
    }

    override fun showPageTitle(title: String) {
        titleText.textContent = title
    }

    override fun showUserAvatar(user: User) {
        userAvatar.textContent = user.name.take(1)
    }

    override fun getContract() = this
}
