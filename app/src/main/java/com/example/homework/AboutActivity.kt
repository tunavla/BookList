package com.example.homework

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val aboutName = findViewById(R.id.about_name_text) as TextView
        val aboutAuthor = findViewById(R.id.about_author_text) as TextView
        val aboutCategory = findViewById(R.id.about_category_text) as TextView
        val aboutDescription = findViewById(R.id.about_description_text) as TextView
        val aboutImg = findViewById(R.id.about_img) as ImageView

        var name = intent.getStringExtra("KEY_ID")
        val book = DbHelper.getBook(name)
        Picasso.with(applicationContext).load(book.imgId).into(aboutImg)

        aboutName.text = book.nameBook
        aboutAuthor.text = book.author
        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
           val intentEdit = Intent(applicationContext, AddActivity::class.java)
            intentEdit.putExtra("KEY_ID_ABOUT", book.nameBook)
            startActivity(intentEdit)
        }
    }
}
