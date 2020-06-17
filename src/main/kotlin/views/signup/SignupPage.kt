package views.signup

import kotlinx.html.*
import kotlinx.html.dom.append
import mvp.Page
import org.w3c.dom.*
import utils.*
import kotlin.browser.document

class SignupPage(
    presenter: SignupPresenter,
    private val loginStateCallback: () -> Unit,
    private val loginCallback: () -> Unit
) : Page<SignupContract, SignupPresenter>(presenter), SignupContract {

    private val alertMessageElement by lazy {
        document.getElementById("alert_text") as HTMLElement
    }

    private val signupButton by lazy {
        document.getElementById("register_btn") as HTMLButtonElement
    }

    private val signupLoader by lazy {
        document.getElementById("login_loader") as HTMLElement
    }

    override fun onCreate(content: HTMLElement) {
        content.append.div("d-flex justify-content-center") {
            div {
                form {
                    div("alert alert-danger fade") {
                        id = "alert_text"
                        role = "alert"
                    }
                    div("form-group") {
                        input(type = InputType.text, classes = "form-control") {
                            id = "name_text"
                            placeholder = "Name"
                        }
                    }
                    div("form-group") {
                        input(type = InputType.email, classes = "form-control") {
                            id = "email_text"
                            placeholder = "Email Address"
                        }
                    }
                    div("form-group") {
                        input(type = InputType.password, classes = "form-control") {
                            id = "password_text"
                            placeholder = "Password"
                        }
                    }
                    div("button-group d-flex") {
                        role = "group"
                        button(type = ButtonType.button, classes = "btn btn-primary flex-fill") {
                            id = "register_btn"
                            span {
                                id = "login_loader"
                            }
                            +"Signup"
                            style = """
                                margin-right: 4px
                            """.trimIndent()
                        }
                        button(type = ButtonType.button, classes = "btn btn-outline-primary flex-fill") {
                            id = "login_btn"
                            +"Login"
                            style = """
                                margin-left: 4px
                            """.trimIndent()
                        }
                    }
                }
            }
        }

        val nameText = document.getElementById("name_text") as HTMLInputElement
        val emailText = document.getElementById("email_text") as HTMLInputElement
        val passwordText = document.getElementById("password_text") as HTMLInputElement
        val loginButton = document.getElementById("login_btn") as HTMLElement

        signupButton.onClick {
            getPresenter().signup(nameText.value, emailText.value, passwordText.value)
        }

        loginButton.onClick {
            loginCallback()
        }
    }

    override fun goToDashboard() {
        loginStateCallback.invoke()
    }

    override fun showRegistrationFailed() {
        alertMessageElement.className = "alert alert-danger show"
        alertMessageElement.textContent = "Unable to Login"
        signupLoader.className = ""
        signupButton.disabled = false
    }

    override fun showProgressBar() {
        alertMessageElement.className = "alert alert-danger fade"
        alertMessageElement.textContent = ""
        signupLoader.className = "spinner-grow spinner-grow-sm show"
        signupButton.disabled = true
    }

    override fun getContract() = this
}