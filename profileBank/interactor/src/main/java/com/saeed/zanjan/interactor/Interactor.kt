package com.saeed.zanjan.interactor

import com.saeed.zanjan.datasource.network.DataService

class Interactor (
     val getData: GetData,
) {
    companion object Factory{
        fun build():Interactor{
            val service= DataService.build()
            return Interactor(
                GetData(
                    service
                )
            )


        }
    }
}