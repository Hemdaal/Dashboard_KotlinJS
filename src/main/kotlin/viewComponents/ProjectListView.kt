package viewComponents

import org.w3c.dom.HTMLElement
import utils.createDiv
import utils.createH4
import kotlin.browser.document
import kotlin.dom.appendText

class ProjectListView {

    fun getView(projectName: String): HTMLElement {
        val projectItemCardElement = document.createDiv().apply {
            id = "project_item_card"
            className = "card"
        }
        val projectItemCardContainerElement = document.createDiv().apply {
            className = "card_container"
        }
        val titleElement = document.createH4().apply {
            appendText(projectName)
        }

        projectItemCardElement.appendChild(projectItemCardContainerElement)
        projectItemCardContainerElement.appendChild(titleElement)

        return projectItemCardElement
    }
}