package yae.composeplayground.codelabs.basicstatecodelab

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun WellnessTaskList(tasks:List<WellnessTaskItem>,
                     onItemClose:(Int)->Unit,
                     onItemSelect:(Int, Boolean)->Unit,
                     modifier:Modifier=Modifier,
                     ) {
    LazyColumn(modifier=Modifier.fillMaxSize()) {
        items(tasks, key = {task->task.id}) { task ->

            WellnessTaskListItem(taskName = task.name, onClose = {onItemClose(task.id)},
                checked=task.selected,
                onChecked = {checked->onItemSelect(task.id, checked)})
        }
    }
}

@Composable
fun WellnessTaskListItem(taskName:String, onClose:()->Unit, modifier:Modifier=Modifier,
                         checked:Boolean=false, onChecked:(Boolean)->Unit,
) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(text = taskName, modifier= Modifier
            .weight(1f)
            .padding(5.dp) )
        Checkbox(checked = checked, onCheckedChange = onChecked)
        IconButton(onClick = onClose) {
            Icon(Icons.Default.Close, contentDescription = "Close")
        }
    }
}

fun getWellnessTasks() = List(15){
    WellnessTask(id=it,name="Task $it")
}