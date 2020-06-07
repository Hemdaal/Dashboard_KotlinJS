package signup

import lineBreak
import login.LoginPage
import login.LoginPresenter
import org.w3c.dom.*
import register.SignupContract
import register.SignupPresenter
import kotlin.browser.document
import kotlin.dom.appendText
import kotlin.dom.clear

class SignupPage(val signupPresenter: SignupPresenter) : SignupContract.View {

    private val content = document.getElementById("app") as HTMLDivElement

    private val form = (document.createElement("form") as HTMLFormElement).apply {
        id = "signup_form"
    }

    private val messageElement = (document.createElement("span") as HTMLSpanElement).apply {
        id = "message"
    }

    private val firstNameElement = (document.createElement("input") as HTMLInputElement).apply {
        type = "text"
        placeholder = "First Name"
        id = "first_name"
    }

    private val lastNameElement = (document.createElement("input") as HTMLInputElement).apply {
        type = "text"
        placeholder = "Last Name"
        id = "last_name"
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

        form.append(messageElement, lineBreak(), firstNameElement, lineBreak(), lastNameElement, lineBreak(), emailElement, lineBreak(), passwordElement, lineBreak(), submit, loginElement)

        submit.addEventListener("submit", {
            it.preventDefault()
            signupPresenter.signup(
                    firstName = firstNameElement.value,
                    lastName = lastNameElement.value,
                    email = emailElement.value,
                    password = passwordElement.value
            )
        })
        loginElement.addEventListener("click", {
            it.preventDefault()
            LoginPage(LoginPresenter()).show()
        })

        content.clear()
        content.appendChild(form)
    }

    override fun goToDashboard() {
        TODO("Not yet implemented")
    }

    override fun showRegistrationFailed() {
        messageElement.clear()
        messageElement.appendText("Invalid username or password.")
    }
}