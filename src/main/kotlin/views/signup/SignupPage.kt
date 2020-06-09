package views.signup

import utils.lineBreak
import views.login.LoginPage
import views.login.LoginPresenter
import org.w3c.dom.*
import kotlin.browser.document
import kotlin.dom.appendText
import kotlin.dom.clear

class SignupPage(
    private val signupPresenter: SignupPresenter,
    private val loginStateCallback: () -> Unit
) : SignupContract.View {

    private val content = document.getElementById("app") as HTMLDivElement

    private val form = (document.createElement("form") as HTMLFormElement).apply {
        id = "signup_form"
    }

    private val messageElement = (document.createElement("span") as HTMLSpanElement).apply {
        id = "message"
    }

    private val nameElement = (document.createElement("input") as HTMLInputElement).apply {
        type = "text"
        placeholder = "Name"
        id = "name"
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
        signupPresenter.attach(this)

        val loginElement = (document.createElement("button") as HTMLButtonElement).apply {
            textContent = "Login"
        }

        val submit = (document.createElement("button") as HTMLButtonElement).apply {
            textContent = "Sign Up"
        }

        form.append(
            messageElement, lineBreak(),
            nameElement, lineBreak(),
            emailElement, lineBreak(),
            passwordElement, lineBreak(),
            submit, loginElement
        )

        submit.addEventListener("click", {
            it.preventDefault()
            signupPresenter.signup(
                name = nameElement.value,
                email = emailElement.value,
                password = passwordElement.value
            )
        })
        loginElement.addEventListener("click", {
            it.preventDefault()
            LoginPage(LoginPresenter(), loginStateCallback).show()
        })

        content.clear()
        content.appendChild(form)
    }

    override fun goToDashboard() {
        loginStateCallback.invoke()
    }

    override fun showRegistrationFailed() {
        messageElement.clear()
        messageElement.appendText("Invalid username or password.")
    }

    override fun showProgressBar() {
        messageElement.clear()
        messageElement.appendText("Please wait")
    }
}