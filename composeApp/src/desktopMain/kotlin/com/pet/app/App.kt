import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.KoinContext

@Composable
fun PetAppDesktop(modifier: Modifier = Modifier) {
    KoinContext {
        Text("Hello Desktop")
    }
}