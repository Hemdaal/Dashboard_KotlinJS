package views.login

import org.w3c.dom.HTMLDivElement
import utils.*
import kotlin.browser.document
import kotlin.dom.appendText
import kotlin.dom.clear

class LoginPage(
    private val content: HTMLDivElement,
    private val loginPresenter: LoginPresenter,
    private val loginStateCallback: () -> Unit,
    private val signupCallback: () -> Unit
) : LoginContract.View {

    private val form = document.createForm().apply {
        id = "login_form"
    }

    private val messageElement = document.createSpan().apply {
        id = "message"
    }

    private val emailElement = document.createInput().apply {
        type = "text"
        placeholder = "Email"
        id = "email"
    }

    private val passwordElement = document.createInput().apply {
        type = "password"
        placeholder = "Password"
        id = "password"
    }

    fun show() {
        loginPresenter.attach(this)

        val submit = document.createButton().apply {
            textContent = "Sign In"
        }

        val signUp = document.createButton().apply {
            textContent = "Signup"
        }
        form.append(
            messageElement,
            lineBreak(), emailElement,
            lineBreak(), passwordElement,
            lineBreak(), submit, signUp
        )

        submit.onClick {
            loginPresenter.login(email = emailElement.value, password = passwordElement.value)
        }

        signUp.onClick {
            signupCallback.invoke()
        }

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