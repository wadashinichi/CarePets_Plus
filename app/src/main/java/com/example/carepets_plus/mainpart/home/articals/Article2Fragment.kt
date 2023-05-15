package com.example.carepets_plus.mainpart.home.articals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carepets_plus.R
import com.example.carepets_plus.databinding.FragmentArticle1Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Article2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Article2Fragment : Fragment() {
    private lateinit var binding: FragmentArticle1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticle1Binding.inflate(layoutInflater)
        binding.articleTitle.text = getString(R.string.article2)
        binding.articleImg.setImageResource(R.drawable.article2)
        binding.articleText.text = "There are many health benefits of owning a pet. They can increase opportunities to exercise, " +
                "get outside, and socialize. Regular walking or playing with pets can decrease blood pressure, cholesterol levels, " +
                "and triglyceride levels.  Pets can help manage loneliness and depression by giving us companionship. " +
                "Most households in the United States have at least one pet." +
                "\nStudies have shown that the bond between people and their pets is linked to several health benefits, including:" +
                "\nDecreased blood pressure, cholesterol levels, triglyceride levels, feelings of loneliness, anxiety, and symptoms of PTSD." +
                "\nIncreased opportunities for exercise and outdoor activities; better cognitive function in older adults; and more opportunities to socialize"
        return binding.root
    }


}