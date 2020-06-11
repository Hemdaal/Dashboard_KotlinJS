package viewComponents

import org.w3c.dom.HTMLElement
import utils.createDiv
import utils.createH4
import kotlin.browser.document
import kotlin.dom.appendText

class AddProjectView {

    fun getView(): HTMLElement {
        val createProjectCardElement = document.createDiv().apply {
            id = "create_project_card"
            className = "card"
        }
        val createProjectCardContainerElement = document.createDiv().apply {
            className = "card_container"
        }
        val titleElement = document.createH4().apply {
            appendText("Create Project")
        }

        createProjectCardElement.appendChild(createProjectCardContainerElement)
        createProjectCardContainerElement.appendChild(titleElement)

        return createProjectCardElement
    }
}