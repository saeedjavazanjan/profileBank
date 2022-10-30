package com.saeed.zanjan.ui_home.ui


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saeed.zanjan.core.DataState
import com.saeed.zanjan.core.UiComponent
import com.saeed.zanjan.domain.FirstBanners
import com.saeed.zanjan.interactor.GetData
import com.saeed.zanjan.ui_home.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel

    @Inject
constructor(
        private val getData: GetData
):ViewModel(){
    private val _state:MutableState<ListState> = mutableStateOf(ListState()) //MutableStateFlow(ListState())
  //  val state get() = _state

    val state: MutableLiveData<MutableState<ListState>> by lazy {
        MutableLiveData<MutableState<ListState>>()
    }

  /*  val state: MutableLiveData<List<FirstBanners>> by lazy {
        MutableLiveData<List<FirstBanners>>()
    }*/
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


                       //     _state.value = _state.value?.copy(response = dataState.uiComponent)!!

                        }
                    }
                }
                is DataState.Data -> {
                   // state.postValue(dataState.data?: listOf())

                    _state.value = _state.value?.copy( firstBanners = dataState.data?: listOf())!!
                //    state.postValue(dataState.data?:listOf())
                    state.postValue(_state)

                }
                is DataState.Loading -> {
                   // state.postValue(state.value?.value(progressBarState =dataState.progressBarState))
                      _state.value = _state.value?.copy(progressBarState =dataState.progressBarState)!!
                  //  state.value=state.value.copy(dataState.progressBarState)
                    //.value=dataState.progressBarState
                    state.postValue(_state)

                }

            }
          //  state.postValue(_state)

        }.launchIn(viewModelScope)
    }
}