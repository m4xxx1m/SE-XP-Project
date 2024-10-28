package ru.hse.se.xp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.hse.se.xp.ui.ListScreen
import ru.hse.se.xp.ui.LoginScreen
import ru.hse.se.xp.ui.MainScreen
import ru.hse.se.xp.ui.RegisterScreen

private sealed class NavScreen(val route: String) {

    data object RegisterScreen : NavScreen(route = "register")

    data object LoginScreen: NavScreen(route = "login")

    data object MainScreen : NavScreen(route = "main")

    class ListScreen(listId: Int, listTitle: String) :
        NavScreen(route = "info/$listId/$listTitle") {
        companion object {
            const val ARGUMENT_ID = "listId"
            const val ARGUMENT_TITLE = "listTitle"
//            val routeWithArgument =
//                ListScreen("{$ARGUMENT_ID}", "{$ARGUMENT_TITLE}").route
        }
    }
}

@Composable
fun App(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.LoginScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {

        composable(route = NavScreen.RegisterScreen.route) {
            RegisterScreen()
        }

        composable(route = NavScreen.LoginScreen.route) {
            LoginScreen()
        }

        composable(route = NavScreen.MainScreen.route) {
            MainScreen(
                toListScreen = { id, title ->
                    toListScreen(navController, NavScreen.ListScreen(id, title))
                }
            )
        }

        composable(
            route = NavScreen.ListScreen(-1, "").route,
            arguments = listOf(
                navArgument(NavScreen.ListScreen.ARGUMENT_ID) {
                    type = NavType.IntType
                },
                navArgument(NavScreen.ListScreen.ARGUMENT_TITLE) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val listId = backStackEntry.arguments?.getInt(NavScreen.ListScreen.ARGUMENT_ID)!!
            val listTitle = backStackEntry.arguments?.getString(NavScreen.ListScreen.ARGUMENT_TITLE)!!

            ListScreen(
                listId,
                listTitle,
                backToScreen = {
                    backToMainScreen(navController)
                }
            )
        }
    }
}

private fun backToMainScreen(navController: NavHostController) {
    navController.navigateUp()
}

private fun toListScreen(navController: NavHostController, screen: NavScreen.ListScreen) {
    navController.navigate(route = screen.route)
}
