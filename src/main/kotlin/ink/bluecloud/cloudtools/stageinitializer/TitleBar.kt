package ink.bluecloud.cloudtools.stageinitializer

import ink.bluecloud.css.ElementButton
import ink.bluecloud.css.theme
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.stage.Stage

class TitleBar(title: String, primaryStage: Stage, onCloseRequest: ((ActionEvent) -> Unit)? = null) : BorderPane() {

    /**
     * 定义偏移量，用于处理窗口移动
     */
    private var xOffset = 0.0
    private var yOffset = 0.0

    init {
        left = HBox().apply {
            children += Label(title).apply {
                style = "-fx-font-size: 15px;"
            }
        }

        right = HBox(5.0).apply {
            children += Button("-").apply {
                setOnAction {
                    primaryStage.isIconified = true
                }
                theme(ElementButton.greenButton)
            }

            children += Button("+").apply {
                setOnAction {
                    primaryStage.isMaximized = (!primaryStage.isMaximized).apply {
                        if (this) {
                            (scene.root as Pane).run {
                                userData = padding
                                padding = Insets(0.0)
                            }

                            enableCustomizeStageListener = false
                        } else {
                            (scene.root as Pane).run {
                                padding = (userData as? Insets)?: Insets(10.0)
                            }
                            enableCustomizeStageListener = true
                        }
                    }
                }
                theme(ElementButton.orangeButton)
            }

            children += Button("x").apply {
                setOnAction {
                    onCloseRequest?.run {
                        onCloseRequest(it)
                    }?: Platform.exit()
                }
                theme(ElementButton.redButton)
            }
        }

        addEventHandler(MouseEvent.MOUSE_PRESSED) {
            xOffset = it.sceneX
            yOffset = it.sceneY
        }

        addEventHandler(MouseEvent.MOUSE_DRAGGED) {
            primaryStage.x = it.screenX - xOffset
            primaryStage.y = it.screenY - yOffset
        }
        padding = Insets(10.0,10.0,0.0,10.0)
    }
}