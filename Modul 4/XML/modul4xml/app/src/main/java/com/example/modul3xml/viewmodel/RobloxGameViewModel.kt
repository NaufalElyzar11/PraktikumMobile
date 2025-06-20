package com.example.modul3xml.viewmodel

import androidx.lifecycle.ViewModel
import com.example.modul3xml.R
import com.example.modul3xml.model.RobloxGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class RobloxGameViewModel(appName: String) : ViewModel() {

    private val _games = MutableStateFlow<List<RobloxGame>>(emptyList())
    val games: StateFlow<List<RobloxGame>> = _games

    private val _navigateToDetail = MutableStateFlow<RobloxGame?>(null)
    val navigateToDetail: StateFlow<RobloxGame?> = _navigateToDetail

    init {
        Timber.d("$appName: ViewModel created.")
        loadGames()
    }

    private fun loadGames() {
        val gameList = listOf(
            RobloxGame(
                1,
                "Adopt Me!",
                "Role-Playing",
                "500K+ Active Players",
                "4.5/5",
                R.drawable.adopt_me,
                "https://www.roblox.com/games/920587237",
                "July 2017",
                "A role-playing game where players can adopt and raise pets, decorate homes, and interact with other players. Features include pet trading, house customization, and mini-games.",
                "DreamCraft"
            ),
            RobloxGame(
                2,
                "Brookhaven RP",
                "Role-Playing",
                "450K+ Active Players",
                "4.3/5",
                R.drawable.brookhaven,
                "https://www.roblox.com/games/4924922222",
                "April 2020",
                "A role-playing game set in the city of Brookhaven where players can live out their virtual lives. Features include house ownership, vehicle driving, and job roleplay.",
                "Wolfpaq"
            ),
            RobloxGame(
                3,
                "Blox Fruits",
                "Adventure",
                "400K+ Active Players",
                "4.7/5",
                R.drawable.blox_fruits,
                "https://www.roblox.com/games/2753915549",
                "June 2019",
                "An anime-inspired fighting game where players can become pirates or marines, eat powerful fruits, and master fighting styles to become the strongest.",
                "Gamer Robot Inc"
            ),
            RobloxGame(
                4,
                "Pet Simulator X",
                "Simulation",
                "350K+ Active Players",
                "4.4/5",
                R.drawable.pet_simulator,
                "https://www.roblox.com/games/6284583030",
                "July 2021",
                "Collect pets, hatch eggs, and discover rare creatures in this pet simulation game. Features include trading, various worlds to explore, and unique pet abilities.",
                "BIG Games"
            ),
            RobloxGame(
                5,
                "Murder Mystery 2",
                "Action",
                "300K+ Active Players",
                "4.6/5",
                R.drawable.murder_mystery,
                "https://www.roblox.com/games/142823291",
                "January 2014",
                "A thrilling game where players are assigned roles of Innocent, Sheriff, or Murderer. Work together or deceive others to win the round.",
                "Nikilis"
            )
        )
        _games.value = gameList
        Timber.i("Data item masuk ke dalam list")
    }

    fun onGameClicked(game: RobloxGame) {
        _navigateToDetail.value = game
        Timber.d("Tombol Detail ditekan untuk game: ${game.title}")
    }

    fun onDetailNavigated() {
        _navigateToDetail.value = null
    }
} 