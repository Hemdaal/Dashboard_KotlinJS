package views.login

import org.w3c.dom.*
import utils.lineBreak
import kotlin.browser.document
import kotlin.dom.appendText
import kotlin.dom.clear

class LoginPage(
    private val content: HTMLDivElement,
    private val loginPresenter: LoginPresenter,
    private val loginStateCallback: () -> Unit,
    private val signupCallback: () -> Unit
) : LoginContract.View {

    private val form = (document.createElement("form") as HTMLFormElement).apply {
        id = "login_form"
    }

    private val messageElement = (document.createElement("span") as HTMLSpanElement).apply {
        id = "message"
    }

    private val emailElement = (document.createElement("input") as HTMLInputElement).apply {
        type = "text"
        placeholder = "Email"
        id = "email"
    }

    private val passwordElement = (document.createElement("input") as HTMLInputElement).apply {
        type = "password"
        placeholder = "Password"
        id = "password"
    }

    fun show() {
        loginPresenter.attach(this)

        val submit = (document.createElement("button") as HTMLButtonElement).apply {
            textContent = "Sign In"
        }

        val signUp = (document.createElement("button") as HTMLButtonElement).apply {
            textContent = "Signup"
        }
        form.append(
            messageElement,
            lineBreak(), emailElement,
            lineBreak(), passwordElement,
            lineBreak(), submit, signUp
        )

        submit.addEventListener("click", {
            it.preventDefault()
            loginPresenter.login(email = emailElement.value, password = passwordElement.value)
        })

        signUp.addEventListener("click", {
            it.preventDefault()
            signupCallback.invoke()
        })

        content.clear()
        content.appendChild(form)
    }

    override fun goToDashboard() {
        loginStateCallback.invoke()
    }

    override fun showLoginFailed() {
        messageElement.clear()
        messageElement.appendText("Invalid username or password.")
    }

    override fun showLoading() {
        messageElement.clear()
        messageElement.appendText("Please wait.")
    }
}