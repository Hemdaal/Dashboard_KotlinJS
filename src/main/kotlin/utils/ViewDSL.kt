package utils

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