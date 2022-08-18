package ink.bluecloud.cloudtools.cloudnotice.Property.NoticeTheme

import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.paint.Color
import javafx.scene.text.Font

abstract class NoticeTheme(
    val font:Font? = null,

    /**
     * 边框宽度
     */
    val borderWidth:SimpleDoubleProperty = SimpleDoubleProperty(2.0),

    /**
     * 上下宽度
     */
    val upperAndLowerWidth:SimpleDoubleProperty = SimpleDoubleProperty(10.0),

    /**
     * 相对窗口的长度
     */
    val relativeWidth:SimpleDoubleProperty = SimpleDoubleProperty(0.8),

    /**
     * 圆角弧度
     */
    val radio:SimpleDoubleProperty = SimpleDoubleProperty(10.0),

    val backgroundColor:SimpleObjectProperty<Color> = SimpleObjectProperty(Color.web("#F0F9EB")),
    val borderColor:SimpleObjectProperty<Color> = SimpleObjectProperty(Color.web("#E2F3D9")),
    val messageColor:SimpleObjectProperty<Color> = SimpleObjectProperty(Color.web("#67C23A")),
) {
    val iconFont: Font = Font.loadFont(Thread.currentThread().contextClassLoader.getResourceAsStream("cloudtools_res/iconfont.ttf"),17.0)
}
