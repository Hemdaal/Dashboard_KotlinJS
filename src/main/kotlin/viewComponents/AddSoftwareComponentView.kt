package viewComponents

import org.w3c.dom.HTMLElement
import utils.createDiv
import utils.createH4
import kotlin.browser.document
import kotlin.dom.appendText

class AddSoftwareComponentView {

    fun getView(): HTMLElement {
        val addSoftwareCardElement = document.createDiv().apply {
            id = "add_software_card"
            className = "software_card"
        }
        val addSoftwareCardContainerElement = document.createDiv().apply {
            className = "software_card_container"
        }
        val titleElement = document.createH4().apply {
            appendText("Add Software")
        }

        addSoftwareCardElement.appendChild(addSoftwareCardContainerElement)
        addSoftwareCardContainerElement.appendChild(titleElement)

        return addSoftwareCardElement
    }
}