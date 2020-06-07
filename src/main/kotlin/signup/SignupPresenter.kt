package register

class SignupPresenter : SignupContract.Presenter {

    private lateinit var view: SignupContract.View

    override fun attach(view: SignupContract.View) {
        this.view = view
    }

    override fun signup(firstName: String, lastName: String, email: String, password: String) {
        view.showRegistrationFailed()
    }
}

class SignupContract {

    interface View {
        fun goToDashboard()
        fun showRegistrationFailed()
    }

    interface Presenter {
        fun attach(view: View)
        fun signup(firstName: String, lastName: String, email: String, password: String)
    }
}