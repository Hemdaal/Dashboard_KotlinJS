package mvp

abstract class Presenter<C : PageContract> {

    protected lateinit var contract: C

    internal fun attach(contract: C) {
        this.contract = contract
        onAttached()
    }

    protected open fun onAttached() {}
}