package yae.composeplayground.state

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collect

@Composable
fun StateFlowUI(vm:StateFlowViewModel = viewModel()) {

    val counter by vm.counterStateFlow.collectAsState()

    var changes by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(key1 = Unit) {
        vm.counterStateFlow.collect() {
            changes++
        }
    }

    Row {
        Button(onClick = { vm.decrement() }) {
            Text(text = "-1")
        }
        Text(text = "$counter")
        Text(text = "changes: $changes")
        Button(onClick = { vm.increment() }) {
            Text(text = "+1")
        }
    }
}