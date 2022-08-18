package ink.bluecloud.cloudtools.cloudslider

import javafx.beans.binding.Bindings
import javafx.geometry.Pos
import javafx.scene.control.Slider
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class CloudSlider(min:Number, max: Number, initValue: Number): StackPane() {
    init {
        val slider:Slider
        val track: Rectangle
        val actionBar: Rectangle

        children += Rectangle().apply {
            fill = Color.rgb(235,238,245,1.0)
            track = this
        }
        children += Rectangle().apply {
            fill = Color.rgb(64,158,255,1.0)
            actionBar = this
        }
        children += Slider(min.toDouble(),max.toDouble(),initValue.toDouble()).apply {
            slider = this
            track.widthProperty().bind(widthProperty())
            track.heightProperty().bind(Bindings.createDoubleBinding({
                height / 2.0
            },heightProperty()))
            actionBar.heightProperty().bind(Bindings.createDoubleBinding({
                height / 2.0
            },heightProperty()))
        }

        track.apply {
            heightProperty().addListener { _, _, newValue ->
                arcHeight = newValue.toDouble()
                arcWidth = newValue.toDouble()

                actionBar.arcHeight = newValue.toDouble()
                actionBar.arcWidth = newValue.toDouble()
            }
        }

        slider.valueProperty().addListener { _, _, newValue ->
            actionBar.width = (newValue.toDouble() / max.toDouble()) * track.width
        }

        alignment = Pos.CENTER_LEFT
        stylesheets += "cloudtools_res/CloudSlider.css"
    }
}

fun Pane.cloudSlider(min:Number, max: Number, initValue: Number) {
    children += CloudSlider(min, max, initValue)
}