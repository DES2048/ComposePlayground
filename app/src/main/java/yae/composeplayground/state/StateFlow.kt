package yae.composeplayground.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StateFlowViewModel:ViewModel() {

    private val _counterStateFlow = MutableStateFlow(0)
    val counterStateFlow:StateFlow<Int> = _counterStateFlow

    fun increment() {
        viewModelScope.launch {
            _counterStateFlow.value++
        }
    }

    fun decrement() {
        _counterStateFlow.value--
    }
}