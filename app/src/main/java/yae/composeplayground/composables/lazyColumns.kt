package yae.composeplayground.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Composable
fun LazyColumnDemo() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(5.dp)
    ) {
        item {
            Text(text="HEADER", fontSize = 20.sp)
        }
        items(count=100) {
            Text(text="item no $it")
        }
    }
}

data class ColumnItem(val name:String, val age:Int)

class LazyColumnWithFlowViewModel:ViewModel() {
    private val listState = MutableStateFlow(emptyList<ColumnItem>())
    val listFlow = listState.asStateFlow()
    val list = listFlow.value.toMutableStateList()

    fun loadList() {
        listState.value = listOf(
            ColumnItem("Oleg", 38),
            ColumnItem("Olga", 65),
            ColumnItem("John", 25)
        )
    }
    fun makeOld(name:String) {
        listState.update { old->
            old.map { p->
                if (p.name == name) {
                    p.copy(age = 99)
                } else {
                    p
                }
            }
        }
    }
}

@Composable
fun LazyColumnWithFlow(vm:LazyColumnWithFlowViewModel = viewModel()) {
    val personList by vm.listFlow.collectAsState()

    LaunchedEffect(Unit) {
        vm.loadList()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(5.dp)
    ) {
        items(personList, key = {it.name}) { person ->
            Row(modifier = Modifier.fillMaxWidth()) {
                val onMakeOld = remember(key1=person.name) {
                    {vm.makeOld(person.name)}
                }
                Text(text=person.name)
                Text(text=person.age.toString())
                Button(onClick = {vm.makeOld(person.name)}) {
                    println("button for ${person.name} recomposed")
                    Text("Make old")
                }
            }
        }
    }
}