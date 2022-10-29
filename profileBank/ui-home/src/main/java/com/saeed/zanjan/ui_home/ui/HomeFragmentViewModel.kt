package com.saeed.zanjan.ui_home.ui


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saeed.zanjan.core.DataState
import com.saeed.zanjan.core.UiComponent
import com.saeed.zanjan.domain.FirstBanners
import com.saeed.zanjan.interactor.GetData
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
  //  private val _state= MutableLiveData<ListState>() //MutableStateFlow(ListState())
  //  val state get() = _state
  val state= MutableLiveData<List<FirstBanners>>()

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

                        }
                    }
                }
                is DataState.Data -> {
                   // state.postValue(dataState.data?: listOf())

                   // _state.value = _state.value?.copy( firstBanners = dataState.data?: listOf())
                    state.postValue(dataState.data?:listOf())

                }
                is DataState.Loading -> {
                  //  _state.value =_state.value?.copy(progressBarState =dataState.progressBarState)
                  //  state.postValue(_state.value)
                  //  state.value=state.value.copy(dataState.progressBarState)
                    //.value=dataState.progressBarState

                }

            }

        }.launchIn(viewModelScope)
    }
}