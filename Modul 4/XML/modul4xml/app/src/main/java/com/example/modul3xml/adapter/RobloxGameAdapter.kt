package com.example.modul3xml.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.modul3xml.databinding.ItemRobloxGameBinding
import com.example.modul3xml.model.RobloxGame

class RobloxGameAdapter(private val listener: OnItemClickListener) :
    ListAdapter<RobloxGame, RobloxGameAdapter.GameViewHolder>(GameDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemRobloxGameBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class GameViewHolder(private val binding: ItemRobloxGameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewDetailsButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val game = getItem(position)
                    if (game != null) {
                        listener.onItemClick(game)
                    }
                }
            }
            binding.playNowButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val game = getItem(position)
                    if (game != null) {
                        listener.onPlayNowClick(game)
                    }
                }
            }
        }

        fun bind(game: RobloxGame) {
            binding.apply {
                gameImage.setImageResource(game.imageUrl)
                gameTitle.text = game.title
                gameGenre.text = game.genre
                gamePlayers.text = game.players
                gameRating.text = game.rating
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(game: RobloxGame)
        fun onPlayNowClick(game: RobloxGame)
    }

    class GameDiffCallback : DiffUtil.ItemCallback<RobloxGame>() {
        override fun areItemsTheSame(oldItem: RobloxGame, newItem: RobloxGame) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: RobloxGame, newItem: RobloxGame) =
            oldItem == newItem
    }
}