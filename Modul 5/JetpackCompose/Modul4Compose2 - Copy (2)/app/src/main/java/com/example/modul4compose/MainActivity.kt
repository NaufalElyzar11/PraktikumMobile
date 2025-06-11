package com.example.modul4compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.modul4compose.navigation.NavArguments
import com.example.modul4compose.navigation.Screen
import com.example.modul4compose.ui.screen.MoviesListScreen
import com.example.modul4compose.ui.screen.MovieDetailScreen
import com.example.modul4compose.ui.screen.WishlistScreen
import com.example.modul4compose.ui.theme.Modul4ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Modul4ComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.GamesList.route
                    ) {
                        composable(Screen.GamesList.route) {
                            MainScaffold(navController = navController, currentRoute = Screen.GamesList.route) {
                                MoviesListScreen(modifier = Modifier, navController = navController)
                            }
                        }

                        composable(
                            route = Screen.MovieDetail.route,
                            arguments = listOf(
                                navArgument("movieId") {
                                    type = NavType.IntType
                                }
                            )
                        ) { backStackEntry ->
                            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
                            MovieDetailScreen(
                                movieId = movieId,
                                navController = navController
                            )
                        }

                        composable(Screen.Wishlist.route) {
                            MainScaffold(navController = navController, currentRoute = Screen.Wishlist.route) {
                                WishlistScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScaffold(navController: androidx.navigation.NavController, currentRoute: String, content: @Composable () -> Unit) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute == Screen.GamesList.route,
                    onClick = { navController.navigate(Screen.GamesList.route) },
                    label = { Text("Movies") },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = currentRoute == Screen.Wishlist.route,
                    onClick = { navController.navigate(Screen.Wishlist.route) },
                    label = { Text("Wishlist") },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = null) }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }
}