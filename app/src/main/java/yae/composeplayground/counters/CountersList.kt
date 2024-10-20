package yae.composeplayground.counters

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CountersList(counters:List<Counter>, onIncrement: (Int) -> Unit, onClear: (Int) -> Unit,
                 state:LazyListState= rememberLazyListState()) {
    LazyColumn(state=state, modifier = Modifier.fillMaxSize()) {
        items(counters, key = {it.id}) {
            CountersListItem(counter = it, onIncrement = onIncrement, onClear=onClear)
        }
    }
}

@Composable
fun CountersListItem(counter:Counter, onIncrement:(Int)->Unit, onClear:(Int)->Unit) {
    Row(modifier= Modifier
        .fillMaxWidth()
        .padding(5.dp)) {
        Text(text = counter.count.toString(), fontWeight = FontWeight.SemiBold)
        Button(onClick = { onIncrement(counter.id) }) {
            Text(text = "+1")
        }
        Button(onClick = { onClear(counter.id) }) {
            Text(text = "Clear")
        }
    }

}