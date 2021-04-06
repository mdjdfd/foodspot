package com.abmdigital.foodspotlight.ui.main.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.abmdigital.foodspotlight.R
import com.abmdigital.foodspotlight.data.model.User
import com.abmdigital.foodspotlight.ui.main.adapter.SearchAdapter
import com.abmdigital.foodspotlight.ui.main.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.view.*
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val TAG: String = SearchFragment::class.java.name

    private val searchViewModel: SearchViewModel by viewModels()
    lateinit var searchAdapter: SearchAdapter
    private lateinit var root: View


    @Inject
    lateinit var appContext: Context


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_search, container, false)

        root.searchview_location.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchAdapter.filter.filter(newText)
                return false
            }

        })

        setupObserver()
        return root
    }


    private fun setupObserver() {
        searchViewModel.user.observe(viewLifecycleOwner, Observer {
            it.data?.let { users -> renderList(users) }
        })
    }

    private fun renderList(users: List<User>) {
        root.recyclerview_search.layoutManager = LinearLayoutManager(appContext)
        searchAdapter = SearchAdapter(users as ArrayList<User>)
        root.recyclerview_search.adapter = searchAdapter
    }


}