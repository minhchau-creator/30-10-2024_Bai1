package com.example.a30_10_2024_bai1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ArrayAdapter


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editTextNumber)
        val radioEven = findViewById<RadioButton>(R.id.radioEven)
        val radioOdd = findViewById<RadioButton>(R.id.radioOdd)
        val radioSquare = findViewById<RadioButton>(R.id.radioSquare)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)
        val listView = findViewById<ListView>(R.id.listView)
        val textViewError = findViewById<TextView>(R.id.textViewError)

        buttonSubmit.setOnClickListener(){
            val input = editText.text.toString()
            val n = input.toInt()

            if (n<0 || n == null){
                textViewError.text = "Nhap sai du lieu"
                listView.adapter = null
                return@setOnClickListener
            }

            textViewError.text = "" // Xóa lỗi khi nhập đúng
            val numbers = when {
                radioEven.isChecked -> printEvenNumber(n)
                radioOdd.isChecked -> printOddNumbers(n)
                radioSquare.isChecked -> printSquareNumbers(n)
                else -> listOf()
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listView.adapter = adapter
        }
    }

}

private fun printEvenNumber(n : Int): List<Int>{
    return (0..n).filter { it % 2 == 0 }
}

private fun printOddNumbers(n: Int): List<Int> {
    return (1..n).filter { it % 2 != 0 }
}

private fun printSquareNumbers(n: Int): List<Int> {
    val result = mutableListOf<Int>()
    var i = 0
    while (i * i <= n) {
        result.add(i * i)
        i++
    }
    return result
}