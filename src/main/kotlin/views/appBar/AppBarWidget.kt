package views.appBar

import constants.PageType
import models.Authenticator
import models.User
import org.w3c.dom.Element
import utils.createH2
import utils.createNavBarText
import utils.onClick
import kotlin.browser.document

class AppBarWidget(
    private val toolBarElement: Element,
    private val appBarPresenter: AppBarContract.Presenter,
    private val logoutCallback :() -> Unit
) : AppBarContract.View {

    private val titleElement = document.createNavBarText()
    private val userAvatar = document.createH2().apply {
        className = "badge badge-pill badge-light navbar-nav ml-auto"
        onClick {
            Authenticator().logout()
            logoutCallback.invoke()
        }
    }

    fun show() {
        toolBarElement.appendChild(titleElement)
        toolBarElement.appendChild(userAvatar)
        appBarPresenter.attach(this)
    }

    fun onPageChange(pageType: PageType) {
        appBarPresenter.setPageType(pageType)
    }

    fun onSignIn(user: User) {
        appBarPresenter.setUser(user)
    }

    override fun showPageTitle(title: String) {
        titleElement.textContent = title
    }

    override fun showUserAvatar(user: User) {
        userAvatar.textContent = user.name.take(1)
    }
}
