package yae.composeplayground.counters

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class CountersScreenViewModel:ViewModel() {
    // list all counters
    private var lastInsertedId = 0
    private val _counters = emptyList<Counter>().toMutableStateList()
    val counters:List<Counter>
        get() = _counters

    // add new counter
    fun addCounter(counter:CounterAddModel):Int {
        lastInsertedId++
        _counters.add(
            Counter(id=++lastInsertedId, title=counter.title, count=counter.count)
        )
        return lastInsertedId
    }
    // remove counter
    fun removeCounter(id:Int) = _counters.removeIf { c->c.id ===id}
    //increment counter
    fun incrementCounter(id:Int) {
        _counters.indexOfFirst { c->c.id===id }.apply {
            if(this >=0) {
                _counters[this] = _counters[this].copyIncremented()
        } }
    }
    // clear counter
    fun clearCounter(id:Int) {
        _counters.indexOfFirst { c->c.id===id }.apply {
            if(this >=0) {
                _counters[this] = _counters[this].copy(count=0)
            } }
    }
    // decrement counter
}