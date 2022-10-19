package com.saeed.zanjan.interactor

import com.saeed.zanjan.datasource.DataService

class Interactor (
    private val getData: GetData
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