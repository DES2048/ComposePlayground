package yae.composeplayground.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import yae.composeplayground.models.SimplePerson

@Composable
fun PersonInfoCard(person:SimplePerson) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),

        elevation = CardDefaults.elevatedCardElevation(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(5.dp)
            ) {
                Row {
                    Text(text = "Имя:")
                }
                Row {
                    Text(text = "Email:")
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Row {
                    Text(text = person.name)
                }
                Row {
                    Text(text=person.email)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonInfoCardPreview() {
    PersonInfoCard(person = SimplePerson(name="Олег", email="electrochange@mail.ru"))
}