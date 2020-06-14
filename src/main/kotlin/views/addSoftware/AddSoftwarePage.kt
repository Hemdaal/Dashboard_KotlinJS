package views.addSoftware

import models.SoftwareComponent
import org.w3c.dom.HTMLDivElement
import utils.*
import kotlin.browser.document
import kotlin.dom.clear

class AddSoftwarePage(
    private val content: HTMLDivElement,
    private val addSoftwarePresenter: AddSoftwareContract.Presenter,
    private val softwareCreatedCallback: (SoftwareComponent) -> Unit,
    private val cancelCallback: () -> Unit
) : AddSoftwareContract.View {

    fun show() {
        addSoftwarePresenter.attach(this)
    }

    override fun showForm() {
        content.clear()
        val form = document.createForm()
        val nameElement = document.createInput("Name")
        val gitHubUrlElement = document.createInput("Github Link")
        val addButton = document.createButton("Add")
        val cancelButton = document.createButton("Cancel")

        cancelButton.onClick {
            cancelCallback.invoke()
        }

        addButton.onClick {
            addSoftwarePresenter.addSoftware(nameElement.value, gitHubUrlElement.value)
        }

        form.append(
            nameElement, document.createLineBreak(),
            gitHubUrlElement, document.createLineBreak(),
            cancelButton, addButton
        )

        content.append(form)
    }

    override fun softwareCreated(softwareComponent: SoftwareComponent) {
        softwareCreatedCallback.invoke(softwareComponent)
    }
}