package views.login

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.Authenticator

class LoginPresenter: LoginContract.Presenter {

    private lateinit var view: LoginContract.View

    override fun attach(view: LoginContract.View) {
        this.view = view
    }

    override fun login(email: String, password: String) {
        view.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val loginStatus = Authenticator().login(email, password)

            if(loginStatus) {
                view.goToDashboard()
            } else {
                view.showLoginFailed()
            }
        }
    }
}

class LoginContract {

    interface View {
        fun goToDashboard()
        fun showLoginFailed()
        fun showLoading()
    }

    interface Presenter {
        fun attach(view: View)
        fun login(email: String, password: String)
    }
}