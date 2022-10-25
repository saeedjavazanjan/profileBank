package com.saeed.zanjan.datasource

import com.saeed.zanjan.datasource.EndPoints.BASE_URL
import com.saeed.zanjan.domain.FirstBanners
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataServiceImpl(
    private val httpClient: OkHttpClient
):DataService {
    override suspend fun getFirstBannersState(): List<FirstBanners> {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(DataService::class.java)
        return retrofit.getFirstBannersState()
    }
}