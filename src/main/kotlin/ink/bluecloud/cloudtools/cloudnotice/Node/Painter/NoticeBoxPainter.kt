package ink.bluecloud.cloudtools.cloudnotice.Node.Painter

import ink.bluecloud.cloudtools.cloudnotice.Property.NoticeProperty
import ink.bluecloud.cloudtools.cloudnotice.Property.NoticeTheme.NoticeTheme
import javafx.geometry.VPos
import javafx.scene.canvas.GraphicsContext
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment

class NoticeBoxPainter(
    private val context: GraphicsContext,
    val property: NoticeProperty,
    private val theme: NoticeTheme
) {

    fun drawBackground():Unit = context.run {
        fill = theme.backgroundColor.get()
        fillRoundRect(0.0,0.0,property.width.get(),property.height.get(),theme.radio.get(),theme.radio.get())
    }

    fun drawBorder():Unit = context.run {
        stroke = theme.borderColor.get()
        lineWidth = theme.borderWidth.get()
        strokeRoundRect(
            theme.borderWidth.get() / 2,
            theme.borderWidth.get() / 2,
            property.width.get() - theme.borderWidth.get(),
            property.height.get() - theme.borderWidth.get(),
            theme.radio.get(),
            theme.radio.get()
        )
    }

    fun drawText():Unit = context.run {
        fill = theme.messageColor.get()
        textAlign = TextAlignment.LEFT
        textBaseline = VPos.CENTER

        font = theme.iconFont
        fillText(property.icon.get(), theme.upperAndLowerWidth.get(),property.height.get() / 2)

        font = theme.font ?: Font.font(17.0)
        fillText(property.message.get(), theme.upperAndLowerWidth.get() + 20.0,property.height.get() / 2)
    }

}