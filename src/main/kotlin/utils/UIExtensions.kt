package utils

import org.w3c.dom.*

fun HTMLElement.onClick(clickCallback: () -> Unit) {
    addEventListener("click", {
        it.preventDefault()
        clickCallback.invoke()
    })
}

fun Document.createPageContainer() = (createElement("div") as HTMLDivElement).apply {
    className = "container p-3 my-3 bg-white text-black"
}

fun Document.createContainer() = (createElement("div") as HTMLDivElement).apply {
    className = "container"
}

fun Document.createNav(brand: String) = (createElement("nav")).apply {
    className = "navbar navbar-expand-sm bg-primary navbar-dark sticky-top"
    appendChild(((createElement("a") as HTMLAnchorElement).apply {
        className = "navbar-brand"
        href = "#"
        text = brand
    }))
}

fun Document.createNavBarText(navBarText: String = "") = (createElement("span") as HTMLSpanElement).apply {
    className = "navbar-text"
    textContent = navBarText
}

fun Document.createForm() = (createElement("form") as HTMLFormElement).let {
    it.className = "form-inline"
    val row = createElement("div") as HTMLDivElement
    row.className = "form-row"
    it.appendChild(row)
    row
}


fun Document.createInput(hint: String = "") = (createElement("input") as HTMLInputElement).let {
    it.className = "col"
    val input = this.createElement("input") as HTMLInputElement
    input.className = "form-control mb-2 mr-sm-2"
    input.placeholder = hint
    it.appendChild(input)
    input
}


fun Document.createLineBreak() = createElement("br") as HTMLBRElement
fun Document.createDiv() = createElement("div") as HTMLDivElement
fun Document.createSpan() = createElement("span") as HTMLSpanElement
fun Document.createButton(text: String = "", callback: ((progressCallback: (Boolean) -> Unit) -> Unit)? = null) =
    (createElement("button") as HTMLButtonElement).apply {
        className = "btn btn-primary mr-2"
        type = "button"
        textContent = text
        val span = createSpan()
        appendChild(span)
        callback?.invoke {
            if (it) {
                span.className = "spinner-grow spinner-grow-sm"
                span.textContent = " "
                this.disabled = true
            } else {
                span.className = ""
                span.textContent = ""
                this.disabled = false
            }
        }
    }

fun Document.createBorderButton(text: String) = (createElement("button") as HTMLButtonElement).apply {
    className = "btn btn-outline-primary mr-2"
    type = "button"
    textContent = text
}

fun Document.createSpinner() = (createElement("div") as HTMLDivElement).apply {
    className = "spinner-grow text-primary"
}

fun Document.createH1() = createElement("h1") as HTMLHeadingElement
fun Document.createH2() = createElement("h2") as HTMLHeadingElement
fun Document.createH3() = createElement("h3") as HTMLHeadingElement
fun Document.createH4() = createElement("h4") as HTMLHeadingElement
