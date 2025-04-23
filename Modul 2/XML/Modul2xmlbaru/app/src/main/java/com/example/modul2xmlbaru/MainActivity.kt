package com.example.modul2xmlbaru

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var billAmountInput: EditText
    private lateinit var tipPercentageDropdown: AutoCompleteTextView
    private lateinit var roundUpSwitch: Switch
    private lateinit var tipResult: TextView

    private val tipOptions = listOf("15%", "18%", "20%")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        billAmountInput = findViewById(R.id.billAmountInput)
        roundUpSwitch = findViewById(R.id.roundUpSwitch)
        tipResult = findViewById(R.id.tipResult)

        billAmountInput.addTextChangedListener(inputWatcher)
        roundUpSwitch.setOnCheckedChangeListener { _, _ -> calculateTip() }
        tipPercentageDropdown = findViewById(R.id.tipPercentageDropdown)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, tipOptions)
        tipPercentageDropdown.setAdapter(adapter)
        tipPercentageDropdown.keyListener = null
        tipPercentageDropdown.setOnItemClickListener { _, _, _, _ ->
            calculateTip()
        }
    }

    private val inputWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val amount = billAmountInput.text.toString().toDoubleOrNull() ?: 0.0
        val tipPercentText = tipPercentageDropdown.text.toString()
        val tipPercent = tipPercentText.removeSuffix("%").toDoubleOrNull()?.div(100) ?: 0.0
        var tip = amount * tipPercent

        if (roundUpSwitch.isChecked) {
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}