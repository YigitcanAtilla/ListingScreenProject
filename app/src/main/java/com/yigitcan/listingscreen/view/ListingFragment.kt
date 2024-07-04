package com.yigitcan.listingscreen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yigitcan.listingscreen.R
import com.yigitcan.listingscreen.adapter.ListingRecyclerViewAdapter
import com.yigitcan.listingscreen.databinding.FragmentListingLayoutBinding
import com.yigitcan.listingscreen.util.DividerItemDecoration
import com.yigitcan.listingscreen.util.ItemClickListener
import com.yigitcan.listingscreen.util.SwipeToDeleteCallback
import com.yigitcan.listingscreen.viewmodel.ListingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListingFragment : Fragment() {

    private val binding by lazy { FragmentListingLayoutBinding.inflate(layoutInflater) }
    private val adapter by lazy { ListingRecyclerViewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel : ListingViewModel by activityViewModels()
        initViews(viewModel)
    }


    private fun initViews(viewModel: ListingViewModel){
        binding.apply {
            initAdapter(listingRecyclerView,viewModel)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                adapter.submitList(it.list)
            }
        }
    }

    private fun initAdapter(listingRecyclerView : RecyclerView,viewModel: ListingViewModel){
        listingRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        listingRecyclerView.adapter = adapter

        adapter.onItemClickListener = ItemClickListener(childFragmentManager)

        val swipeToDeleteCallback = SwipeToDeleteCallback(adapter,viewModel)
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(listingRecyclerView)

        val dividerItemDecoration = DividerItemDecoration(resources.getDimensionPixelSize(R.dimen.divider_height))
        listingRecyclerView.addItemDecoration(dividerItemDecoration)

    }

}

