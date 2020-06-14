package views.signup

import org.w3c.dom.*
import utils.*
import kotlin.browser.document
import kotlin.dom.appendText
import kotlin.dom.clear

class SignupPage(
    private val content: HTMLDivElement,
    private val signupPresenter: SignupPresenter,
    private val loginStateCallback: () -> Unit,
    private val loginCallback: () -> Unit
) : SignupContract.View {

    private val form = document.createForm()

    private lateinit var progressCallback : (Boolean) -> Unit

    private val messageElement = document.createElement("span") as HTMLSpanElement

    private val nameElement = document.createInput("Name").apply {
        type = "text"
    }

    private val emailElement = document.createInput("Email").apply {
        type = "text"
    }

    private val passwordElement = document.createInput("Password").apply {
        type = "password"
    }

    fun show() {
        signupPresenter.attach(this)

        val loginElement = document.createBorderButton("Login")
        val submit = document.createButton("Sign up") {
            progressCallback = it
        }

        form.append(
            messageElement, document.createLineBreak(),
            nameElement, document.createLineBreak(),
            emailElement, document.createLineBreak(),
            passwordElement, document.createLineBreak()
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
            loginCallback.invoke()
        })

        content.clear()
        content.appendChild(form)
        val buttonContainer = document.createContainer()
        content.appendChild(buttonContainer)

        buttonContainer.append(submit, loginElement)
    }

    override fun goToDashboard() {
        loginStateCallback.invoke()
    }

    override fun showRegistrationFailed() {
        messageElement.clear()
        messageElement.appendText("Invalid username or password.")
        progressCallback.invoke(false)
    }

    override fun showProgressBar() {
        messageElement.clear()
        progressCallback.invoke(true)
    }
}