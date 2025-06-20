package com.example.modul4compose.data

data class RobloxGame(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val playersCount: String,
    val gameUrl: String,
    val genre: String,
    val rating: String,
    val releaseDate: String,
    val developer: String
)

object RobloxGamesData {
    val games = listOf(
        RobloxGame(
            1,
            "Adopt Me!",
            "A role-playing game where players can adopt and raise pets, decorate homes, and interact with other players. Features include pet trading, house customization, and mini-games.",
            "https://tr.rbxcdn.com/180DAY-23a22e242c44352464dbca03bbcb189a/768/432/Image/Webp/noFilter",
            "500K+ Active Players",
            "https://www.roblox.com/games/920587237",
            "Role-Playing",
            "4.5/5",
            "July 2017",
            "DreamCraft"
        ),
        RobloxGame(
            2,
            "Brookhaven RP",
            "A roleplaying game where players can live, work, and socialize in a virtual city. Features include house ownership, vehicle system, and various jobs to choose from.",
            "https://tr.rbxcdn.com/180DAY-d743ef79fbc0a90f9aea0bdf740cd00c/768/432/Image/Webp/noFilter",
            "450K+ Active Players",
            "https://www.roblox.com/games/4924922222",
            "Role-Playing",
            "4.3/5",
            "April 2020",
            "Wolfpaq"
        ),
        RobloxGame(
            3,
            "Blox Fruits",
            "An action-adventure game inspired by One Piece where players can train, fight, and collect Devil Fruits. Features include PvP combat, fruit hunting, and character progression.",
            "https://tr.rbxcdn.com/180DAY-37cc49a6bdb03b5a4394db84ff264fe5/768/432/Image/Webp/noFilter",
            "400K+ Active Players",
            "https://www.roblox.com/games/2753915549",
            "Action-Adventure",
            "4.7/5",
            "January 2019",
            "Gamer Robot Inc"
        ),
        RobloxGame(
            4,
            "Pet Simulator X",
            "Collect pets, hatch eggs, and explore various worlds while becoming the ultimate pet master. Features include trading system, rare pets collection, and world exploration.",
            "https://tr.rbxcdn.com/180DAY-384dc534a8bd24e4c6217056c1d4ad4f/768/432/Image/Webp/noFilter",
            "300K+ Active Players",
            "https://www.roblox.com/games/6284583030",
            "Simulation",
            "4.4/5",
            "July 2021",
            "BIG Games"
        ),
        RobloxGame(
            5,
            "Murder Mystery 2",
            "A thrilling game where players are assigned roles of innocent, sheriff, or murderer in each round. Features include weapon collecting, trading system, and competitive gameplay.",
            "https://tr.rbxcdn.com/180DAY-c929c9ba9069190914f60bbfe47b6cb9/768/432/Image/Webp/noFilter",
            "250K+ Active Players",
            "https://www.roblox.com/games/142823291",
            "Action",
            "4.6/5",
            "January 2014",
            "Nikilis"
        )
    )
}