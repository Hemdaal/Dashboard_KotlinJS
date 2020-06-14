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

    private val form = document.createForm()

    private lateinit var progressCallback: (Boolean) -> Unit

    private val messageElement = document.createSpan().apply {
        id = "message"
    }

    private val emailElement = document.createInput("Email").apply {
        type = "text"
    }

    private val passwordElement = document.createInput("Password").apply {
        type = "password"
    }

    fun show() {
        loginPresenter.attach(this)

        val submit = document.createButton("Sign In") {
            progressCallback = it
        }
        val signUp = document.createBorderButton("Sign Up")

        form.append(
            messageElement, document.createLineBreak(),
            emailElement, document.createLineBreak(),
            passwordElement, document.createLineBreak()
        )

        submit.onClick {
            loginPresenter.login(email = emailElement.value, password = passwordElement.value)
        }

        signUp.onClick {
            signupCallback.invoke()
        }

        content.clear()
        content.appendChild(form)
        val buttonContainer = document.createContainer()
        content.appendChild(buttonContainer)

        buttonContainer.append(submit, signUp)
    }

    override fun goToDashboard() {
        loginStateCallback.invoke()
    }

    override fun showLoginFailed() {
        messageElement.clear()
        messageElement.appendText("Invalid username or password.")
        progressCallback(false)
    }

    override fun showLoading() {
        messageElement.clear()
        progressCallback(true)
    }
}