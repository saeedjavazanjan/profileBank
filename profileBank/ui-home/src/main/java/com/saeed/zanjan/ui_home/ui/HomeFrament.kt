package com.saeed.zanjan.ui_home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saeed.zanjan.core.DataState
import com.saeed.zanjan.core.ProgressBarState
import com.saeed.zanjan.domain.FirstBanners
import com.saeed.zanjan.ui_home.components.Adapter
import com.saeed.zanjan.ui_home.databinding.HomeFragmentBinding
import com.saeed.zanjan.ui_home.ui.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
@AndroidEntryPoint
class HomeFragment:Fragment() {

    private var _binding: HomeFragmentBinding?=null
    private val binding get() = _binding!!
    val progressbar:ProgressBar?=null
    val firstBannersList:RecyclerView?=null
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // val view=inflater.inflate(R.layout.home_fragment,container,false)
        _binding =HomeFragmentBinding .inflate(inflater, container, false)
        progressbar!=binding.progressbar//view.findViewById<ProgressBar>(R.id.progressbar)
        firstBannersList!=binding.firstBannerList//view.findViewById<RecyclerView>(R.id.first_banner_list)
        val viewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]
       val state=viewModel.state
     //   val progressBarState=viewModel.progressBarState

        GlobalScope.launch(Dispatchers.Main){
            val adapter = Adapter(banners = state.value.firstBanners)
          //  adapter.setRecyclerViewData(state.value.firstBanners)
            firstBannersList?.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL, false
            )
            firstBannersList?.adapter = adapter

       }



        return binding.root
    }



}