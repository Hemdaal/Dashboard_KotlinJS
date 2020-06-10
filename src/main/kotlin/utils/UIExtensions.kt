package utils

import org.w3c.dom.*
import kotlin.browser.document

fun HTMLElement.onClick(clickCallback: () -> Unit) {
    addEventListener("click", {
        it.preventDefault()
        clickCallback.invoke()
    })
}

fun Document.createDiv() = document.createElement("div") as HTMLDivElement
fun Document.createInput() = document.createElement("input") as HTMLInputElement
fun Document.createSpan() = document.createElement("span") as HTMLSpanElement
fun Document.createForm() = document.createElement("form") as HTMLFormElement
fun Document.createButton() = document.createElement("button") as HTMLButtonElement
fun Document.createH1() = document.createElement("h1") as HTMLHeadingElement
fun Document.createH2() = document.createElement("h2") as HTMLHeadingElement
fun Document.createH3() = document.createElement("h3") as HTMLHeadingElement
fun Document.createH4() = document.createElement("h4") as HTMLHeadingElement
