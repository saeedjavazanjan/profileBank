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

        val listObserver = Observer<MutableState<ListState>> { list ->
            val adapter = BannersAdapter()

            if (list.equals(list.value.progressBarState)){
                progressBar.visibility = View.VISIBLE
                recycler.visibility=View.GONE

            }else if (list.equals(list.value.firstBanners)){
                progressBar.visibility = View.GONE
                recycler.visibility=View.VISIBLE
                adapter.setRecyclerData(list.value.firstBanners)
                recycler.layoutManager = LinearLayoutManager(requireContext())
                recycler.adapter = adapter
            }


            when(list){
                list.value.progressBarState->{



                }
                list.value.firstBanners->{


                }
              /*  list.value.response->{
                    Toast.makeText(requireContext(),list.value.response.toString(),Toast.LENGTH_SHORT).show()
                }*/
            }

        }
        viewModel.state.observe(viewLifecycleOwner, listObserver)





        return binding.root
    }
}