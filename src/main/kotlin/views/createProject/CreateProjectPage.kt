package views.createProject

import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.js.div
import models.Project
import mvp.Page
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import utils.onClick
import kotlin.browser.document

class CreateProjectPage(
    presenter: CreateProjectPresenter,
    private val projectCreatedCallback: (Project) -> Unit,
    private val cancelledCallback: (() -> Unit)? = null
) : Page<CreatePageContract, CreateProjectPresenter>(presenter), CreatePageContract {

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
                            placeholder = "Project Name"
                        }
                    }
                    div("form-check") {
                        label("form-check-label flex-fill") {
                            input(type = InputType.checkBox, classes = "form-check-input flex-fill") {
                                attributes["data-toggle"] = "collapse"
                                attributes["data-target"] = "#project_management_div"
                            }
                            +"Project Management"
                        }
                    }
                    div("collapse") {
                        id = "project_management_div"
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
                                    +"JIRA"
                                }
                            }
                        }
                        div("form-group") {
                            input(type = InputType.text, classes = "form-control") {
                                id = "tool_url_text"
                                placeholder = "Tool url"
                            }
                        }
                        div("form-group") {
                            input(type = InputType.text, classes = "form-control") {
                                id = "tool_token_text"
                                placeholder = "Tool token"
                            }
                        }
                    }
                    div("button-group d-flex") {
                        role = "group"
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
            getPresenter().createProject(nameText.value)
        }

        cancelButton.onClick {
            cancelledCallback?.invoke()
        }
        cancelButton.disabled = cancelledCallback == null
    }

    override fun getContract() = this

    override fun showLoading() {
        alertMessageElement.className = "alert alert-danger fade"
        alertMessageElement.textContent = ""
        createLoader.className = "spinner-grow spinner-grow-sm show"
        createButton.disabled = true
    }

    override fun showCreateProjectFailure() {
        alertMessageElement.className = "alert alert-danger show"
        alertMessageElement.textContent = "Unable to Create Project"
        createLoader.className = ""
        createButton.disabled = false
    }

    override fun showProjectCreated(project: Project) {
        projectCreatedCallback(project)
    }

}