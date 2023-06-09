package com.example.carepets_plus.mainpart.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.fragment.app.Fragment
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
    private var mContext: Context? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        loadData()
//        binding.editSearch.addTextChangedListener {it ->
//            binding.editSearch.doAfterTextChanged {
//                loadDataHaveInput(binding.editSearch.text.toString())
//            }
//        }
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
//    private fun loadDataHaveInput(input: String) {
//        val articleApi = RetrofitClient.create()
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = articleApi.getArticlesByName(input)
//            val articles = gson.fromJson<List<Article>>(response.string(),
//                object : TypeToken<List<Article>>() {}.type)
//            withContext(Dispatchers.Main) {
//                displayList(articles)
//            }
//
//        }
//    }
    private fun displayList(alist: List<Article>) {
        adapter = ArticleListAdapter(alist, requireContext())
        binding.rvArticleList.adapter = adapter
        binding.rvArticleList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
//    private fun displayChangedList(alist: List<Article>) {
//        adapter.
//    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
    }


}