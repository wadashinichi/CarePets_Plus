package com.example.carepets_plus.mainpart.home.height

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carepets_plus.R
import com.example.carepets_plus.database.Height

class HeightListAdapter(var hlist: List<Height>) : RecyclerView.Adapter<HeightListAdapter.ViewHolder>() {
    lateinit var binding: HeightDiagramActivity

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemHeight: TextView = itemView.findViewById(R.id.tv_result_data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.line_result_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = hlist[position]
        holder.itemHeight.text = "${item.heightDate} - ${item.heightTime} - ${item.heightResult} cm"
    }
}