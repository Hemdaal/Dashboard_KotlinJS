package views.createProject

import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.js.div
import models.Project
import mvp.Page
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import utils.*
import kotlin.browser.document
import kotlin.dom.appendText
import kotlin.dom.clear

class CreateProjectPage(
    presenter: CreateProjectPresenter,
    private val projectCreatedCallback: (Project) -> Unit,
    private val cancelledCallback: (() -> Unit)? = null
) : Page<CreatePageContract, CreateProjectPresenter>(presenter), CreatePageContract {

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
                        label("form-check-label") {
                            input(type = InputType.checkBox, classes = "form-check-input") {
                                attributes["data-toggle"] = "collapse"
                                attributes["data-target"] = "#project_management_div"
                            }
                            +"Project Management"
                        }
                    }
                    div("collapse") {
                        id = "project_management_div"
                        hr("my-3")

                    }
                }
            }
        }
    }

    override fun getContract() = this

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun showCreateProjectFailure() {
        TODO("Not yet implemented")
    }

    override fun showProjectCreated(project: Project) {
        TODO("Not yet implemented")
    }

}