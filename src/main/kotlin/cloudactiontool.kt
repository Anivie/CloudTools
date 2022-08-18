import javafx.event.ActionEvent
import javafx.scene.Node
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Synchronized
@Suppress("UNCHECKED_CAST")
fun <T : Node> ActionEvent.node():T {
    return if (source is Node) {
        source as T
    } else {
        throw IllegalArgumentException("不支持的操作：传入的${source}不是Node的一员！")
    }
}

fun runIO(block: suspend CoroutineScope.() -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        block()
    }
}
