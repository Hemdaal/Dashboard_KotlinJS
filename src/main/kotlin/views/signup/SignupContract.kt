package views.signup

import mvp.PageContract

interface SignupContract : PageContract {

    fun goToDashboard()
    fun showRegistrationFailed()
    fun showProgressBar()
}