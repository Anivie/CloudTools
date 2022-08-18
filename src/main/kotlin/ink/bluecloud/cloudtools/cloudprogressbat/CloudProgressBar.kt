package ink.bluecloud.cloudtools.cloudprogressbat

import ink.bluecloud.cloudtools.CLOUD_INTERPOLATOR
import javafx.animation.Interpolator
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.control.ProgressBar
import javafx.util.Duration

private val mySmoothProgress = SimpleDoubleProperty()
private val interpolator: Interpolator = CLOUD_INTERPOLATOR
private lateinit var timeline: Timeline

val ProgressBar.smoothProgress
    get() = mySmoothProgress.apply {
        timeline = Timeline(KeyFrame(Duration.millis(100.0), KeyValue(progressProperty(),0.0, interpolator)))
        addListener { _, _, newValue ->
            timeline.run {
                keyFrames[0] = KeyFrame(Duration.millis(100.0), KeyValue(progressProperty(),newValue, interpolator))
                play()
            }
            timeline.setOnFinished {
                if (newValue == 1.0) progress = 1.0
            }
        }
    }
