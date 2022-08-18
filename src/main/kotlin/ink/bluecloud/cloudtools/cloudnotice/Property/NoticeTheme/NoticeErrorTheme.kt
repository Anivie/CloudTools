package ink.bluecloud.cloudtools.cloudnotice.Property.NoticeTheme

import javafx.scene.paint.Color

class NoticeErrorTheme(): NoticeTheme() {
    init {
        backgroundColor.set(Color.web("#FEF0F0"))
        borderColor.set(Color.web("#FDE2E2"))
        messageColor.set(Color.web("#F56C6C"))
    }
}