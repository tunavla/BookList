package com.example.homework

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val nameText = findViewById(R.id.add_name_text) as EditText
        val authorText = findViewById(R.id.add_author_text) as EditText
        val selectCategoryBtn = findViewById(R.id.select_category_btn) as Button
        val addButton = findViewById(R.id.add_btn)as Button

        var flag = 0
        val book = Book()
        var nameBook = book.nameBook
        var author = book.author
        selectCategoryBtn.setOnClickListener {
            flag++
        }

        addButton.setOnClickListener {
            if (nameText.text.toString() == "")
                Toast.makeText(this, "Input book's name", Toast.LENGTH_SHORT).show()
            else {
                book.nameBook = nameText.text.toString()
                flag++
            }
            if (authorText.text.toString() == "")
                Toast.makeText(this, "Input author(s) of book", Toast.LENGTH_SHORT).show()
            else{
                book.author = authorText.text.toString()
                flag++
            }
            if (flag >=2){
                DbHelper.addBook(book)
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
