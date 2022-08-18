package ink.bluecloud.cloudtools.cloudmessagebox.Node

import ink.bluecloud.cloudtools.cloudmessagebox.Property.MessageBoxProperty
import ink.bluecloud.cloudtools.cloudmessagebox.Property.Theme.MessageBoxTheme
import javafx.application.Platform
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.effect.BlurType
import javafx.scene.effect.DropShadow
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.StageStyle
import javafx.stage.WindowEvent
import kotlin.concurrent.thread

/**
 * @author Anivie
 */
class MessageView(
    theme: MessageBoxTheme,
    property: MessageBoxProperty,
    messageButton: Array<String>,
) : Stage() {
    val index = SimpleIntegerProperty(-1)
    val isCanClose = SimpleBooleanProperty(true)


    var node: Node? = null

    /**
     * 定义偏移量，用于处理窗口移动
     */
    private var xOffset = 0.0
    private var yOffset = 0.0
    init {
        val titleBox = HBox().apply {
            padding = Insets(5.0, 0.0,5.0,0.0)
            alignment = Pos.CENTER
            children += Label(property.title.get()).apply {
                style = "-fx-font-size: ${theme.titleSize.get()};-fx-text-fill: ${theme.titleColor}"
                isMouseTransparent = true
            }
        }

        val textBox = HBox().apply {
            padding = Insets(0.0, 50.0,0.0,20.0)
            maxWidth = property.width.get()
            children += Label(property.message.get()).apply {
                style = "-fx-font-size: ${theme.textSize.get()};-fx-text-fill: ${theme.textColor}"
                isWrapText = true
            }
        }

        val baseBox = StackPane().apply {
            children += VBox().apply box@{
                property.title.value?.run { children += titleBox }
                property.message.value?.run { children += textBox }
                property.messagePane.value?.run {
                    this@box.children.add(this)
                }

                spacing = 10.0
                children += getButtonBox(messageButton)
                style = "-fx-background-radius: ${property.radios.get()};-fx-background-color: ${theme.backgroundColor}"
                effect = DropShadow(20.0, Color.rgb(0, 0, 0, 0.3)).apply { blurType = BlurType.GAUSSIAN }
            }

            padding = Insets(20.0)
            style = "-fx-background-color: transparent"

            if (property.stageCanDrag.value) {
                setOnMousePressed { event ->
                    if (event.target != ((this.children[0] as VBox).children[0] as Pane)) return@setOnMousePressed
                    xOffset = event.sceneX
                    yOffset = event.sceneY
                }
                setOnMouseDragged { event ->
                    if (event.target != ((this.children[0] as VBox).children[0] as Pane)) return@setOnMouseDragged
                    this@MessageView.x = event.screenX - xOffset
                    this@MessageView.y = event.screenY - yOffset
                }
            }
        }

        addEventHandler(WindowEvent.WINDOW_SHOWN) {
//            baseBox.translateY = 0.0
//            (if (theme.animation) ZoomInDown(baseBox) else BounceInDown(baseBox)).play()
        }

        property.run {
            parentStage.get()?.run {
                if (!property.parentStage.get().icons.isEmpty()) {
                    this@MessageView.icons.add(property.parentStage.get().icons[0])
                }
            }
            icon.get()?.run { this@MessageView.icons.add(property.icon.get()) }
            messagePane.get()?.run { node = property.messagePane.value }

            title.addListener { _, _, newValue -> this@MessageView.title = newValue }
            icon.addListener { _, _, newValue -> this@MessageView.icons.add(newValue) }
        }

        initStyle(StageStyle.TRANSPARENT)
        title = property.title.get()
        scene = Scene(baseBox).apply {
            fill = null
            stylesheets += "cloudtools_res/cloudbutton.css"
        }
    }

    private fun getButtonBox(messageButton: Array<String>): HBox {
        val buttons = Array(messageButton.size) {
            Button(messageButton[it]).apply {
                setOnAction { _ ->
                    properties["index"] = it
                    if (!isCanClose.value) return@setOnAction
                    index.set(it)
                    thread {
//                        (if (theme.animation) ZoomOutUp(baseBox) else BounceOutUp(baseBox)).play()
//                        Thread.sleep(1000)
                        Platform.runLater { close() }
                    }
                }
            }
        }

        return HBox(10.0).apply {
            alignment = Pos.CENTER_RIGHT
            padding = Insets(0.0,10.0,10.0,10.0)
            children.addAll(*buttons)
        }
    }
}