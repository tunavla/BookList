package com.example.homework

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.util.*

class BookAdapter(arr: ArrayList<Book>): RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    var mData = arr
    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.nameText.text = mData[position].nameBook
        holder.authorText.text = mData[position].author
        holder.categoryText.text = mData[position].category.name
        holder.descriptionText.text = mData[position].category.description
        Picasso.with(holder.view.context).load(mData[position].imgId).into(holder.img)
        holder.view.setOnClickListener {
            val intent = Intent(holder.view.context, AboutActivity::class.java)
            intent.putExtra("KEY_ID" ,mData[position].nameBook)
            startActivity(holder.view.context, intent, null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.book,parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var view:View
        var nameText:TextView
        var authorText:TextView
        var categoryText:TextView
        var descriptionText:TextView
        var img:ImageView
        init {
            view = itemView
            nameText = itemView.findViewById(R.id.name_text) as TextView
            authorText = itemView.findViewById(R.id.author_text) as TextView
            categoryText = itemView.findViewById(R.id.category_text) as TextView
            descriptionText = itemView.findViewById(R.id.description_text) as TextView
            img = itemView.findViewById(R.id.img) as ImageView
        }
    }
}