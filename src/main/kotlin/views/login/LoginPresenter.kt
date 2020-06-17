package views.login

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.Authenticator
import mvp.Presenter

class LoginPresenter: Presenter<LoginContract>() {

    fun login(email: String, password: String) {
        contract.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            val loginStatus = Authenticator().login(email, password)

            if(loginStatus) {
                contract.goToDashboard()
            } else {
                contract.showLoginFailed()
            }
        }
    }
}