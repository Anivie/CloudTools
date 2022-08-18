package ink.bluecloud.cloudtools.cloudmessagebox

import ink.bluecloud.cloudtools.cloudmessagebox.Node.MessageView
import ink.bluecloud.cloudtools.cloudmessagebox.Property.MessageBoxProperty
import ink.bluecloud.cloudtools.cloudmessagebox.Property.Theme.MessageBoxTheme
import javafx.scene.Node
import javafx.scene.image.Image
import javafx.scene.layout.Pane

/**
 * @author Anivie
 */
class CloudMessageBox(
    title: String? = null,
    message: String? = null,
    messagePane: Pane? = null,
    icon: Image? = null,

    val property: MessageBoxProperty = MessageBoxProperty(),
    theme: MessageBoxTheme = MessageBoxTheme(),
    button: Array<String> = arrayOf("取消", "确定")
) {
    var view: MessageView

    init {
        title?.run { property.title.set(this) }
        message?.run { property.message.set(this) }
        messagePane?.run { property.messagePane.set(this) }
        icon?.run { property.icon.set(this) }

        view = MessageView(theme, property, button)
    }

    fun changeMessage(title: String?, message: String?, button: Array<String>): CloudMessageBox {
        val property = MessageBoxProperty().apply {
            if (title != null) this.title.set(title)
            if (message != null) this.message.set(message)
        }
        view = MessageView(MessageBoxTheme(), property, button)
        return this
    }

    fun changeMessage(title: String?, node: Pane?, button: Array<String>): CloudMessageBox {
        val property = MessageBoxProperty().apply {
            if (title != null) this.title.set(title)
            if (node != null) this.messagePane.set(node)
        }
        view = MessageView(MessageBoxTheme(), property, button)
        return this
    }

    fun showOnly() {
        view.showAndWait()
        view.close()
    }

    fun showOnNode(): Node? {
        view.showAndWait()
        return view.node
    }

    fun showAndGet(): Int {
        view.showAndWait()
        return view.index.get()
    }
}