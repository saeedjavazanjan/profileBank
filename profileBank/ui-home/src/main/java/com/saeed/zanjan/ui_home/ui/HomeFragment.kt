package com.saeed.zanjan.ui_home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.saeed.zanjan.interactor.GetData
import com.saeed.zanjan.interactor.Interactor
import com.saeed.zanjan.ui_home.components.BannersAdapter
import com.saeed.zanjan.ui_home.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
@AndroidEntryPoint
class HomeFragment:Fragment() {

    var _binding:HomeFragmentBinding?=null
    val binding get() =_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=HomeFragmentBinding.inflate(inflater,container,false)

        val recycler=binding.firstBannerList
        val progressBar=binding.progressbar
        val viewModel=ViewModelProvider(this)[HomeFragmentViewModel::class.java]

        viewModel.state.observe(viewLifecycleOwner, Observer{ list ->
                /* Here you'll have your list and can set it on the adapter*/
         //       CoroutineScope(Dispatchers.Main).launch {
                    val adapter=BannersAdapter()
                    adapter.setRecyclerData(list)
                    recycler.layoutManager= LinearLayoutManager(requireContext())
                    recycler.adapter=adapter


             //   }



        })


        return binding.root
    }
}