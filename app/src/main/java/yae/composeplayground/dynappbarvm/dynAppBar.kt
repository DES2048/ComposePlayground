package yae.composeplayground.dynappbarvm

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object CatList
    @Serializable
    data class CatDetail(val id:Int)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoDynAppBarVm() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
          ContentAwareTopAppBar(
              navController = navController
          )
        },
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        NavHost(
            startDestination = Screen.CatList,
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable<Screen.CatList> {
                CatListScreen(
                    {id -> navController.navigate(Screen.CatDetail(id))}
                )
            }

            composable<Screen.CatDetail> {
                val args = it.toRoute<Screen.CatDetail>()
                CatDetailScreen(
                    args.id,
                    navController::popBackStack
                )
            }

        }
    }
}

@Composable
fun CatListScreen(
    goToDetail: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    val cats = remember { CatRepository.cats }

    ProvideAppBarTitle {
        Text(text="Cats")
    }

    LazyColumn(
        modifier = modifier,
    ) {
        items(cats, key={it.id}) { cat ->
            Text(
                text = cat.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        goToDetail(cat.id)
                    }
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun CatDetailScreen(
    id: Int,
    goBack: ()->Unit,
    modifier: Modifier = Modifier,
) {

    val cat = remember { CatRepository.getById(id) }
    var counter by remember { mutableStateOf(0) }

    ProvideAppBarTitle {
        Text(text = cat.name)
    }
    ProvideAppBarNavigationIcon {
        IconButton(
            onClick = {
                goBack()
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
    ProvideAppBarActions {
        IconButton(
            onClick = {
                counter++
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "Add"
            )
        }
    }
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = cat.description,
            modifier = Modifier.fillMaxWidth(),
        )
    }

}