package mvp

import org.w3c.dom.HTMLElement

abstract class Page<C : PageContract, P : Presenter<C>>(private val presenter: P) {

    abstract fun onCreate(content: HTMLElement)

    abstract fun getContract(): C

    fun attach() {
        presenter.attach(getContract())
    }

    protected fun getPresenter() = presenter
}