package mvp

abstract class Presenter<C : PageContract> {

    protected lateinit var contract: C

    fun attach(contract: C) {
        this.contract = contract
        onAttached()
    }

    protected abstract fun onAttached()
}