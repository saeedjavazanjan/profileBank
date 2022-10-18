package com.saeed.zanjan.home_datasource

import com.saeed.zanjan.home_datasource.EndPoints.FIRST_BANNERS_STATE
import com.saeed.zanjan.home_domein.FirstBanners
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface DataService {
    @GET(FIRST_BANNERS_STATE)
    fun getFirstBannersState():List<FirstBanners>

    companion object Factory{
        fun build():DataService{
            val interceptor  = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            return DataServiceImpl(
                httpClient = OkHttpClient()
                    .newBuilder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(interceptor).build()
            )

        }
    }
}