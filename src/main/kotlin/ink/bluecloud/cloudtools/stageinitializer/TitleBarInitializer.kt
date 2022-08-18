package ink.bluecloud.cloudtools

import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import javafx.stage.Stage

/**
 * 定义偏移量，用于处理窗口移动
 */
private var xOffset = 0.0
private var yOffset = 0.0

fun Pane.initTitleBar(primaryStage: Stage) = this.apply {
    addEventHandler(MouseEvent.MOUSE_PRESSED) {
        xOffset = it.sceneX
        yOffset = it.sceneY
    }

    addEventHandler(MouseEvent.MOUSE_DRAGGED) {
        primaryStage.x = it.screenX - xOffset
        primaryStage.y = it.screenY - yOffset
    }
}