package yae.composeplayground.dynappbarvm

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry

class TopAppBarViewModel : ViewModel() {

    var title by mutableStateOf<@Composable () -> Unit>({ }, referentialEqualityPolicy())

    var navigationIcon by mutableStateOf<@Composable () -> Unit>({ }, referentialEqualityPolicy())

    var actions by mutableStateOf<@Composable RowScope.() -> Unit>({ }, referentialEqualityPolicy())

}

@Composable
fun ProvideAppBarTitle(title: @Composable () -> Unit) {

    val viewModelStoreOwner = LocalViewModelStoreOwner.current
    (viewModelStoreOwner as? NavBackStackEntry)?.let { owner ->
        val viewModel: TopAppBarViewModel = viewModel(
            viewModelStoreOwner = owner,
            initializer = { TopAppBarViewModel() },
        )
        LaunchedEffect(title) {
            viewModel.title = title
        }
    }
}

@Composable
fun ProvideAppBarNavigationIcon(navigationIcon: @Composable () -> Unit) {

    val viewModelStoreOwner = LocalViewModelStoreOwner.current
    (viewModelStoreOwner as? NavBackStackEntry)?.let { owner ->
        val viewModel: TopAppBarViewModel = viewModel(
            viewModelStoreOwner = owner,
            initializer = { TopAppBarViewModel() },
        )
        LaunchedEffect(navigationIcon) {
            viewModel.navigationIcon = navigationIcon
        }
    }
}

@Composable
fun ProvideAppBarActions(actions: @Composable RowScope.() -> Unit) {

    val viewModelStoreOwner = LocalViewModelStoreOwner.current
    (viewModelStoreOwner as? NavBackStackEntry)?.let { owner ->
        val viewModel: TopAppBarViewModel = viewModel(
            viewModelStoreOwner = owner,
            initializer = { TopAppBarViewModel() },
        )
        LaunchedEffect(actions) {
            viewModel.actions = actions
        }
    }

}