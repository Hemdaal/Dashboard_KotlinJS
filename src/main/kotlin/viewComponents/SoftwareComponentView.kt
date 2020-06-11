package viewComponents

import org.w3c.dom.HTMLElement
import utils.createDiv
import utils.createH4
import kotlin.browser.document
import kotlin.dom.appendText

class SoftwareComponentView {

    fun getView(softwareComponentName: String): HTMLElement {
        val addSoftwareCardElement = document.createDiv().apply {
            id = "software_card"
            className = "card"
        }
        val addSoftwareCardContainerElement = document.createDiv().apply {
            className = "card_container"
        }
        val titleElement = document.createH4().apply {
            appendText(softwareComponentName)
        }

        addSoftwareCardElement.appendChild(addSoftwareCardContainerElement)
        addSoftwareCardContainerElement.appendChild(titleElement)

        return addSoftwareCardElement
    }
}