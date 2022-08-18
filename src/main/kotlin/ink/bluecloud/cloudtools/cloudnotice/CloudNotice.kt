package ink.bluecloud.cloudtools.cloudnotice

import ink.bluecloud.cloudtools.CLOUD_INTERPOLATOR
import ink.bluecloud.cloudtools.cloudnotice.Node.MainNoticeBoxCanvas
import ink.bluecloud.cloudtools.cloudnotice.Property.NoticeProperty
import ink.bluecloud.cloudtools.cloudnotice.Property.NoticeTheme.*
import ink.bluecloud.cloudtools.cloudnotice.Property.NoticeType
import javafx.animation.Interpolator
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.application.Platform
import javafx.scene.text.Text
import javafx.stage.Popup
import javafx.stage.Window
import javafx.stage.WindowEvent
import javafx.util.Duration
import kotlin.concurrent.thread

class CloudNotice(
    noticeType: NoticeType,
    message: String,
    window: Window,

    floatingDistance:Number? = null,
    animationTIme:Number? = null,

    width: Double? = null,
    height: Double? = null,
    aliveTime:Number = 2000,
    val property: NoticeProperty = NoticeProperty(),
) {
    private val popup = Popup()
    private val INTERPOLATOR = CLOUD_INTERPOLATOR
    
    
    init {
        floatingDistance?.run { property.floatingDistance.value = this.toDouble() }
        animationTIme?.run { property.animationTIme.value = this.toDouble() }

        popup.content.add(MainNoticeBoxCanvas().apply {
            paintNotice(
                width = width,
                noticeBoxCanvas = this,
                theme = when(noticeType){
                    NoticeType.Right -> NoticeRightTheme()
                    NoticeType.Message -> NoticeMessageTheme()
                    NoticeType.Warning -> NoticeWarningTheme()
                    NoticeType.Error -> NoticeErrorTheme()
                },
                height = height,
                message = message,
                noticeType = noticeType,
                window = window
            )
        })

        property.positionY.addListener { _, _, newValue -> popup.y = newValue.toDouble() }
        popup.setOnShowing {
            showingAnimation()
            timerOff(aliveTime.toLong())
        }
    }

    private fun timerOff(aliveTime: Long) {
        thread {
            Thread.sleep(aliveTime)
            Platform.runLater {
                Timeline(
                    144.0,
                    KeyFrame(
                        Duration.millis(property.animationTIme.value),
                        KeyValue(
                            property.positionY,
                            property.positionY.get() - 50,
                            INTERPOLATOR
                        )
                    ), KeyFrame(
                        Duration.millis(property.animationTIme.value),
                        KeyValue(
                            popup.content[0].opacityProperty(),
                            0,
                            INTERPOLATOR
                        )
                    )
                ).play()
            }
            Thread.sleep(property.animationTIme.value.toLong())
            Platform.runLater { popup.hide() }
        }
    }

    private fun showingAnimation() {
        Timeline(
            170.0,
            KeyFrame(
                Duration.millis(property.animationTIme.value),
                KeyValue(
                    property.positionY,
                    property.positionY.get() + 50,
                    INTERPOLATOR
                )
            ), KeyFrame(
                Duration.millis(property.animationTIme.value),
                KeyValue(
                    popup.content[0].opacityProperty(),
                    1,
                    INTERPOLATOR
                )
            )
        ).play()
    }

    private fun paintNotice(
        width: Double?,
        noticeBoxCanvas: MainNoticeBoxCanvas,
        theme: NoticeTheme,
        height: Double?,
        message: String,
        noticeType: NoticeType,
        window: Window
    ) {
        noticeBoxCanvas.paint(property.apply {
            width?.run { this@apply.width.set(width * theme.relativeWidth.get()) }

            window.run {
                this@apply.window.set(window)
                window.scene?.run {
                    this@apply.width.set(window.scene.width * theme.relativeWidth.get())
                    noticeBoxCanvas.paint(property,theme)
                }
                if (window.scene == null) {
                    window.addEventHandler(WindowEvent.WINDOW_SHOWN){
                        this@apply.width.set(window.scene.width * theme.relativeWidth.get())
                        noticeBoxCanvas.paint(property,theme)
                    }
                }
            }

            if (height != null) {
                this.height.set(height)
            } else {
                this.height.set((Text(message).apply { font = theme.iconFont }.boundsInParent.height) + theme.upperAndLowerWidth.get())
            }

            this.message.value = " $message"
            this.icon.value = when (noticeType) {
                NoticeType.Right -> "\uEBE5"
                NoticeType.Message -> "\uEBB7"
                NoticeType.Warning -> "\uEB65"
                NoticeType.Error -> "\uED1A"
            }
        }, theme)

        if (width != null && window == null) throw NullPointerException("您没有指定任何尺寸信息！stage和width不能同时为空！")
    }

    fun show() {
        bindPosition(property.window.get())
        popup.show(property.window.get())
    }

    private fun bindPosition(window: Window?) {
        if (window != null){
            popup.x = window.x + ((window.width / 2) - (property.width.get() / 2))
            popup.y = window.y + property.titleBarHeight.get() + property.floatingDistance.get() - 50.0

            property.positionX.set(popup.x)
            property.positionY.set(popup.y)
            window.apply {
                xProperty().addListener { _, _, newValue -> popup.x = newValue.toDouble() + ((window.width / 2) - (property.width.get() / 2)) }
                yProperty().addListener { _, _, newValue -> popup.y = newValue.toDouble() + property.titleBarHeight.get() + property.floatingDistance.get() }
            }
        } else {
            popup.x = property.positionX.get()
            popup.y = property.positionY.get()
        }
        property.positionX.addListener { _, _, newValue -> popup.x = newValue.toDouble() }
        property.positionY.addListener { _, _, newValue -> popup.y = newValue.toDouble() }
    }
}