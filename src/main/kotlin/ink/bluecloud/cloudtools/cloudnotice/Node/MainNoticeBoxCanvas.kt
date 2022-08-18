package ink.bluecloud.cloudtools.cloudnotice.Node

import ink.bluecloud.cloudtools.cloudnotice.Node.Painter.NoticeBoxPainter
import ink.bluecloud.cloudtools.cloudnotice.Property.NoticeProperty
import ink.bluecloud.cloudtools.cloudnotice.Property.NoticeTheme.NoticeTheme
import javafx.scene.canvas.Canvas

class MainNoticeBoxCanvas :Canvas(){
    fun paint(property: NoticeProperty, theme: NoticeTheme){
        width = property.width.get()
        height = property.height.get()
        NoticeBoxPainter(graphicsContext2D, property, theme).run {
            drawBackground()
            drawBorder()
            drawText()
        }
    }
}