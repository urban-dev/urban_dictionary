package com.app.urbandictionary.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.urbandictionary.model.UrbanDictionaryModel
import com.app.urbandictionary.network.UrbanDisctionalryListService
import com.app.urbandictionary.utils.UrbanDictionaryUtils
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UrbanDictionaryRepository private constructor() {
    private val urbanDisctionalryListService: UrbanDisctionalryListService?
    fun getDictionaryResults(searchTerm: String?): MutableLiveData<UrbanDictionaryModel>? {
        val data: MutableLiveData<UrbanDictionaryModel> = MutableLiveData()
        urbanDisctionalryListService!!.getDictionaryResults(searchTerm)
            .enqueue(object : Callback<UrbanDictionaryModel?> {
                override fun onResponse(
                    call: Call<UrbanDictionaryModel?>?,
                    response: Response<UrbanDictionaryModel?>?
                ) {
                    data.setValue(response?.body())
                }

                override fun onFailure(
                    call: Call<UrbanDictionaryModel?>?,
                    t: Throwable?
                ) {
                    Log.e("Failure", t!!.localizedMessage)
                }
            })
        return data
    }

    companion object {
        private var urbanDictionaryRepository: UrbanDictionaryRepository? = null
        val instance: UrbanDictionaryRepository?
            get() {
                if (urbanDictionaryRepository == null) {
                    urbanDictionaryRepository =
                        UrbanDictionaryRepository()
                }
                return urbanDictionaryRepository
            }
    }

    init {
        urbanDisctionalryListService = Retrofit.Builder()
            .baseUrl(UrbanDictionaryUtils.URBAN_DICTONARY_HOST)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(UrbanDisctionalryListService::class.java)
    }
}