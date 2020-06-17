package views.signup

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.Authenticator
import mvp.Presenter

class SignupPresenter : Presenter<SignupContract>() {

    fun signup(name: String, email: String, password: String) {

        CoroutineScope(Dispatchers.Default).launch {
            contract.showProgressBar()
            val isSuccessSignup = Authenticator().createUser(name, email, password)
            if (isSuccessSignup) {
                contract.goToDashboard()
            } else {
                contract.showRegistrationFailed()
            }
        }
    }
}