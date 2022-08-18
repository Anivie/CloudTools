package ink.bluecloud.cloudtools.cloudmessagebox.Property.Theme

import javafx.beans.property.SimpleDoubleProperty

/**
 * @author Anivie
 */
data class MessageBoxTheme (
    val backgroundColor: String = "white",
    val buttonColor: String = "#FFFEEF",
    val titleColor: String = "#5C5C5C",
    val textColor: String = "#7D7D7D",


    val titleSize: SimpleDoubleProperty = SimpleDoubleProperty(20.0),
    val textSize: SimpleDoubleProperty = SimpleDoubleProperty(15.0),

    /**
     * true for Zoom,false for BounCe
     */
    val animation:Boolean = true,
    val icons:Double = 0.0
)