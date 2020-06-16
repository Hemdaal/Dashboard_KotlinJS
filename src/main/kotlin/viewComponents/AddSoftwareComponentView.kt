package viewComponents

import org.w3c.dom.HTMLElement
import utils.createDiv
import utils.createH4
import kotlin.browser.document

class AddSoftwareComponentView {

    fun getView(): HTMLElement {
        val addSoftwareCardElement = document.createDiv().apply {
            id = "add_software_card"
            className = "card"
        }
        val addSoftwareCardContainerElement = document.createDiv().apply {
            className = "card_container"
        }
        val titleElement = document.createH4("Add Software")

        addSoftwareCardElement.appendChild(addSoftwareCardContainerElement)
        addSoftwareCardContainerElement.appendChild(titleElement)

        return addSoftwareCardElement
    }
}