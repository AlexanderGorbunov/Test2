package ru.gorbunov.gaspromservice.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.gorbunov.gaspromservice.api.ApiServiceInterface.Factory.create
import ru.gorbunov.gaspromservice.models.Consts
import ru.gorbunov.gaspromservice.models.EquipmentWrapper

interface ApiServiceInterface {

    @GET("api/v1/test/plant")
    fun getEquipments(
        @Query("key") key: String = "temp01ghs49"
    ): Observable<EquipmentWrapper>

    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl(Consts.BASE_URL)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}