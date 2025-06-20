package com.example.modul3xml.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.modul3xml.R
import com.example.modul3xml.adapter.RobloxGameAdapter
import com.example.modul3xml.databinding.FragmentRobloxGameListBinding
import com.example.modul3xml.model.RobloxGame
import com.example.modul3xml.viewmodel.RobloxGameViewModel
import com.example.modul3xml.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch
import timber.log.Timber

class RobloxGameListFragment : Fragment(), RobloxGameAdapter.OnItemClickListener {

    private var _binding: FragmentRobloxGameListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RobloxGameViewModel by viewModels {
        ViewModelFactory(requireContext().applicationContext.packageName)
    }

    private lateinit var adapter: RobloxGameAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRobloxGameListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = RobloxGameAdapter(this)
        binding.gamesRecyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.games.collect { games ->
                adapter.submitList(games)
            }
        }

        lifecycleScope.launch {
            viewModel.navigateToDetail.collect { game ->
                game?.let {
                    val action =
                        RobloxGameListFragmentDirections.actionGameListToGameDetail(it)
                    findNavController().navigate(action)
                    Timber.i("Data dari list yang dipilih ketika berpindah ke halaman Detail: %s", it)
                    viewModel.onDetailNavigated()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(game: RobloxGame) {
        viewModel.onGameClicked(game)
    }

    override fun onPlayNowClick(game: RobloxGame) {
        Timber.d("Tombol Explicit Intent ditekan untuk game: %s", game.title)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(game.gameUrl))
        requireActivity().startActivity(intent)
    }
} 