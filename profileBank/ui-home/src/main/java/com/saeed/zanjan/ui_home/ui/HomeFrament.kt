package com.saeed.zanjan.ui_home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.saeed.zanjan.core.DataState
import com.saeed.zanjan.ui_home.components.Adapter
import com.saeed.zanjan.ui_home.databinding.HomeFragmentBinding
import com.saeed.zanjan.ui_home.ui.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
@AndroidEntryPoint
class HomeFragment:Fragment() {

    private var _binding: HomeFragmentBinding?=null
    private val binding get() = _binding!!

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
        val progressbar=binding.progressbar//view.findViewById<ProgressBar>(R.id.progressbar)
        val firstBannersList=binding.firstBannerList//view.findViewById<RecyclerView>(R.id.first_banner_list)
        val viewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]
        val state=viewModel.state
     //   GlobalScope.launch(Dispatchers.Main) {
            if (state is DataState.Loading<*>){
            //    progressbar?.visibility = View.GONE
             //   firstBannersList?.visibility = View.VISIBLE

            }
            if (state is DataState.Data<*>){
             //   progressbar?.visibility = View.GONE
             //   firstBannersList?.visibility = View.VISIBLE

            }
            val adapter = Adapter()
            adapter.setRecyclerViewData(state.value.firstBanners)
            firstBannersList?.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,false)
            firstBannersList?.adapter = adapter

     //   }


        return binding.root
    }

}