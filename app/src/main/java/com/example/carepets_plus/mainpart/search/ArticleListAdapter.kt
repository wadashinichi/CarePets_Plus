package com.example.carepets_plus.mainpart.search

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.carepets_plus.R
import com.example.carepets_plus.database.Article
import com.squareup.picasso.Picasso
import java.net.URL


class ArticleListAdapter(var alist: List<Article>, var context: Context) : RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.article_img)
        val title: TextView = itemView.findViewById(R.id.article_title)
        val cardView: CardView = itemView.findViewById(R.id.article_cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.line_article_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (alist != null) {
            return alist.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = alist[position]
//        val url: URL = URL(item.img)
//        val uri: Uri = Uri.parse(url.toString())
//        holder.img.setImageURI(uri)
        //
//        val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
//        holder.img.setImageBitmap(bitmap)

        val imgUrl: String = "https://www.pedigree.com/sites/g/files/fnmzdf1201/files/migrate-product-files/images/ytyyeemm2nmwrddrotut.png"
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
//            holder.img.setImageURI(imgUri)

            Picasso.get()
                .load(imgUrl)
                .placeholder(R.drawable.ic_android)
                .error(R.drawable.ic_calendar)
                .fit()
                .into(holder.img)

            holder.title.text = item.title
        }
        holder.cardView.setOnClickListener {
            var img = ""
            if (item.img != null) {
                img = item.img
            }
            linkToArticle(item.title, img, item.text)

        }
    }
    fun linkToArticle(title:String, url: String = "", text: String) {
        val i: Intent = Intent(context, ReadArticleActivity::class.java)
        i.putExtra("title", title)
        i.putExtra("url", url)
        i.putExtra("text", text)
        context.startActivity(i)
    }
}