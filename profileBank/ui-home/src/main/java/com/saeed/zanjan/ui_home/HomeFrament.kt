package com.saeed.zanjan.ui_home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.saeed.zanjan.core.DataState
import com.saeed.zanjan.core.ProgressBarState
import com.saeed.zanjan.core.UiComponent
import com.saeed.zanjan.domain.FirstBanners
import com.saeed.zanjan.interactor.Interactor
import com.saeed.zanjan.ui_home.databinding.HomeFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeFragment:Fragment() {
    private val firstBanners: MutableState<List<FirstBanners>> = mutableStateOf(listOf())
    private val progressBarState:MutableState<ProgressBarState> = mutableStateOf(ProgressBarState.Idle)

    private var _binding:HomeFragmentBinding?=null
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
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        val progressbar=binding.progressbar
        val firstBannersList=binding.firstBannerList
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        firstBannersList.layoutManager=layoutManager
        val getData=Interactor.build().getData

        getData.execute().onEach { dataState->
            when (dataState) {
                is DataState.Response -> {
                    when (dataState.uiComponent) {
                        is UiComponent.Dialog -> {
                        }
                        is UiComponent.None -> {

                        }
                    }
                }
                is DataState.Data -> {

                    //    _state.value = _state.value.copy(heros = dataState.data ?: listOf())
                    firstBanners.value=dataState.data?: listOf()

                    GlobalScope.launch(Dispatchers.Main) {
                        if (dataState is DataState.Loading){
                            progressbar?.visibility = View.GONE
                            firstBannersList?.visibility = View.VISIBLE

                        }
                        if (dataState is DataState.Data){
                            progressbar?.visibility = View.GONE
                            firstBannersList?.visibility = View.VISIBLE

                        }
                      /*  val adapter = Adapter(heros =heros.value)
                        herolist?.layoutManager = LinearLayoutManager(this@MainActivity)
                        herolist?.adapter = adapter*/
                    }
                }
                is DataState.Loading -> {

                    //  _state.value =_state.value.copy(progressBarState =dataState.progressBarState)
                    progressBarState.value=dataState.progressBarState

                }

            }

        }.launchIn(CoroutineScope(Dispatchers.IO))

        return binding.root
    }

}