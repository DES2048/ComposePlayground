package yae.composeplayground.codelabs.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun StatelessCounter(counter:Int, increaseText:String="+1", onIncrease:()->Unit, onClear:()->Unit) {
    Column(
        modifier= Modifier.padding(5.dp)
    ){
        Text(text = "You had drunk $counter glasses of water")
        Row {
            Button(onClick = onIncrease, modifier=Modifier.weight(0.5f)) {
                Text(text=increaseText)
            }
            Button(onClick = onClear, modifier=Modifier.weight(0.5f)) {
                Text(text="Clear")
            }
        }

    }
}