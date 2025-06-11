package com.example.modul4compose.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import coil.compose.AsyncImage
import com.example.modul4compose.data.room.AppDatabase
import com.example.modul4compose.data.room.WishlistMovie
import kotlinx.coroutines.launch

@Composable
fun WishlistScreen() {
    val context = LocalContext.current
    val db = remember {
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "app_db"
        ).fallbackToDestructiveMigration().build()
    }
    var wishlist by remember { mutableStateOf(listOf<WishlistMovie>()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        wishlist = db.wishlistDao().getAll()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Wishlist Film", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(wishlist, key = { it.id }) { movie ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                            contentDescription = movie.title,
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(movie.title, modifier = Modifier.weight(1f))
                        IconButton(onClick = {
                            scope.launch {
                                db.wishlistDao().delete(movie)
                                wishlist = db.wishlistDao().getAll()
                            }
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Remove")
                        }
                    }
                }
            }
        }
    }
}
