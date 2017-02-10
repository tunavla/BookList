package com.example.homework

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

import android.provider.SyncStateContract.Helpers.update








class DbHelper private constructor(context: Context):SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    companion object {
        private var dbHelper: DbHelper? = null
        private var db: SQLiteDatabase? = null
        private val DB_NAME = "test"
        private val DB_VERSION = 1
        private val DB_COLUMN_NAME = "name"
        private val DB_COLUMN_AUTHOR = "author"
        //private val DB_COLUMN_CATEGORY = "category"
        private val DB_COLUMN_DESCRIPTION = "description"
        private val DB_COLUMN_IMG = "img"
        private val DB_TABLE_BOOK = "book"
        private val DB_CREATE_QUERY = " CREATE TABLE " +
                DB_TABLE_BOOK + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DB_COLUMN_NAME + " TEXT NOT NULL, " +
                DB_COLUMN_AUTHOR + " TEXT, " +
                // DB_COLUMN_CATEGORY + " TEXT, " +
                DB_COLUMN_IMG + " INTEGER, " +
                DB_COLUMN_DESCRIPTION + " TEXT )"

        fun initDbHelper(context: Context?) {
            if (db == null) {
                dbHelper = DbHelper(context!!)
                db = dbHelper!!.writableDatabase
            }
        }


        fun addBook(arr: ArrayList<Book>) {
            for (book in arr) {
                var cv = ContentValues()
                cv.put(DB_COLUMN_NAME, book.nameBook)
                cv.put(DB_COLUMN_AUTHOR, book.author)
                // cv.put(DB_COLUMN_CATEGORY, book.category.name)
                //cv.put(DB_COLUMN_DESCRIPTION, book.category.description)
                cv.put(DB_COLUMN_IMG, book.imgId)
                db!!.insert(DB_TABLE_BOOK, null, cv)
            }
        }

        fun addBook(book: Book) {
            var cv = ContentValues()
            cv.put(DB_COLUMN_NAME, book.nameBook)
            cv.put(DB_COLUMN_AUTHOR, book.author)
            // cv.put(DB_COLUMN_CATEGORY, book.category.name)
            //cv.put(DB_COLUMN_DESCRIPTION, book.category.description)
            cv.put(DB_COLUMN_IMG, book.imgId)
            db!!.insert(DB_TABLE_BOOK, null, cv)
        }

        //TODO problem with getBook(), exactly with getCategory, I think
        fun getBook(): ArrayList<Book> {
            val result = ArrayList<Book>()
            val resultQuery = db!!.query(DB_TABLE_BOOK, null, null, null, null, null, null, null)
            while (resultQuery.moveToNext()) {
                result.add(Book(
                        resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_NAME)),
                        resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_AUTHOR)),
                        resultQuery.getInt(resultQuery.getColumnIndex(DB_COLUMN_IMG))//,
                        //Category.valueOf(resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_CATEGORY)))
                ))
            }
            return result
        }

        fun update(book: Book){
            val cv = ContentValues()
            cv.put(DB_COLUMN_NAME, book.nameBook)
            cv.put(DB_COLUMN_AUTHOR, book.author)
            db!!.update(DB_TABLE_BOOK,cv, DB_COLUMN_IMG+ "=" +book.imgId, null)

        }


        fun closeDbHelper() {
            db!!.close()
            dbHelper!!.close()
        }

        fun deleteAll(): Int {
            return db!!.delete(DB_TABLE_BOOK, null, null)
        }


        fun getBook(name: String): Book {
            var result = Book()
            val resultQuery = db!!.query(DB_TABLE_BOOK, null, null, null, null, null, null, null)
            while (resultQuery.moveToNext()) {
                if (name == resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_NAME)))
                    result = Book(
                            resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_NAME)),
                            resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_AUTHOR)),
                            resultQuery.getInt(resultQuery.getColumnIndex(DB_COLUMN_IMG))//,
                    )
            }
            return result
        }
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(DB_CREATE_QUERY)
    }

}