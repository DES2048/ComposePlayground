package yae.composeplayground.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun TextFieldDemo() {
    var name = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Name is ${name.value}")
        OutlinedTextField(value=name.value, onValueChange = {name.value = it},
            placeholder = {Text(text="Type your name")})
    }

}