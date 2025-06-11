package com.example.modul4compose.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.Room
import coil.compose.AsyncImage
import com.example.modul4compose.data.room.AppDatabase
import com.example.modul4compose.data.room.WishlistMovie
import com.example.modul4compose.viewmodel.MoviesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    movieId: Int,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: MoviesViewModel = viewModel()
) {
    val movie by viewModel.movieDetail.collectAsState()
    val context = LocalContext.current
    val db = remember {
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "app_db"
        ).fallbackToDestructiveMigration().build()
    }
    val scope = rememberCoroutineScope()
    var addedToWishlist by remember { mutableStateOf(false) }

    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetail(movieId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = movie?.title ?: "Movie Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.TopCenter
        ) {
            if (movie != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${movie!!.poster_path}",
                        contentDescription = movie!!.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = movie!!.title,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = movie!!.overview,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            scope.launch {
                                db.wishlistDao().insert(
                                    WishlistMovie(
                                        id = movie!!.id,
                                        title = movie!!.title,
                                        poster_path = movie!!.poster_path
                                    )
                                )
                                addedToWishlist = true
                            }
                        },
                        enabled = !addedToWishlist
                    ) {
                        Text(if (addedToWishlist) "Added to Wishlist" else "Add to Wishlist")
                    }
                }
            } else {
                CircularProgressIndicator()
            }
        }
    }
} 