package yae.composeplayground.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// побалуемся с Text

@Composable
fun DemoText() {
    Column(modifier = Modifier.fillMaxSize()) {
        // простой текст, по умолчанию wrap content
        Text(text="Simple text", modifier = Modifier.background(color= Color.Gray))

        // сделаем текст на всю ширину
        Text(text="fill max width text", modifier = Modifier.fillMaxWidth()
            .background(color = Color.Gray))

        // Добавим немного отступов
        Text(text="padding text sample", modifier = Modifier.fillMaxWidth()
            .background(color = Color.Gray).padding(5.dp))

    }
}