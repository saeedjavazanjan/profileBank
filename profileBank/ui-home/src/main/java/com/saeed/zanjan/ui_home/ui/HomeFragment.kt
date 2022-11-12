package com.saeed.zanjan.ui_home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.saeed.zanjan.core.ProgressBarState
import com.saeed.zanjan.ui_home.ListState
import com.saeed.zanjan.ui_home.components.BannersAdapter
import com.saeed.zanjan.ui_home.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    var _binding: HomeFragmentBinding? = null
    val binding get() = _binding!!
    val adapter = BannersAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)

        val recycler = binding.firstBannerList
        val progressBar = binding.progressbar
        val viewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]

        val stateObserver = Observer<MutableState<ListState>> { state ->


            if (state.value.firstBanners.isEmpty()
                && state.value.progressBarState.equals(ProgressBarState.Loading)
            ) {
                progressBar.visibility = View.VISIBLE
                recycler.visibility = View.GONE
            } else if (state.value.firstBanners.isNotEmpty()) {
                progressBar.visibility = View.GONE
                recycler.visibility = View.VISIBLE
                adapter.setRecyclerData(state.value.firstBanners)
                recycler.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)
                recycler.adapter = adapter

            } else if (state.value.response != "" && state.value.firstBanners.isEmpty()) {
                progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), state.value.response, Toast.LENGTH_SHORT).show()

            }

        }


        viewModel.state.observe(viewLifecycleOwner, stateObserver)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}

