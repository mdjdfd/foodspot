package com.opensource.foodspotlight.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.opensource.foodspotlight.R
import com.opensource.foodspotlight.data.model.User
import kotlinx.android.synthetic.main.search_list_item.view.*
import kotlin.collections.ArrayList

class SearchAdapter(private val userList: ArrayList<User>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(), Filterable {

    var userFilterList = ArrayList<User>()

    init {
        userFilterList = userList
    }


    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val TAG: String = SearchViewHolder::class.java.name

        fun bind(user: User) {
            itemView.textview_search.text = user.location
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.search_list_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(userFilterList[position])
    }

    override fun getItemCount(): Int = userFilterList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                userFilterList = if (charSearch.isEmpty()) {
                    userList
                } else {
                    val resultList = ArrayList<User>()
                    for (row in userList) {
                        if (row.location?.contains(charSearch, true) == true) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = userFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                userFilterList = results?.values as ArrayList<User>
                notifyDataSetChanged()
            }

        }
    }


}