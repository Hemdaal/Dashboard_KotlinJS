package login

class LoginPresenter: LoginContract.Presenter {

    private lateinit var view: LoginContract.View

    override fun attach(view: LoginContract.View) {
        this.view = view
    }

    override fun login(email: String, password: String) {
        view.showLoginFailed()
    }
}

class LoginContract {

    interface View {
        fun goToDashboard()
        fun showLoginFailed()
    }

    interface Presenter {
        fun attach(view: View)
        fun login(email: String, password: String)
    }
}