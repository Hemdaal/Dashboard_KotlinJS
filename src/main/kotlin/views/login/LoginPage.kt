package views.login

import org.w3c.dom.HTMLDivElement
import utils.*
import kotlin.browser.document
import kotlin.dom.appendText
import kotlin.dom.clear
import kotlinx.html.*
import kotlinx.html.dom.*
import kotlinx.html.js.onClickFunction
import mvp.Page
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement

class LoginPage(
    presenter: LoginPresenter,
    private val loginStateCallback: () -> Unit,
    private val signupCallback: () -> Unit
) : Page<LoginContract, LoginPresenter>(presenter), LoginContract {

    private val alertMessageElement by lazy {
        document.getElementById("alert_text") as HTMLElement
    }

    private val loginButton by lazy {
        document.getElementById("login_btn") as HTMLButtonElement
    }

    private val loginLoader by lazy {
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
                            id = "login_btn"
                            span {
                                id = "login_loader"
                            }
                            +"Login"
                            style = """
                                margin-right: 4px
                            """.trimIndent()
                        }
                        button(type = ButtonType.button, classes = "btn btn-outline-primary flex-fill") {
                            id = "register_btn"
                            +"Signup"
                            style = """
                                margin-left: 4px
                            """.trimIndent()
                        }
                    }
                }
            }
        }

        val emailText = document.getElementById("email_text") as HTMLInputElement
        val passwordText = document.getElementById("password_text") as HTMLInputElement
        val signupButton = document.getElementById("register_btn") as HTMLElement

        loginButton.onClick {
            getPresenter().login(emailText.value, passwordText.value)
        }

        signupButton.onClick {
            signupCallback()
        }
    }

    override fun goToDashboard() {
        loginStateCallback.invoke()
    }

    override fun showLoginFailed() {
        alertMessageElement.className = "alert alert-danger show"
        alertMessageElement.textContent = "Unable to Login"
        loginLoader.className = ""
        loginButton.disabled = false
    }

    override fun showLoading() {
        alertMessageElement.className = "alert alert-danger fade"
        alertMessageElement.textContent = ""
        loginLoader.className = "spinner-grow spinner-grow-sm show"
        loginButton.disabled = true
    }


    override fun getContract() = this
}