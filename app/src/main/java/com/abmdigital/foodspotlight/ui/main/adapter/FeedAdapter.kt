package com.abmdigital.foodspotlight.ui.main.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abmdigital.foodspotlight.ApplicationController
import com.abmdigital.foodspotlight.R
import com.abmdigital.foodspotlight.data.model.User
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view.view.*


class FeedAdapter(private val users: ArrayList<User>): RecyclerView.Adapter<FeedAdapter.DataViewHolder>(){


    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            itemView.textview_username.text = user.user_name
            itemView.textview_userlocation.text = user.location

            Glide.with(itemView.imageview_avatar.context)
                .load(user.user_image)
                .into(itemView.imageview_avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_view, parent, false
        ))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun addData(list: List<User>){
        users.addAll(list)
    }

}