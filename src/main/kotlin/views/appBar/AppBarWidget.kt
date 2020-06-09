package views.appBar

import constants.PageType
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import kotlin.browser.document

class AppBarWidget(
    private val toolBarElement: HTMLDivElement,
    private val appBarPresenter: AppBarContract.Presenter
) : AppBarContract.View {

    private val titleElement = (document.createElement("a") as HTMLElement).apply {
        id = "title"
    }

    fun show() {
        toolBarElement.appendChild(titleElement)
        appBarPresenter.attach(this)
    }

    override fun showPageTitle(title: String) {
        titleElement.innerText = title
    }

    override fun showUserAvatar() {
    }

    fun onPageChange(pageType: PageType) {
        appBarPresenter.setPageType(pageType)
    }
}