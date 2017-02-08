package com.example.homework

import java.util.*


class Book() {


    var nameBook = "Name"
    var author = "Author"
    var imgId = R.drawable.book
    var category = Category.SCIENCE

    constructor(name:String, author:String, img:Int, category: Category): this(){
        nameBook = name
        this.author = author
        imgId = img
        this.category = category
    }
    fun createArr(): ArrayList<Book> {
        val books= arrayListOf<Book>()
        val b1 = Book( "Hidden Figures", "Margot Lee Shetterly", R.drawable.hidden_figures, Category.BIOGRAPHY)
        books.add(b1)
        val b2 = Book("Wonder", "R. J. Palacio", R.drawable.wonder,Category.CHILDREN )
        books.add(b2)
        val b3 = Book("The Night Bird","Brian Freeman",R.drawable.night_bird, Category.MYSTERY)
        books.add(b3)
        val b4 = Book("Introduction to Algorithms",  "Thomas Cormen, Charles Leiserson, Ronald Rivest, Clifford Stein",
                R.drawable.algorithms, Category.SCIENCE)
        books.add(b4)
        val b5 = Book("The Girl on the Train", "Paula Hawkins",R.drawable.girl_on_the_train, Category.ROMANCE)
        books.add(b5)
        return books
    }
}
