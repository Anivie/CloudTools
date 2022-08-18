package ink.bluecloud.cloudtools

import javafx.animation.Interpolator
import javafx.animation.RotateTransition
import javafx.animation.Timeline
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.scene.layout.Region
import javafx.scene.paint.Color
import javafx.scene.shape.SVGPath
import javafx.util.Duration

fun Button.loading() {
    if (!properties.containsKey("cloud-tools-loading-button")) {
        if (!stylesheets.contains("cloudtools_res/loadingbutton.css")) stylesheets += "cloudtools_res/loadingbutton.css"

        graphic = Label("\uE607").apply {
            styleClass += "loading-label"

            this@loading.properties["cloud-tools-loading-transition"] = RotateTransition(Duration.seconds(1.0),this).apply {
                fromAngle = 0.0
                toAngle = 360.0
                cycleCount = Timeline.INDEFINITE
                interpolator = Interpolator.LINEAR
                play()
            }
        }

        properties["cloud-tools-loading-button"] = true
    } else {
        graphic = null
        (properties["cloud-tools-loading-transition"]as RotateTransition).stop()
        properties.remove("cloud-tools-loading-button")
        properties.remove("cloud-tools-loading-transition")
    }
}