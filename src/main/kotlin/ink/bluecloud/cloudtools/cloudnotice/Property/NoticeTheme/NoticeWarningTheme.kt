package ink.bluecloud.cloudtools.cloudnotice.Property.NoticeTheme

import javafx.scene.paint.Color

class NoticeWarningTheme: NoticeTheme() {
    init {
        backgroundColor.set(Color.web("#FDF6EC"))
        borderColor.set(Color.web("#FBEEDC"))
        messageColor.set(Color.web("#E6A23C"))
    }
}