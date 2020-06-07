package dashboard

class DashboardPresenter : DashboardContract.Presenter {

    private lateinit var view: DashboardContract.View

    override fun onAttach(view: DashboardContract.View) {
        this.view = view
    }

    override fun checkState() {
        view.showLoginPage()
    }
}

class DashboardContract {
    interface View {
        fun showLoginPage()
    }

    interface Presenter {
        fun onAttach(view: View)
        fun checkState()
    }
}