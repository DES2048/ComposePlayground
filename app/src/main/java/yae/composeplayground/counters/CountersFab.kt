package yae.composeplayground.counters

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun CountersFab(onClick:()->Unit) {
    ExtendedFloatingActionButton(
        onClick = onClick
    ) {
        Icon(Icons.Default.Add, contentDescription = "add")
    }
}