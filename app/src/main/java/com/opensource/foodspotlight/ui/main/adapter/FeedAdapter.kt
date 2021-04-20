package com.opensource.foodspotlight.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opensource.foodspotlight.R
import com.opensource.foodspotlight.data.model.User
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.information_section.view.*
import kotlinx.android.synthetic.main.item_view.view.*
import kotlinx.android.synthetic.main.user_section.view.*


class FeedAdapter(private val users: ArrayList<User>) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val TAG: String = FeedViewHolder::class.java.name

        fun bind(user: User) {
            itemView.textview_username.text = user.user_name
            itemView.textview_userlocation.text = user.location
            itemView.textview_posttitle.text = user.other_information.title
            itemView.textview_postdescription.text = user.other_information.description
            itemView.textview_likes.text = user.other_information.total_likes.toString()
            itemView.textview_dislikes.text = user.other_information.total_dislikes.toString()



            Glide.with(itemView.imageview_avatar.context)
                .load(user.user_image)
                .circleCrop()
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .into(itemView.imageview_avatar)

            Glide.with(itemView.imageview_post.context)
                .load(user.post_image)
                .placeholder(R.drawable.ic_baseline_photo_24)
                .into(itemView.imageview_post)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun addData(list: List<User>) {
        users.addAll(list)
    }

}