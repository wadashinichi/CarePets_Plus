package com.example.carepets_plus.mainpart.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.carepets_plus.R
import com.example.carepets_plus.databinding.ActivityReadArticleBinding
import com.squareup.picasso.Picasso

class ReadArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadArticleBinding.inflate(layoutInflater)
        setToolBar()
        val i: Intent = intent
        val title = i.getStringExtra("title")
        val url = i.getStringExtra("url")
        val text = i.getStringExtra("text")
        bindToView(title, url, text)
        setContentView(binding.root)
    }
    private fun setToolBar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun bindToView(title: String?, url: String?, text: String?) {
        binding.articleTitle.text = title

        if (url != null) {
            if (url.isEmpty()) {
                binding.articleImg.setImageResource(R.drawable.ic_error)
            } else {
                val imgUrl: String? = url
                Picasso.get()
                    .load(imgUrl)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_error)
                    .fit()
                    .into(binding.articleImg)
            }
        }
        binding.articleText.text = text
    }
}