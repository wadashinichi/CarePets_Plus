package com.example.carepets_plus.mainpart.home.heartbeat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carepets_plus.R
import com.example.carepets_plus.database.HeartBeat
import com.example.carepets_plus.mainpart.home.height.HeightListAdapter

class HeartBeatListAdapter(var hblist: List<HeartBeat>) : RecyclerView.Adapter<HeartBeatListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemHeartBeat: TextView = itemView.findViewById(R.id.tv_result_data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.line_result_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (hblist != null) {
            return hblist.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = hblist[position]
        holder.itemHeartBeat.text = "${item.heartBeatDate} - ${item.heartBeatTime} - ${item.heartBeatResult} beat/min"
    }
}
