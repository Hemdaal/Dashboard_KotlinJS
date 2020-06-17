package utils

import kotlinx.html.TagConsumer
import kotlinx.html.consumers.onFinalize
import kotlinx.html.dom.createTree
import mvp.Page
import org.w3c.dom.*
import kotlin.browser.document
import kotlin.dom.clear

fun Element.onClick(clickCallback: () -> Unit) {
    addEventListener("click", {
        it.preventDefault()
        clickCallback.invoke()
    })
}

fun HTMLElement.replace(page: Page<*, *>) {
    clear()
    page.onCreate(this)
    page.attach()
}

fun HTMLElement.replace(): TagConsumer<HTMLElement> {
    clear()
    return ownerDocumentExt.createTree().onFinalize { element, partial ->
        if (!partial) {
            appendChild(element)
        }
    }
}

private val Node.ownerDocumentExt: Document
    get() = when (this) {
        is Document -> this
        else -> ownerDocument ?: throw IllegalStateException("Node has no ownerDocument")
    }
