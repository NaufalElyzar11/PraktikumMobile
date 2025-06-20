package com.example.modul4compose.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.modul4compose.data.RobloxGame
import com.example.modul4compose.data.RobloxGamesData
import timber.log.Timber

class GamesViewModel(private val username: String) : ViewModel() {

    private val _games = MutableStateFlow<List<RobloxGame>>(emptyList())
    val games: StateFlow<List<RobloxGame>> = _games

    private val _selectedGame = MutableStateFlow<RobloxGame?>(null)
    val selectedGame: StateFlow<RobloxGame?> = _selectedGame

    init {
        Timber.i("ViewModel initialized with user: $username")

        _games.value = RobloxGamesData.games
        Timber.i("Loaded ${_games.value.size} games into the list")
    }

    fun selectGame(game: RobloxGame) {
        _selectedGame.value = game
        Timber.i("Selected game for detail view: ${game.title}")
    }

    fun getGameById(gameId: Int): RobloxGame? {
        return _games.value.find { it.id == gameId }
    }
}
