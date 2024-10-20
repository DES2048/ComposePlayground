package yae.composeplayground.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavigationDemo() {
    // navController, нужен для навигации
    val navController = rememberNavController()

    // NavHost это Composable в котором настраиваются мрашруты и отображаются экраны
    // startDestination - стартовый маршрут
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        // здесь описывается конкретный маршрут и его экран
        composable(route = Screen.MainScreen.route) {
            MainScreen(onToDetails = {navController.navigate(Screen.DetailScreen.withName(it))})
        }
        // также можно передать параметры, которые принимает маршррут
        composable(
            route=Screen.DetailScreen.route,
            arguments = Screen.DetailScreen.argList()) {
            ///!!! не передавайте navController в composables, это приведет к лишним рекомпозициям
            DetailScreen(navController = navController, name = it.arguments?.getString("name"))
        }
    }
}

@Composable
fun MainScreen(onToDetails:(name:String)->Unit) {
    val name = remember {
        mutableStateOf("")
    }
    val isNameError = remember {
        mutableStateOf(false)
    }
    val nameErrorDetail = remember {
        mutableStateOf("")
    }
    fun cleanErrors() {
        nameErrorDetail.value = ""
        isNameError.value = false
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        TextField(value = name.value, isError = isNameError.value,
            onValueChange = {
                cleanErrors()
                name.value = it
                            },
            label = {
            Text("name")
        }, modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp))
        if (isNameError.value) {
            Text(text = nameErrorDetail.value, color= Color.Red)
        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(onClick = {
            val trimmed = name.value.trim()
            if (trimmed.isEmpty()) {
                isNameError.value = true
                nameErrorDetail.value = "Please provide name!"
            } else {
                onToDetails(name.value)
            } }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "To Details")
        }
    }
}

@Composable
fun DetailScreen(navController: NavController, name:String?) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
        Text(text = name ?: "No name provided:(")
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Back")
        }
    }
}

sealed class Screen(val route:String) {
    data object MainScreen : Screen(route = "main_screen")
    data object DetailScreen : Screen(route = "detail_screen/{name}") {
        fun withName(name: String):String {
            return "detail_screen/$name"
        }
        fun argList(): List<NamedNavArgument> = listOf(
            navArgument("name") {
                type = NavType.StringType
                defaultValue = "Annonimus"
                nullable = false
            }
        )
    }

}