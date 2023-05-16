package com.example.carepets_plus.mainpart.home.articles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carepets_plus.R
import com.example.carepets_plus.databinding.FragmentArticle1Binding

class Article1Fragment : Fragment() {
    lateinit var binding: FragmentArticle1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticle1Binding.inflate(layoutInflater)
        binding.articleTitle.text = getString(R.string.article1)
        binding.articleImg.setImageResource(R.drawable.article1)
        binding.articleText.text = "Having a pet sounds interesting and fun. But, adopting a pet and taking care of them is a big responsibility. Here are some of the tips which will help you to take good care of your pet and keep them happy, active and healthy:" +
                ""
        return binding.root
    }
}