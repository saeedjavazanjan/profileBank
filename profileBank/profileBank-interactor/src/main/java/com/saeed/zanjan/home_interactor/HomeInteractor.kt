package com.saeed.zanjan.home_interactor

import com.saeed.zanjan.home_datasource.DataService

class HomeInteractor(
    private val getData: GetData
) {
    companion object Factory{
        fun build():HomeInteractor{
            val service=DataService.build()
            return HomeInteractor(
                GetData(
                    service
                )
            )


        }
    }
}