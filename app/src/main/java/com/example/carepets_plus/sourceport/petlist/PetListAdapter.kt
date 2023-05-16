package com.example.carepets_plus.sourceport.petlist

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.carepets_plus.R
import com.example.carepets_plus.database.*
import com.example.carepets_plus.mainpart.TrackerActivity

class PetListAdapter(var plist: MutableList<Pet>, var context: Context) : RecyclerView.Adapter<PetListAdapter.ViewHolder>()  {

    private lateinit var res: PetRepository
    private lateinit var resWeight: WeightRepository
    private lateinit var resHeight: HeightRepository
    private lateinit var resHeartBeat: HeartBeatRepository

//    private var list: MutableList<Pet> = plist.toMutableList()
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.pet_image)
        val name: TextView = itemView.findViewById(R.id.pet_name)
        val species: TextView = itemView.findViewById(R.id.pet_species)
        val layoutItem: ConstraintLayout = itemView.findViewById(R.id.pet_detail)
        val delItem: FrameLayout = itemView.findViewById(R.id.pet_del)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.line_pet_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (plist != null) {
            return plist.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = plist[position]
        holder.name.text = item.name
        holder.species.text = item.species + " - " + item.birth

        // image

        var id: Int? = plist[position].id
        holder.layoutItem.setOnClickListener {
            sendIdToTrack(id, context)
        }
        holder.delItem.setOnClickListener {
            res = PetRepository(context)
            resWeight = WeightRepository(context)
            resHeight = HeightRepository(context)
            resHeartBeat = HeartBeatRepository(context)
            val builder: AlertDialog.Builder? = AlertDialog.Builder(context)
            builder?.apply {
                setPositiveButton("Delete"
                ) { _, _ ->
                    if (id != null) {
                        res.delPet(id)
                        //                res.getAllPet()?.let { it1 -> updateListChange(it1) }
                        resWeight.delWeightById(id)
                        resHeight.delHeightById(id)
                        resHeartBeat.delHeartBeatById(id)
//                        reDirect()
                        plist.removeAt(position)
                        val adapter: PetListAdapter = this@PetListAdapter
                        adapter.notifyItemRemoved(position)
//                        notifyDataSetChanged()
                    }
                }
                setNegativeButton("Cancle",
                DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()
                })
            }
            builder?.setMessage("Do you want to delete ${item.name} and all of its data?")
            builder?.create()
            builder?.show()
        }

    }
    private fun sendIdToTrack(id: Int?, context: Context) {
        val i: Intent = Intent(context, TrackerActivity::class.java)
        i.putExtra("petId", id)
        ContextCompat.startActivity(context, i, null)
    }
//    private fun updateListChange(nlist: List<Pet>) {
//        val diff: DiffUtilListChange = DiffUtilListChange(list, nlist)
//        val diffResult = DiffUtil.calculateDiff(diff)
////        plist = nlist
////        list.clear()
//        list.addAll(nlist)
//        diffResult.dispatchUpdatesTo(this)
//    }
    private fun reDirect() {
        val i: Intent = Intent()
        i.setClass(context, ListPetActivity::class.java)
        context.startActivity(i)
    }
}