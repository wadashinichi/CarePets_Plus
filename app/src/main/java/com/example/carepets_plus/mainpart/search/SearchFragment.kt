package com.example.carepets_plus.mainpart.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carepets_plus.database.Article
import com.example.carepets_plus.databinding.FragmentSearchBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val gson = Gson()
    private lateinit var adapter: ArticleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        loadData()

        return binding.root
    }

    private fun loadData() {
        val articleApi = RetrofitClient.create()
        CoroutineScope(Dispatchers.IO).launch {
            val response = articleApi.getArticles()
            val articles = gson.fromJson<List<Article>>(response.string(),
            object : TypeToken<List<Article>>() {}.type)
            withContext(Dispatchers.Main) {
            displayList(articles)
            }

        }
    }
    private fun displayList(alist: List<Article>) {
        adapter = ArticleListAdapter(alist, requireContext())
        binding.rvArticleList.adapter = adapter
        binding.rvArticleList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }


}