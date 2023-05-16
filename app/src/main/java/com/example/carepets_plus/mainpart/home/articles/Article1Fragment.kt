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
                "1. Feed your pet a good and high-quality foods\n" +
                "\n" +
                "2. Take them for a walk every day for at least half an hour\n" +
                "\n" +
                "3. Provide them with the needed vaccination on time\n" +
                "\n" +
                "4. Keep a clean and hygienic environment for them\n" +
                "\n" +
                "5. Visit Vet on a weekly/monthly basis\n" +
                "\n" +
                "6. Engage and do not leave them alone for a long time\n" +
                "\n" +
                "7. Provide them with a good and comfortable shelter\n" +
                "\n" +
                "8. Keep them away from dust and allergies\n" +
                "\n" +
                "9. Love them unconditionally, treat them like your family, talk to them"
        return binding.root
    }
}