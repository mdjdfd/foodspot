package com.abmdigital.foodspotlight.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.abmdigital.foodspotlight.ApplicationController
import com.abmdigital.foodspotlight.R
import com.abmdigital.foodspotlight.data.model.User
import com.abmdigital.foodspotlight.ui.main.adapter.FeedAdapter
import com.abmdigital.foodspotlight.ui.main.viewmodel.FeedViewModel
import com.abmdigital.foodspotlight.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.fragment_feed.view.*
import javax.inject.Inject


@AndroidEntryPoint
class FeedFragment: Fragment(R.layout.fragment_feed) {

    private val TAG: String = FeedFragment::class.java.name

    private val feedViewModel: FeedViewModel by viewModels()
    private lateinit var feedAdapter: FeedAdapter

    @Inject
    lateinit var applicationController: ApplicationController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_feed, container, false)


        setupUI(root)
        setupObserver()

        return root

    }


    private fun setupUI(root: View) {
        root.recyclerview_feed.layoutManager = LinearLayoutManager(applicationController)
        feedAdapter = FeedAdapter(arrayListOf())
        root.recyclerview_feed.adapter = feedAdapter
    }

    private fun setupObserver() {
        feedViewModel.user.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressbar_feed.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerview_feed.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressbar_feed.visibility = View.VISIBLE
                    recyclerview_feed.visibility = View.GONE
                }
                Status.ERROR -> {
                    recyclerview_feed.visibility = View.GONE
                    Toast.makeText(applicationController, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    private fun renderList(users: List<User>) {
        feedAdapter.addData(users)
        feedAdapter.notifyDataSetChanged()
    }
}