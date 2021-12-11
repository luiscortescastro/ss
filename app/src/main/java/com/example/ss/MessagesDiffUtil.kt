package com.example.ss

import androidx.recyclerview.widget.DiffUtil
import com.ss.apportar.nw.Mensaje

class MessagesDiffUtil(
    private val oldList: List<Mensaje>,
    private val newList: List<Mensaje>,
 ): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].message != newList[newItemPosition].message ->{
                true
            }
            oldList[oldItemPosition].email != newList[newItemPosition].email ->{
                false
            }
            else -> true
        }
    }

}