package com.saeed.zanjan.interactor

import com.saeed.zanjan.core.DataState
import com.saeed.zanjan.core.ProgressBarState
import com.saeed.zanjan.core.UiComponent
import com.saeed.zanjan.datasource.network.DataService
import com.saeed.zanjan.domain.FirstBanners
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetData(
    private val service: DataService
) {
    fun execute():Flow<DataState<List<FirstBanners>>>{
        return flow {
            try {
                emit(DataState.Loading<List<FirstBanners>>(ProgressBarState.Loading))
                val banners:List<FirstBanners> = try {
                    service.getFirstBannersState()
                } catch (e:Exception){
                    e.printStackTrace()
                    emit(DataState.Response<List<FirstBanners>>(
                        uiComponent = UiComponent.None(
                            message = e.message?:"UnknownError"

                        )))
                    listOf()
                }
                emit(DataState.Data(banners))
            }catch (e:Exception){
                e.printStackTrace()
                emit(DataState.Response<List<FirstBanners>>(
                    uiComponent = UiComponent.None(
                        message = e.message?:"UnknownError"
                    )))
            }
            finally {
                emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
            }



        }

    }
}