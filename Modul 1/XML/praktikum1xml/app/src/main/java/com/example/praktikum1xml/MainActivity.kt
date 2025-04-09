package com.example.praktikum1xml

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var diceImage1: ImageView
    private lateinit var diceImage2: ImageView
    private lateinit var rollButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceImage1 = findViewById(R.id.dice_image_1)
        diceImage2 = findViewById(R.id.dice_image_2)
        rollButton = findViewById(R.id.roll_button)
        diceImage1.setImageResource(R.drawable.dice_0)
        diceImage2.setImageResource(R.drawable.dice_0)


        rollButton.setOnClickListener {
            val result1 = (1..6).random()
            val result2 = (1..6).random()
            val imageRes1 = getDiceImage(result1)
            val imageRes2 = getDiceImage(result2)

            diceImage1.setImageResource(imageRes1)
            diceImage2.setImageResource(imageRes2)

            if (result1 == result2) {
                Toast.makeText(this, "Selamat, anda dapat dadu double!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDiceImage(result: Int): Int {
        return when (result) {
            0 -> R.drawable.dice_0
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}
