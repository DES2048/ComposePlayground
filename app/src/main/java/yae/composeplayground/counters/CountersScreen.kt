package yae.composeplayground.counters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun CountersScreen(viewModel: CountersScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    var showAddCounterDialog = remember {
        mutableStateOf(false)
    }

    Scaffold(
        // show top app bar
        topBar = {
                 CountersAppBar(titleText = "Counters")
        },
        // show fab for add counter
        floatingActionButton = {
            CountersFab(onClick = {showAddCounterDialog.value=true})
        }
            ){ innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            // show list
            CountersList(
                counters = viewModel.counters,
                onIncrement = {id->viewModel.incrementCounter(id)},
                onClear = {id->viewModel.clearCounter(id)}
            )
            // Add counter dialog
            if(showAddCounterDialog.value) {
                AddCounterDialog(onClose = { showAddCounterDialog.value=false},
                    onAdd = {
                        viewModel.addCounter(it)
                        showAddCounterDialog.value = false
                    })
            }
        }

    }



}