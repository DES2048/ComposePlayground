package yae.composeplayground.dynappbarvm

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentAwareTopAppBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    backStackEntry?.let { entry ->
        val vm: TopAppBarViewModel = viewModel(
            viewModelStoreOwner = entry,
            initializer = { TopAppBarViewModel() }
        )

        LargeTopAppBar(
            title = vm.title,
            navigationIcon = vm.navigationIcon,
            actions = vm.actions ,
        modifier = modifier,
        )
    }
}