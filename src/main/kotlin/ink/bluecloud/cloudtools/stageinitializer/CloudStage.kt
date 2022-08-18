package ink.bluecloud.cloudtools.stageinitializer

import ink.bluecloud.cloudtools.stageinitializer.Orientation.*
import javafx.geometry.Insets
import javafx.scene.Cursor
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.effect.BlurType
import javafx.scene.effect.DropShadow
import javafx.scene.input.MouseEvent
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.StageStyle

private var originX = 0.0
private var originY = 0.0
private var xOffset = 0.0
private var yOffset = 0.0

private var originW = 0.0
private var originH = 0.0

private var moving = NONE
var enableCustomizeStageListener = true

enum class Orientation { NONE,E,W,S,N,NW,NE,SW,SE }

fun Stage.initCustomizeStage(pixel: Number = 5.0) = this.apply {
    val pixel = pixel.toDouble()
    addEventFilter(MouseEvent.MOUSE_MOVED) {
        if (!enableCustomizeStageListener) return@addEventFilter
        if ((it.x > pixel) && (it.x < width - pixel) && (it.y > pixel) && (it.y < height - pixel)) {
            //还原
            move(NONE)
            cursor(Cursor.DEFAULT)
        } else if ((it.x <= pixel) && (it.y > pixel) && (it.y < height - pixel)) {
            //左边
            move(W)
            cursor(Cursor.W_RESIZE)
        } else if ((it.x >= width - pixel) && (it.y > pixel) && (it.y < height - pixel)) {
            //右边
            move(E)
            cursor(Cursor.E_RESIZE)
        } else if ((it.x >= pixel) && (it.x <= width - pixel) && (it.y <= pixel)) {
            //上边
            move(N)
            cursor(Cursor.N_RESIZE)
        } else if ((it.y >= height - pixel) && (it.x <= width - pixel) && (it.x >= pixel)) {
            //下边
            move(S)
            cursor(Cursor.S_RESIZE)
        } else if ((it.x <= pixel) && (it.y <= pixel)) {
            //左上角
            move(NW)
            cursor(Cursor.NW_RESIZE)
        } else if ((it.x >= height - pixel) && (it.y <= pixel)) {
            //右上角
            move(NE)
            cursor(Cursor.NE_RESIZE)
        }  else if ((it.x <= pixel) && (it.y >= height - pixel)) {
            //左下角
            move(SW)
            cursor(Cursor.SW_RESIZE)
        }  else if ((it.x >= width - pixel) && (it.y >= height - pixel)) {
            //右下角
            move(SE)
            cursor(Cursor.SE_RESIZE)
        }
    }

    addEventFilter(MouseEvent.MOUSE_PRESSED) {
        if (!enableCustomizeStageListener) {
            return@addEventFilter
        }

        if ((moving != NONE) || (moving != N) || (moving != S)) {
            originW = width
            it.screenX.run {
                originX = this
                xOffset = this
            }
        }

        if ((moving != NONE) || (moving != W) || (moving != E)) {
            originH = height
            it.screenY.run {
                yOffset = this
                originY = this
            }
        }
    }

    addEventFilter(MouseEvent.MOUSE_DRAGGED) {
        if (!enableCustomizeStageListener) return@addEventFilter

        if ((moving == SE) || (moving == E) || (moving == NE)) {
            width = originW + (it.screenX - xOffset)
        }

        if ((moving == SE) || (moving == S) || (moving == SW)) {
            height = originH + (it.screenY - yOffset)
        }

        if ((moving == W) || (moving == NW) || (moving == SW)) {
            width = originW - (it.screenX - xOffset)
            x = originX + (it.screenX - xOffset)
        }

        if ((moving == N) || (moving == NW) || (moving == NE)) {
            height = originH - (it.screenY - yOffset)
            y = originY + (it.screenY - yOffset)
        }
    }

    sceneProperty().addListener { _, _, newValue ->
        newValue.fill = null
    }

    initStyle(StageStyle.TRANSPARENT)
}

