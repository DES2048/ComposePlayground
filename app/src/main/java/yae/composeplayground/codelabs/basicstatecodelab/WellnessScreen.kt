package yae.composeplayground.codelabs.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WellnessScreen(modifier:Modifier=Modifier, vm: WellnessViewModel = viewModel()) {

    Column(modifier= Modifier
        .fillMaxWidth()
        .padding(5.dp)) {

        WaterCounter(modifier=modifier)
        Spacer(modifier = Modifier.height(5.dp))

        WellnessTaskList(tasks = vm.tasks,
            onItemClose = {id->vm.removeTask(id)}, onItemSelect = {id, checked->vm.selectTask(id, checked)}
        )
    }

}