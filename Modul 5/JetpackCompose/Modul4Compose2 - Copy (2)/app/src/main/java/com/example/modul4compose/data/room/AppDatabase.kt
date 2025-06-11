package com.example.modul4compose.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete

@Entity
data class WishlistMovie(
    @PrimaryKey val id: Int,
    val title: String,
    val poster_path: String
)

@Dao
interface WishlistDao {
    @Query("SELECT * FROM WishlistMovie")
    suspend fun getAll(): List<WishlistMovie>

    @Insert
    suspend fun insert(movie: WishlistMovie)

    @Delete
    suspend fun delete(movie: WishlistMovie)
}

@Database(entities = [WishlistMovie::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wishlistDao(): WishlistDao
} 