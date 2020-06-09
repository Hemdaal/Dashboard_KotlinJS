package views.createProject

import org.w3c.dom.*
import utils.lineBreak
import kotlin.browser.document
import kotlin.dom.appendText
import kotlin.dom.clear

class CreateProjectPage(
    private val content: HTMLDivElement,
    private val createProjectPresenter: CreateProjectContract.Presenter
) : CreateProjectContract.View {

    private val form = (document.createElement("form") as HTMLFormElement).apply {
        id = "create_project_form"
    }

    private val messageElement = (document.createElement("span") as HTMLSpanElement).apply {
        id = "message"
    }

    private val nameElement = (document.createElement("input") as HTMLInputElement).apply {
        type = "text"
        placeholder = "Project Name"
        id = "name"
    }

    fun show() {
        createProjectPresenter.attach(this)
    }

    override fun showCreateProjectPage() {
        val submit = (document.createElement("button") as HTMLButtonElement).apply {
            textContent = "Create"
        }

        submit.addEventListener("click", {
            it.preventDefault()
            createProjectPresenter.createProject(name = nameElement.value)
        })

        form.append(
            messageElement,
            lineBreak(), nameElement,
            lineBreak(), submit
        )
        content.clear()
        content.appendChild(form)
    }

    override fun showCreateProjectFailure() {
        messageElement.clear()
        messageElement.appendText("Unable to create project")
    }

}