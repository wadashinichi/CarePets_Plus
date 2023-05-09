package com.example.carepets_plus.sourceport.petlist

import androidx.recyclerview.widget.DiffUtil
import com.example.carepets_plus.database.Pet
import javax.annotation.Nullable

class DiffUtilListChange(
    private val oldList: List<Pet>,
    private val newList: List<Pet>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPet = oldList[oldItemPosition]
        val newPet = oldList[newItemPosition]
        return oldPet.name == newPet.name
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}