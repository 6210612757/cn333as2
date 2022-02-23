package com.wanchana.deenotes.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.wanchana.deenotes.R
import com.wanchana.deenotes.databinding.MainFragmentBinding
import com.wanchana.deenotes.models.Noted

class MainFragment() :
    Fragment(), ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {

    private lateinit var binding: MainFragmentBinding
    var clickListener: MainFragmentInteractionListener? = null
    var holdClickListener: MainFragmentInteractionListener? = null

    interface MainFragmentInteractionListener {
        fun listItemTapped(list: Noted)
        fun listItemHold(list: Noted)
    }


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        // id: lists_recyclerview => listsRecyclerview
        binding.listRecyvleview.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(),
            MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(requireActivity())))
            .get(MainViewModel::class.java)

        val recyclerViewAdapter = ListSelectionRecyclerViewAdapter(viewModel.lists, this,this)
        binding.listRecyvleview.adapter = recyclerViewAdapter
        viewModel.onListAdded = {
            recyclerViewAdapter.listsUpdated()
        }
        viewModel.onListRemoved ={
            recyclerViewAdapter.listsRemove(viewModel.whereRemoved)

        }
    }
    override fun listItemClicked(list: Noted) {
        clickListener?.listItemTapped(list)
    }

    override fun listItemHold(list: Noted) {
        holdClickListener?.listItemHold(list)
    }


}