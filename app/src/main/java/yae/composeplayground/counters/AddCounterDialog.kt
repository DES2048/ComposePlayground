package yae.composeplayground.counters

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Dialog

@Composable
fun AddCounterDialog(onClose:()->Unit, onAdd:(CounterAddModel)->Unit) {
    var titleText = remember {
        mutableStateOf("")
    }
    Dialog(onDismissRequest = onClose) {
        Card {
            OutlinedTextField(value = titleText.value, onValueChange = {titleText.value=it} )
            Row {
                Button(onClick = {onAdd(CounterAddModel(titleText.value))}) {
                    Text(text = "Add")
                }
                Button(onClick = onClose ) {
                    Text(text = "Close")
                }
            }
        }
    }
}