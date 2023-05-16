package com.example.carepets_plus.mainpart.home.articles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carepets_plus.R
import com.example.carepets_plus.databinding.FragmentArticle1Binding

class Article3Fragment : Fragment() {
    lateinit var binding: FragmentArticle1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticle1Binding.inflate(layoutInflater)
        binding.articleTitle.text = getString(R.string.article3)
        binding.articleImg.setImageResource(R.drawable.article3)
        binding.articleText.text = "Much has been written about the human-animal bond, and the benefits it can bring to owners of companion animals. Sometimes, pets are portrayed as more-or-less interchangeable, as if it made little difference to the relationship whether the pet happens to be a cat, a dog, or a rabbit.\n" +
                "\n" +
                "The emotional ties that owners feel towards their pets may be somewhat independent of the type of animal involved, but the way the animals feel about their owners will be markedly different from one species to another. Moreover, these differences have profound implications for the well-being of animals that find themselves in a less-than-ideal relationship.\n" +
                "\n" +
                "Of course, it’s obvious that cats and dogs aren’t the same, and the differences between them will be reflected in what they can contribute to the relationship. Few cat owners take their cats for walks, so it will mainly be dog owners who get the benefits of physical exercise and sociable exchanges with other owners. Dogs are much easier to train than cats are, and are much more tolerant of other members of their own species: both these differences stem from the two animals’ contrasting origins, and both are clues as to how they perceive their relationship with us.\n" +
                "\n" +
                "The domestic cat is essentially a rather solitary and territorial animal, and one that is still not completely domesticated, despite appearances to the contrary. Descended from the North African/Middle Eastern wildcat Felis lybica, it probably began a loose association with mankind some 10,000 years ago. But domestication in the sense of turning into a pet does not seem to have begun until about 2,000 B.C., and has not proceeded entirely smoothly since then.\n" +
                "\n" +
                "The status of cats as pets has waxed and waned over the centuries, and it is only very recently, on an evolutionary time-scale, that they have become as popular as dogs. Apart from the minority of kittens that come with a pedigree, most are the product of matings planned by the cats themselves, not by their owners. This habit takes cats outside the strict definition of a domesticated animal, which requires breeding to be at least predominantly under human control."
        return binding.root
    }


}