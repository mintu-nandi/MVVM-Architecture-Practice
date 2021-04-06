package com.example.example.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.example.R
import com.example.example.api.status.Result
import com.example.example.databinding.HomeFragmentBinding
import com.example.example.model.Todo
import com.example.example.util.toast
import com.example.example.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.home_fragment) {

    private lateinit var adapter: HomeAdapter
    private val viewModel: MainViewModel by viewModels()
    private lateinit var fragmentBinding: HomeFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding = HomeFragmentBinding.bind(view)
        viewModel.getTodoData()

        initView(view.context)
        setupDataObserver()
    }

    private fun initView(context: Context) {
        adapter = HomeAdapter(emptyList())

        val linearLayoutManager = LinearLayoutManager(context)
        val orientation = linearLayoutManager.orientation
        val decoration = DividerItemDecoration(context, orientation)

        fragmentBinding.recyclerView.layoutManager = linearLayoutManager
        fragmentBinding.recyclerView.addItemDecoration(decoration)
        fragmentBinding.recyclerView.adapter = adapter
    }

    private fun setupDataObserver() {
        viewModel.getTodoList().observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Result.Loading -> {
                    displayProgressBar(true)
                }
                is Result.Success -> {
                    displayProgressBar(false)
                    updateView(result.data)
                }
                is Result.Error -> {
                    displayProgressBar(false)
                    context?.toast(result.message)
                }
            }
        })
    }

    private fun updateView(data: List<Todo>) {
        adapter.apply {
            addProperties(data)
            notifyDataSetChanged()
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean){
        fragmentBinding.progressBar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

}