package com.saeed.zanjan.home_datasource

import com.saeed.zanjan.home_datasource.EndPoints.BASE_URL
import com.saeed.zanjan.home_domein.FirstBanners
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataServiceImpl(
    private val httpClient: OkHttpClient
):DataService {
    override fun getFirstBannersState(): List<FirstBanners> {
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(DataService::class.java)
        return retrofit.getFirstBannersState()
    }
}