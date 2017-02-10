package com.example.homework

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.Window
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
        var edit = false

        var editBookName:String? = intent.getStringExtra("KEY_ID_ABOUT")
        if (editBookName != null){
            edit = true
            val editBook = DbHelper.getBook(editBookName)
            nameText.setText(editBook.nameBook)
            authorText.setText(editBook.author)
        }
        var flag = 0
        var c = 0
        val book = Book()
        var nameBook = book.nameBook
        var author = book.author
        var chooseItem:Int? = null




        val arr = Array(5, {i->Category.values()[i].name})
        var i:Int
        selectCategoryBtn.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
                    .setTitle("Chose category")
                    .setSingleChoiceItems(arr,-1 , { dialogInterface, i -> chooseItem = i

                    })
                    .setPositiveButton("Ok", { dialog, which ->
                        if (chooseItem == null)
                            Toast.makeText(this, "Chose at least one category", Toast.LENGTH_SHORT).show()
                        else {
                            book.category = Category.valueOf(arr[chooseItem!!])
                            dialog.dismiss()
                        }

                    })
                    .create()
                    .show()
           // book.category = resultCategory

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
                if (edit) DbHelper.update(book)
                else DbHelper.addBook(book)

                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
