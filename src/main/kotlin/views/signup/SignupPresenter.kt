package views.signup

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.Authenticator

class SignupPresenter : SignupContract.Presenter {

    private lateinit var view: SignupContract.View

    override fun attach(view: SignupContract.View) {
        this.view = view
    }

    override fun signup(name: String, email: String, password: String) {

        CoroutineScope(Dispatchers.Default).launch {
            view.showProgressBar()
            val isSuccessSignup = Authenticator().createUser(name, email, password)
            if(isSuccessSignup) {
                view.goToDashboard()
            } else {
                view.showRegistrationFailed()
            }
        }

    }
}

class SignupContract {

    interface View {
        fun goToDashboard()
        fun showRegistrationFailed()
        fun showProgressBar()
    }

    interface Presenter {
        fun attach(view: View)
        fun signup(name: String, email: String, password: String)
    }
}