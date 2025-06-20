package com.example.modul3xml.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.modul3xml.R
import com.example.modul3xml.databinding.FragmentRobloxGameDetailBinding
import com.example.modul3xml.model.RobloxGame

class RobloxGameDetailFragment : Fragment() {

    private var _binding: FragmentRobloxGameDetailBinding? = null
    private val binding get() = _binding!!
    private val args: RobloxGameDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRobloxGameDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // In a real app, you would fetch this data from a repository or ViewModel
        val game = when (args.gameId) {
            1 -> getAdoptMeDetails()
            2 -> getBrookhavenDetails()
            3 -> getBloxFruitsDetails()
            4 -> getPetSimulatorDetails()
            5 -> getMurderMysteryDetails()
            else -> getAdoptMeDetails() // Default to Adopt Me! if ID not found
        }

        displayGameDetails(game)
    }

    private fun displayGameDetails(game: RobloxGame) {
        binding.apply {
            gameHeaderImage.setImageResource(game.imageUrl)
            gameTitle.text = game.title
            gameGenre.text = game.genre
            gameRating.text = game.rating
            gameReleased.text = game.releaseDate
            gameDescription.text = game.description
            gameDeveloper.text = game.developer

            playNowButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(game.gameUrl))
                startActivity(intent)
            }
        }
    }

    private fun getAdoptMeDetails() = RobloxGame(
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
    )

    private fun getBrookhavenDetails() = RobloxGame(
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
    )

    private fun getBloxFruitsDetails() = RobloxGame(
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
    )

    private fun getPetSimulatorDetails() = RobloxGame(
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
    )

    private fun getMurderMysteryDetails() = RobloxGame(
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 