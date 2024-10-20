package yae.composeplayground.codelabs.basicstatecodelab

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

data class WellnessTaskItem(val id:Int, val name:String, val selected:Boolean) {

}

fun WellnessTask.ToListItem() = WellnessTaskItem(id, name, false)
fun List<WellnessTask>.toListItems() = this.map { t->t.ToListItem() }