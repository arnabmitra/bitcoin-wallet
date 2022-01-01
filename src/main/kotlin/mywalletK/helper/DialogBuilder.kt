package mywalletK.helper

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXDialog
import com.jfoenix.controls.JFXDialogLayout
import javafx.event.EventHandler
import javafx.scene.control.TextInputDialog
import javafx.scene.input.MouseEvent
import javafx.scene.layout.StackPane
import javafx.scene.text.Text


class DialogBuilder {
    private var title = "Alert"
    private var content = "Content"

    constructor(title: String, content: String) : this(content) {
        this.title = title
    }

    constructor(content: String) {
        this.content = content
    }

    constructor() {}

    fun buildYesNo(stackPane: StackPane?, onOk: EventHandler<in MouseEvent?>?,
                   onCancel: EventHandler<in MouseEvent?>?): JFXDialog {
        val content = JFXDialogLayout()
        content.setHeading(Text(title))
        content.setBody(Text(this.content))
        val dialog = JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER)
        val btnOk = JFXButton("OK")
        btnOk.onMouseClicked = onOk
                ?: EventHandler { e: MouseEvent? -> dialog.close() }
        btnOk.style = "-fx-text-fill: green;"
        val btnCancel = JFXButton("CANCEL")
        btnCancel.onMouseClicked = onCancel
                ?: EventHandler { e: MouseEvent? -> dialog.close() }
        btnCancel.style = "-fx-text-fill: red;"
        content.setActions(btnOk, btnCancel)
        return dialog
    }

    companion object {
        @JvmStatic
        fun buildPasswordInputDialog(headerText: String? = "Wallet is encrypted, password required"): TextInputDialog {
            val dialog = TextInputDialog()
            dialog.title = "Wallet Password"
            dialog.headerText = headerText
            dialog.contentText = "Wallet Password"
            return dialog
        }
    }
}
