    package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.affirmations.navigation.NavArguments
import com.example.affirmations.navigation.Screen
import com.example.affirmations.ui.screens.GameDetailScreen
import com.example.affirmations.ui.screens.GamesListScreen
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsTheme {
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
                            GamesListScreen(navController = navController)
                        }

                        composable(
                            route = Screen.GameDetail.route,
                            arguments = listOf(
                                navArgument(NavArguments.GAME_ID) {
                                    type = NavType.IntType
                                }
                            )
                        ) { backStackEntry ->
                            val gameId = backStackEntry.arguments?.getInt(NavArguments.GAME_ID) ?: 0
                            GameDetailScreen(
                                gameId = gameId,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
