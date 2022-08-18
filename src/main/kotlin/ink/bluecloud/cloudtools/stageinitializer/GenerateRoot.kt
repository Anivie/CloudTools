package ink.bluecloud.cloudtools.stageinitializer

import javafx.geometry.Insets
import javafx.scene.effect.BlurType
import javafx.scene.effect.DropShadow
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color

fun Pane.generateTransparentRoot(width: Number? = null, height: Number? = null) = StackPane().apply root@{
    children += this@generateTransparentRoot.apply {
        effect = DropShadow(10.0,2.0,1.0, Color(0.0, 0.0, 0.0, 0.35)).apply {
            blurType = BlurType.GAUSSIAN
        }

        style = """
                -fx-background-color: white;
                -fx-background-radius: 10;
            """.trimIndent()
    }

    width?.toDouble()?.run {
        prefWidth = this
    }
    height?.toDouble()?.run {
        prefHeight = this
    }
    style = "-fx-background-color: transparent;"
    padding = Insets(10.0)
}