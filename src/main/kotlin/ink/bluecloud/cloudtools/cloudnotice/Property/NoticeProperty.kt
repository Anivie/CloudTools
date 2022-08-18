package ink.bluecloud.cloudtools.cloudnotice.Property

import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.stage.Window

data class NoticeProperty(
    val type: NoticeType = NoticeType.Message,
    val autoCloseable: Boolean = true,

    val icon: SimpleStringProperty = SimpleStringProperty(),
    val message: SimpleStringProperty = SimpleStringProperty(),

    val width:SimpleDoubleProperty = SimpleDoubleProperty(),
    val height:SimpleDoubleProperty = SimpleDoubleProperty(),

    val positionX:SimpleDoubleProperty = SimpleDoubleProperty(),
    val positionY:SimpleDoubleProperty = SimpleDoubleProperty(),

    val window: SimpleObjectProperty<Window> = SimpleObjectProperty(),

    val titleBarHeight:SimpleDoubleProperty = SimpleDoubleProperty(30.0),

    /**
     * 动画播放时长
     */
    val animationTIme: SimpleDoubleProperty = SimpleDoubleProperty(1000.0),

    /**
     * 浮动条与窗口的距离
     */
    val floatingDistance:SimpleDoubleProperty = SimpleDoubleProperty(30.0)
)

enum class NoticeType {
    Right,Message,Warning,Error
}