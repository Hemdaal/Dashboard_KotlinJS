package views.createProject

import models.Project
import org.w3c.dom.HTMLDivElement
import utils.*
import kotlin.browser.document
import kotlin.dom.appendText
import kotlin.dom.clear

class CreateProjectPage(
    private val content: HTMLDivElement,
    private val createProjectPresenter: CreateProjectContract.Presenter,
    private val projectCreatedCallback: (Project) -> Unit,
    private val cancelledCallback: (() -> Unit)? = null
) : CreateProjectContract.View {

    private val form = document.createForm()

    private val messageElement = document.createSpan()

    private val nameElement = document.createInput("Project Name")

    private lateinit var progressCallback: (Boolean) -> Unit

    fun show() {
        createProjectPresenter.attach(this)
    }

    override fun showCreateProjectPage() {
        val submit = document.createButton("Create") {
            progressCallback = it
        }
        val cancelBtn = document.createBorderButton("Cancel")

        submit.onClick {
            createProjectPresenter.createProject(name = nameElement.value)
        }

        form.append(
            messageElement, document.createLineBreak(),
            nameElement, document.createLineBreak(),
            cancelBtn, submit
        )
        content.clear()
        content.appendChild(form)

        if (cancelledCallback == null) {
            cancelBtn.disabled = true
        } else {
            cancelBtn.disabled = false
            cancelBtn.onClick {
                cancelledCallback.invoke()
            }
        }
    }

    override fun showCreateProjectFailure() {
        messageElement.clear()
        messageElement.appendText("Unable to create project")
        progressCallback.invoke(false)
    }

    override fun showProjectCreated(project: Project) {
        projectCreatedCallback.invoke(project)
    }

    override fun showLoading() {
        messageElement.clear()
        progressCallback.invoke(true)
    }

}