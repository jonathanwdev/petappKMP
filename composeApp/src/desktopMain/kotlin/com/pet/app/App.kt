import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pet.app.presentation.navigation.PetNavHost
import org.koin.compose.KoinContext

@Composable
fun PetAppDesktop(modifier: Modifier = Modifier) {
    KoinContext {
        PetNavHost()
    }
}