package ink.bluecloud.cloudtools.cloudmessagebox.Property

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.image.Image
import javafx.scene.layout.Pane
import javafx.stage.Stage

/**
 * @author Anivie
 */
data class MessageBoxProperty(
    val title: SimpleStringProperty = SimpleStringProperty(),
    val message: SimpleStringProperty = SimpleStringProperty(),
    val messagePane: SimpleObjectProperty<Pane> = SimpleObjectProperty<Pane>(null),

    val width:SimpleDoubleProperty = SimpleDoubleProperty(500.0),
    val height:SimpleDoubleProperty = SimpleDoubleProperty(200.0),
    val radios:SimpleDoubleProperty = SimpleDoubleProperty(10.0),


    val parentStage: SimpleObjectProperty<Stage> = SimpleObjectProperty(),
    val icon: SimpleObjectProperty<Image> = SimpleObjectProperty(),
    val stageCanDrag: SimpleBooleanProperty = SimpleBooleanProperty(true)
)