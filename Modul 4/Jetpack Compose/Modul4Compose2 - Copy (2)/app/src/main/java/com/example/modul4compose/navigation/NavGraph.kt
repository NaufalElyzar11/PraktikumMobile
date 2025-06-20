package com.example.modul4compose.navigation

sealed class Screen(val route: String) {
    object GamesList : Screen("games_list")
    object GameDetail : Screen("game_detail/{gameId}") {
        fun createRoute(gameId: Int) = "game_detail/$gameId"
    }
}

object NavArguments {
    const val GAME_ID = "gameId"
} 