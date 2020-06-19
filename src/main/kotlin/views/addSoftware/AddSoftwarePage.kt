package views.addSoftware

import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.js.div
import models.SoftwareComponent
import mvp.Page
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import utils.*
import kotlin.browser.document
import kotlin.dom.clear

class AddSoftwarePage(
    presenter: AddSoftwarePresenter,
    private val softwareCreatedCallback: (SoftwareComponent) -> Unit,
    private val cancelCallback: () -> Unit
) : Page<AddSoftwareContract, AddSoftwarePresenter>(presenter), AddSoftwareContract {

    private val alertMessageElement by lazy {
        document.getElementById("alert_text") as HTMLElement
    }

    private val createButton by lazy {
        document.getElementById("create_btn") as HTMLButtonElement
    }

    private val createLoader by lazy {
        document.getElementById("create_loader") as HTMLElement
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
                            placeholder = "Software Name"
                        }
                    }
                    div("form-check") {
                        label("form-check-label flex-fill") {
                            input(type = InputType.checkBox, classes = "form-check-input flex-fill") {
                                attributes["data-toggle"] = "collapse"
                                attributes["data-target"] = "#code_management_div"
                            }
                            +"Code Management"
                        }
                    }
                    div("collapse") {
                        id = "code_management_div"
                        hr("my-3")
                        div("form-group") {
                            label {
                                attributes["for"] = "tool_select"
                                +"Select Tool"
                            }
                            select("form-control") {
                                id = "tool_select"
                                option {
                                    +"GITHUB"
                                }
                                option {
                                    +"GITLAB"
                                }
                            }
                        }
                        div("form-group") {
                            input(type = InputType.text, classes = "form-control") {
                                id = "tool_url_text"
                                placeholder = "GIT url"
                            }
                        }
                        div("form-group") {
                            input(type = InputType.text, classes = "form-control") {
                                id = "tool_token_text"
                                placeholder = "Token (optional)"
                            }
                        }
                    }
                    div("button-group d-flex") {
                        role = "group"
                        style = """
                            margin-top: 12px;
                        """.trimIndent()
                        button(type = ButtonType.button, classes = "btn btn-primary flex-fill") {
                            id = "create_btn"
                            span {
                                id = "create_loader"
                            }
                            +"Create"
                            style = """
                                margin-right: 4px
                            """.trimIndent()
                        }
                        button(type = ButtonType.button, classes = "btn btn-outline-primary flex-fill") {
                            id = "cancel_btn"
                            +"Cancel"
                            style = """
                                margin-left: 4px
                            """.trimIndent()
                        }
                    }
                }
            }
        }

        val nameText = document.getElementById("name_text") as HTMLInputElement
        val cancelButton = document.getElementById("cancel_btn") as HTMLButtonElement

        createButton.onClick {
            getPresenter().addSoftware(nameText.value, "")
        }

        cancelButton.onClick {
            cancelCallback()
        }
    }

    override fun softwareCreated(softwareComponent: SoftwareComponent) {
        softwareCreatedCallback.invoke(softwareComponent)
    }

    override fun getContract() = this
}