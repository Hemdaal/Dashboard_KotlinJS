package views.login

import mvp.PageContract

interface LoginContract : PageContract {

    fun goToDashboard()
    fun showLoginFailed()
    fun showLoading()
}