package com.saeed.zanjan.ui_home.ui

import android.view.View
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.saeed.zanjan.core.DataState
import com.saeed.zanjan.core.ProgressBarState
import com.saeed.zanjan.core.UiComponent
import com.saeed.zanjan.domain.FirstBanners
import com.saeed.zanjan.interactor.GetData
import com.saeed.zanjan.ui_home.ListState
import com.saeed.zanjan.ui_home.components.Adapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel

    @Inject
constructor(
        private val getData: GetData
):ViewModel(){
 //   private val _state= MutableStateFlow(FirstBanners())
    val state: MutableState<ListState> = mutableStateOf(ListState())
   // val progressBarState:MutableState<ProgressBarState> = mutableStateOf(ProgressBarState.Idle)
    init {
        getData()
    }

    private fun getData(){
        getData.execute().onEach { dataState->
            when (dataState) {
                is DataState.Response -> {
                    when (dataState.uiComponent) {
                        is UiComponent.Dialog -> {
                        }
                        is UiComponent.None -> {
                         /*   GlobalScope.launch(Dispatchers.Main)
                            {
                                Toast.makeText(requireContext(),(dataState.uiComponent as UiComponent.None).message,
                                    Toast.LENGTH_SHORT).show()

                            }*/

                        }
                    }
                }
                is DataState.Data -> {

                    state.value = state.value.copy( firstBanners = dataState.data?: listOf())
                  //  state.value=dataState.data?: listOf()


                }
                is DataState.Loading -> {

                    state.value =state.value.copy(progressBarState =dataState.progressBarState)
                  //  state.value=state.value.copy(dataState.progressBarState)

                }

            }

        }.launchIn(CoroutineScope(Dispatchers.IO))
    }
}