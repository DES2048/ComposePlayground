package yae.composeplayground.composables

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDismissDemo() {
    val context = LocalContext.current

    var dismissState = rememberDismissState(
        confirmValueChange = {value->
            if(value == DismissValue.DismissedToStart) {
                makeToast(context, "Dismissed to start")
                true
            } else if (value ==DismissValue.DismissedToEnd) {
                makeToast(context, "Dismissed to end")
                true
            }
            false
        },
        positionalThreshold = {dist->
            //Log.d("DISMISS", "total distance is $dist")
            dist / 2
        }
    )

    var thresholdState by remember {
        mutableStateOf(ThresholdState.NOT_REACHED)
    }

    val thresholdReached by remember {
        derivedStateOf {
            dismissState.progress != 1.0f
        }
    }

    thresholdState = if (thresholdReached) {
        ThresholdState.REACHED
    } else if (thresholdState == ThresholdState.REACHED
        && !thresholdReached) {
        ThresholdState.BACK
    } else {
        ThresholdState.NOT_REACHED
    }
    LaunchedEffect(key1 = thresholdState) {
        if (thresholdState == ThresholdState.REACHED || thresholdState == ThresholdState.BACK) {
            vibrateOnce(context)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = thresholdState.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.background
                )
                .padding(10.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Threshold ${if (thresholdReached) "reached" else "not reached" }",
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.background
                )
                .padding(10.dp),
            color = MaterialTheme.colorScheme.onBackground
        )

        Box(modifier = Modifier
            .fillMaxSize()
            .height(IntrinsicSize.Min),
            contentAlignment = Alignment.Center) {
            SwipeToDismiss(
                state = dismissState ,
                background = {
                    dismissState.dismissDirection ?: return@SwipeToDismiss

                    var bgColor = Color.Red
                    var alignment = Alignment.CenterEnd
                    if (dismissState.dismissDirection == DismissDirection.StartToEnd) {
                        bgColor = Color.Green
                        alignment = Alignment.CenterStart
                    }

                    Box(modifier = Modifier
                        .background(bgColor)
                        .fillMaxSize(),
                        contentAlignment = alignment) {
                        Text(text = "DISMISS")
                    }
                } ,
                dismissContent = {
                    Box(modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()) {
                        Text(text = "Dismissed content",
                            modifier = Modifier.padding(10.dp),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        }
    }
}

enum class ThresholdState {
    NOT_REACHED,
    REACHED,
    BACK
}

fun makeToast(context:Context, message:String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG)
        .show()
}

fun vibrateOnce(context: Context) {
    // Vibration Controller
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    vibrator.cancel()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val effect = VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.vibrate(effect)
    } else {
        // Handling vibrations for devices below Android 8.0
        vibrator.vibrate(100)
    }
}