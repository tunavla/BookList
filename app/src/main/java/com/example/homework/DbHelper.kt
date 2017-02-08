package com.example.homework

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*


class DbHelper private constructor(context: Context):SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    companion object {
        private var dbHelper: DbHelper? = null
        private var db: SQLiteDatabase? = null
        private val DB_NAME = "test"
        private val DB_VERSION = 1
        private val DB_COLUMN_NAME = "name"
        private val DB_COLUMN_AUTHOR = "author"
        private val DB_COLUMN_CATEGORY = "category"
        private val DB_COLUMN_DESCRIPTION = "description"
        private val DB_COLUMN_IMG = "img"
        private val DB_TABLE_BOOK = "book"
        private val DB_CREATE_QUERY = " CREATE TABLE " +
                DB_TABLE_BOOK + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DB_COLUMN_NAME + " TEXT NOT NULL, " +
                DB_COLUMN_AUTHOR + " TEXT, " +
                DB_COLUMN_CATEGORY + " TEXT, " +
                DB_COLUMN_IMG + " INTEGER, " +
                DB_COLUMN_DESCRIPTION + " TEXT )"

       public fun initDbHelper(context: Context?) {
            if (db == null) {
                dbHelper = DbHelper(context!!)
                db = dbHelper!!.writableDatabase
            }
        }


        public fun addBook(arr: ArrayList<Book>) {
            for (book in arr) {
                var cv = ContentValues()
                cv.put(DB_COLUMN_NAME, book.nameBook)
                cv.put(DB_COLUMN_AUTHOR, book.author)
                cv.put(DB_COLUMN_CATEGORY, book.category.name)
                cv.put(DB_COLUMN_DESCRIPTION, book.category.description)
                cv.put(DB_COLUMN_IMG, book.imgId)
                db!!.insert(DB_TABLE_BOOK, null, cv)
            }
        }

        public fun addBook(book:Book){
            var cv = ContentValues()
            cv.put(DB_COLUMN_NAME, book.nameBook)
            cv.put(DB_COLUMN_AUTHOR, book.author)
            cv.put(DB_COLUMN_CATEGORY, book.category.name)
            cv.put(DB_COLUMN_DESCRIPTION, book.category.description)
            cv.put(DB_COLUMN_IMG, book.imgId)
            db!!.insert(DB_TABLE_BOOK, null, cv)
        }

        public fun getBook(): ArrayList<Book> {
            val result = ArrayList<Book>()
            val resultQuery = db!!.query(DB_TABLE_BOOK, null, null, null, null, null, null, null)
            while (resultQuery.moveToNext()) {
                result.add(Book(
                        resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_NAME)),
                        resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_AUTHOR)),
                        resultQuery.getInt(resultQuery.getColumnIndex(DB_COLUMN_IMG)),
                        Category.valueOf(resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_CATEGORY)))
                ))
            }
            return result
        }

        fun closeDbHelper() {
            db!!.close()
            dbHelper!!.close()
        }

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(DB_CREATE_QUERY)
    }

}