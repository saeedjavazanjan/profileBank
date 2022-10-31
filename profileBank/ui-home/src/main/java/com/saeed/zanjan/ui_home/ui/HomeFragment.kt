package com.saeed.zanjan.ui_home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.saeed.zanjan.core.DataState
import com.saeed.zanjan.core.ProgressBarState
import com.saeed.zanjan.core.UiComponent
import com.saeed.zanjan.domain.FirstBanners
import com.saeed.zanjan.interactor.GetData
import com.saeed.zanjan.interactor.Interactor
import com.saeed.zanjan.ui_home.ListState
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

        val stateObserver = Observer<MutableState<ListState>> { state ->
            val adapter = BannersAdapter()


            if(state.value.firstBanners.isEmpty() && state.value.progressBarState.equals(ProgressBarState.Loading)) {
                progressBar.visibility = View.VISIBLE
                recycler.visibility = View.GONE
            }else if(state.value.firstBanners.isNotEmpty()){
                    progressBar.visibility = View.GONE
                    recycler.visibility=View.VISIBLE
                    adapter.setRecyclerData(state.value.firstBanners)
                    recycler.layoutManager = LinearLayoutManager(requireContext())
                    recycler.adapter = adapter

                } else if(state.value.response !="" &&  state.value.firstBanners.isEmpty()) {
                    progressBar.visibility=View.GONE
                Toast.makeText(requireContext(),state.value.response,Toast.LENGTH_SHORT).show()

                }

            }


        viewModel.state.observe(viewLifecycleOwner, stateObserver)





        return binding.root
    }
}