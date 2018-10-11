package com.sri.testsen.`interface`

import com.sri.testsen.model.RealEstateModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitApiService {

    @GET("properties")
    fun loadfeed(): Observable<RealEstateModel>

    companion object RetrofitApiclient {

        fun create(): RetrofitApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://demo7442132.mockable.io/test/")
                    .build()

            return retrofit.create<RetrofitApiService>(RetrofitApiService::class.java)
        }
    }
}