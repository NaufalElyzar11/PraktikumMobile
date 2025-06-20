package com.example.modul3xml.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.modul3xml.databinding.ItemRobloxGameBinding
import com.example.modul3xml.model.RobloxGame
import com.example.modul3xml.ui.RobloxGameListFragmentDirections

class RobloxGameAdapter : RecyclerView.Adapter<RobloxGameAdapter.GameViewHolder>() {
    
    private var games = listOf<RobloxGame>()

    fun submitList(newGames: List<RobloxGame>) {
        games = newGames
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemRobloxGameBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount(): Int = games.size

    class GameViewHolder(private val binding: ItemRobloxGameBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(game: RobloxGame) {
            binding.apply {
                gameImage.setImageResource(game.imageUrl)
                gameTitle.text = game.title
                gameGenre.text = game.genre
                gamePlayers.text = game.players
                gameRating.text = game.rating

                // Play Now button - Explicit Intent
                playNowButton.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(game.gameUrl))
                    it.context.startActivity(intent)
                }

                // View Details button - Navigation Component
                viewDetailsButton.setOnClickListener { view ->
                    val action = RobloxGameListFragmentDirections
                        .actionGameListToGameDetail(game.id)
                    view.findNavController().navigate(action)
                }
            }
        }
    }
} 