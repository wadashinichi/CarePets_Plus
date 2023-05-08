package com.example.carepets_plus.sourceport.petlist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.carepets_plus.R
import com.example.carepets_plus.database.Pet
import com.example.carepets_plus.database.PetRepository
import com.example.carepets_plus.mainpart.TrackerActivity

class PetListAdapter(var plist: List<Pet>, var context: Context) : RecyclerView.Adapter<PetListAdapter.ViewHolder>()  {

    private lateinit var listPetActivity: ListPetActivity
    private lateinit var res: PetRepository
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
            if (id != null) {
                res.delPet(id)
            }
        }

    }
    private fun sendIdToTrack(id: Int?, context: Context) {
        val i: Intent = Intent(context, TrackerActivity::class.java)
        i.putExtra("petId", id)
        ContextCompat.startActivity(context, i, null)
    }
}