fun Stage.initCustomizeStageAndRoot(pixel: Number = 5.0) = this.apply {
    val pixel = pixel.toDouble()
    addEventFilter(MouseEvent.MOUSE_MOVED) {
        if (!enableCustomizeStageListener) return@addEventFilter
        if ((it.x > pixel) && (it.x < width - pixel) && (it.y > pixel) && (it.y < height - pixel)) {
            //还原
            move(NONE)
            cursor(Cursor.DEFAULT)
        } else if ((it.x <= pixel) && (it.y > pixel) && (it.y < height - pixel)) {
            //左边
            move(W)
            cursor(Cursor.W_RESIZE)
        } else if ((it.x >= width - pixel) && (it.y > pixel) && (it.y < height - pixel)) {
            //右边
            move(E)
            cursor(Cursor.E_RESIZE)
        } else if ((it.x >= pixel) && (it.x <= width - pixel) && (it.y <= pixel)) {
            //上边
            move(N)
            cursor(Cursor.N_RESIZE)
        } else if ((it.y >= height - pixel) && (it.x <= width - pixel) && (it.x >= pixel)) {
            //下边
            move(S)
            cursor(Cursor.S_RESIZE)
        } else if ((it.x <= pixel) && (it.y <= pixel)) {
            //左上角
            move(NW)
            cursor(Cursor.NW_RESIZE)
        } else if ((it.x >= height - pixel) && (it.y <= pixel)) {
            //右上角
            move(NE)
            cursor(Cursor.NE_RESIZE)
        }  else if ((it.x <= pixel) && (it.y >= height - pixel)) {
            //左下角
            move(SW)
            cursor(Cursor.SW_RESIZE)
        }  else if ((it.x >= width - pixel) && (it.y >= height - pixel)) {
            //右下角
            move(SE)
            cursor(Cursor.SE_RESIZE)
        }
    }

    addEventFilter(MouseEvent.MOUSE_PRESSED) {
        if (!enableCustomizeStageListener) {
            return@addEventFilter
        }

        if ((moving != NONE) || (moving != N) || (moving != S)) {
            originW = width
            it.screenX.run {
                originX = this
                xOffset = this
            }
        }

        if ((moving != NONE) || (moving != W) || (moving != E)) {
            originH = height
            it.screenY.run {
                yOffset = this
                originY = this
            }
        }
    }

    addEventFilter(MouseEvent.MOUSE_DRAGGED) {
        if (!enableCustomizeStageListener) return@addEventFilter

        if ((moving == SE) || (moving == E) || (moving == NE)) {
            width = originW + (it.screenX - xOffset)
        }

        if ((moving == SE) || (moving == S) || (moving == SW)) {
            height = originH + (it.screenY - yOffset)
        }

        if ((moving == W) || (moving == NW) || (moving == SW)) {
            width = originW - (it.screenX - xOffset)
            x = originX + (it.screenX - xOffset)
        }

        if ((moving == N) || (moving == NW) || (moving == NE)) {
            height = originH - (it.screenY - yOffset)
            y = originY + (it.screenY - yOffset)
        }
    }

    sceneProperty().addListener { _, _, newValue ->
        newValue.fill = null
    }

    val generateRoot: Parent.(Scene) -> Unit = {
        if (userData != "cloud_root") {
            effect = DropShadow(10.0,2.0,1.0, Color(0.0, 0.0, 0.0, 0.35)).apply {
                blurType = BlurType.GAUSSIAN
            }

            style = """
                -fx-background-color: white;
                -fx-background-radius: 10;
                """.trimIndent()

            if (root.children.size != 1) {
                root.children += this
            } else {
                root.children[0] = this
            }
            it.root = root
        }
    }

    sceneProperty().addListener s@{ _, _, scene ->
        scene.root.run {
            generateRoot(scene)
        }

        scene.rootProperty().addListener { _, _, newValue ->
            newValue.generateRoot(scene)
        }
    }

    initStyle(StageStyle.TRANSPARENT)
}

private fun Stage.move(orientation: Orientation){
    if (moving != orientation) {
        moving = orientation
    }
}

private fun Stage.cursor(cursor: Cursor) {
    if (scene.cursor != cursor) {
        scene.cursor = cursor
    }
}

private val root = StackPane().apply root@{
    userData = "cloud_root"
    style = """
        -fx-background-color: transparent;
        -fx-padding: 10;
    """.trimIndent()
}