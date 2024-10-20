package yae.composeplayground.codelabs.basicstatecodelab

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel:ViewModel() {
    // Для того чтобы composer отслеживал изменения в списке, такие как добавление
    // удаление и замена элементов, определим стэйт как MutableStateList
    private val _tasks = getWellnessTasks().toListItems().toMutableStateList()

    // другой нормальной практикой является геттер с типом List<T>, который представляет
    // собой коллекцию только для чтения элементов, а для изменения этой коллекции используются
    // методы ViewModel
    val tasks: List<WellnessTaskItem>
        get() = _tasks

    fun removeTask(id:Int) = _tasks.removeIf {t->t.id === id}

    fun selectTask(id:Int,selected:Boolean) {
        val idx = _tasks.indexOfFirst { task->task.id ===id }
        if (idx >= 0) {
            _tasks[idx] = _tasks[idx].copy(selected = selected)
        }

    }
}