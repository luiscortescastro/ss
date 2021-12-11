package com.ss.apportar.nw

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.calculateDiff
import androidx.recyclerview.widget.RecyclerView
import com.example.ss.MessagesDiffUtil

import com.example.ss.R

class CommAdapter(
    val messagesListener: MessagesListener,
) : RecyclerView.Adapter<CommAdapter.ViewHolder>() {

    var listMessages = ArrayList<Mensaje>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val layoutView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.comment_cardview_layout, viewGroup, false)

        return ViewHolder(layoutView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val messages = listMessages[position]
        holder.commUsername.text = messages.email
        holder.commEnt.text = messages.message
        holder.commImage.setImageResource(
            R.drawable.persona_black_icon
        )
    }

    inner class ViewHolder(commView: View) : RecyclerView.ViewHolder(commView) {
        var commImage: ImageView = commView.findViewById(R.id.userLogo)
        var commUsername: TextView = commView.findViewById(R.id.userName)
        var commEnt: TextView = commView.findViewById(R.id.userComm)
    }


    override fun getItemCount() = listMessages.size

    fun updateData(data: List<Mensaje>){
        listMessages.clear()
        listMessages.addAll(data)
        notifyDataSetChanged()
    }
}