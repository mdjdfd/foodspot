package com.opensource.foodspotlight.ui.main.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.opensource.foodspotlight.ApplicationController
import com.opensource.foodspotlight.R
import com.opensource.foodspotlight.data.model.Currency
import com.opensource.foodspotlight.data.model.User
import com.opensource.foodspotlight.ui.main.adapter.FeedAdapter
import com.opensource.foodspotlight.ui.main.viewmodel.CurrencyViewModel
import com.opensource.foodspotlight.ui.main.viewmodel.FeedViewModel
import com.opensource.foodspotlight.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.fragment_feed.view.*
import javax.inject.Inject


@AndroidEntryPoint
class FeedFragment: Fragment(R.layout.fragment_feed) {

    private val TAG: String = FeedFragment::class.java.name

    private val feedViewModel: FeedViewModel by viewModels()
    private val currencyViewModel: CurrencyViewModel by viewModels()
    private lateinit var feedAdapter: FeedAdapter

    @Inject
    lateinit var appContext: Context


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
        root.recyclerview_feed.layoutManager = LinearLayoutManager(appContext)
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
                    Toast.makeText(appContext, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        currencyViewModel.currency.observe(viewLifecycleOwner, {
            when (it.status){
                Status.SUCCESS -> {
                    it.data?.let { currency -> renderCurrencyList(currency) }
                }
            }
        })
    }

    private fun renderCurrencyList(currency: List<Currency>) {
        Log.i(TAG, "_log setupObserver : $currency")
    }


    private fun renderList(users: List<User>) {
        feedAdapter.addData(users)
        feedAdapter.notifyDataSetChanged()
    }
}