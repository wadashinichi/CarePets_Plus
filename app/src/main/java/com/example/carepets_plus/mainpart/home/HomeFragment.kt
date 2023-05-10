package com.example.carepets_plus.mainpart.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carepets_plus.R
import com.example.carepets_plus.database.*
import com.example.carepets_plus.databinding.FragmentHomeBinding
import com.example.carepets_plus.mainpart.TrackerActivity
import com.example.carepets_plus.mainpart.home.heartbeat.HeartBeatDiagramActivity
import com.example.carepets_plus.mainpart.home.height.HeightDiagramActivity
import com.example.carepets_plus.mainpart.home.modify.ModifyInformationActivity
import com.example.carepets_plus.mainpart.home.weight.WeightDiagramActivity

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var res: PetRepository
    private lateinit var resWeight: WeightRepository
    private lateinit var resHeight: HeightRepository
    private lateinit var resHeartBeat: HeartBeatRepository
    private lateinit var trackerActivity: TrackerActivity
    private var id: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        trackerActivity = activity as TrackerActivity
        id = trackerActivity.getPetId()
        res = PetRepository(requireContext())
        resWeight = WeightRepository(requireContext())
        resHeight = HeightRepository(requireContext())
        resHeartBeat = HeartBeatRepository(requireContext())
        displayInfo(id)
        displayHealthTracker(id)
        // display noteBook

        linkToHealthTracker(id)
        return binding.root
    }


    private fun displayInfo(id: Int) {
        val pet: Pet? = res.getPetById(id)
//        val birth: String? = pet?.birth
        // calculate age
        binding.petInfor.text = "${pet?.name} - ${pet?.birth}"
        binding.editBtn.setOnClickListener {
            var i: Intent = Intent(requireContext(), ModifyInformationActivity::class.java)
            i.putExtra("petId", id)
            startActivity(i)
        }
    }
    private fun displayHealthTracker(id: Int) {
        binding.petWeight.text = resWeight.getLastWeight(id).toString() + "kg"
        binding.petHeight.text = resHeight.getLastHeight(id).toString() + "cm"
        binding.petHeartBeat.text = resHeartBeat.getLastHeartBeat(id).toString() + " beat/min"
    }
    private fun linkToHealthTracker(id: Int) {
        val context: android.content.Context = requireContext()
        binding.weightCardView.setOnClickListener {
            val i: Intent = Intent(context, WeightDiagramActivity::class.java)
            i.putExtra("petId", id)
            startActivity(i)
        }
        binding.heightCardView.setOnClickListener {
            val i: Intent = Intent(context, HeightDiagramActivity::class.java)
            i.putExtra("petId", id)
            startActivity(i)
        }
        binding.heartBeatCardView.setOnClickListener {
            val i: Intent = Intent(context, HeartBeatDiagramActivity::class.java)
            i.putExtra("petId", id)
            startActivity(i)
        }
    }

}