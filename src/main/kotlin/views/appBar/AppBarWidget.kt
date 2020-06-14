package views.appBar

import constants.PageType
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import utils.createNavBarText
import kotlin.browser.document

class AppBarWidget(
    private val toolBarElement: Element,
    private val appBarPresenter: AppBarContract.Presenter
) : AppBarContract.View {

    private val titleElement = document.createNavBarText()

    fun show() {
        toolBarElement.appendChild(titleElement)
        appBarPresenter.attach(this)
    }

    override fun showPageTitle(title: String) {
        titleElement.textContent = title
    }

    override fun showUserAvatar() {
    }

    fun onPageChange(pageType: PageType) {
        appBarPresenter.setPageType(pageType)
    }
}