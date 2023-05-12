package com.example.carepets_plus.mainpart.home.weight

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carepets_plus.R
import com.example.carepets_plus.database.Weight

class WeightListAdapter(var wlist: List<Weight>) : RecyclerView.Adapter<WeightListAdapter.ViewHolder>() {
    lateinit var binding: WeightDiagramActivity

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemWeight: TextView = itemView.findViewById(R.id.tv_result_data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.line_result_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return wlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = wlist[position]
        holder.itemWeight.text = "${item.weightDate} - ${item.weightTime} - ${item.weightResult} kg"
    }
}