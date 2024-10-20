package yae.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import yae.composeplayground.codelabs.basicstatecodelab.WellnessScreen
import yae.composeplayground.composables.LazyColumnDemo
import yae.composeplayground.composables.PersonInfoCard
import yae.composeplayground.composables.SwipeToDismissDemo
import yae.composeplayground.composables.TextFieldDemo
import yae.composeplayground.counters.CountersScreen
import yae.composeplayground.models.SimplePerson
import yae.composeplayground.navigation.NavigationDemo
import yae.composeplayground.state.StateFlowUI
import yae.composeplayground.ui.theme.ComposePlaygroundTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                //CountersScreen()
                //SwipeToDismissDemo()
                StateFlowUI()
            }
        }
    }

    private fun checkExternalStoragePermissions() {
    }
}

@Composable
fun CardDemo() {
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {

    }
}

@Composable
fun NameRow(name:String) {
    Row(
        modifier=Modifier.fillMaxWidth()
    ) {
        Text(text = "Name:")
        Text(text = name)
    }
}

@Composable
fun InfoRow(info:String) {
    Row(
        modifier= Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text=info)
        Text(text="Menu here")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, device = "id:Nexus 4")
@Composable
fun GreetingPreview() {
    ComposePlaygroundTheme {
        Greeting("Android")
    }
}