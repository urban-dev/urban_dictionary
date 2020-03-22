package com.app.urbandictionary

import com.app.urbandictionary.model.UrbanDictionaryListModel
import com.app.urbandictionary.model.UrbanDictionaryModel
import com.app.urbandictionary.network.UrbanDisctionalryListService
import com.app.urbandictionary.utils.UrbanDictionaryUtils
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UrbanDictionaryApiTest {
    private var urbanDictionaryListModels: ArrayList<UrbanDictionaryListModel> =
        ArrayList<UrbanDictionaryListModel>()

    @Test
    @Throws(Exception::class)
    fun testUrbanDictionaryApi() {
        val urbanDisctionalryListService: UrbanDisctionalryListService = Retrofit.Builder()
            .baseUrl(UrbanDictionaryUtils.URBAN_DICTONARY_HOST)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(UrbanDisctionalryListService::class.java)
        urbanDisctionalryListService.getDictionaryResults("sports")
            .enqueue(object : Callback<UrbanDictionaryModel?> {
                override fun onResponse(
                    call: Call<UrbanDictionaryModel?>?,
                    response: Response<UrbanDictionaryModel?>
                ) {
                    urbanDictionaryListModels = response.body()?.list!!
                }

                override fun onFailure(
                    call: Call<UrbanDictionaryModel?>?,
                    t: Throwable?
                ) {
                }
            })
        Thread.sleep(2000)
        Assert.assertNotNull(urbanDictionaryListModels)
        Assert.assertNotNull(urbanDictionaryListModels[0].current_vote)
        Assert.assertNotNull(urbanDictionaryListModels[0].definition)
        Assert.assertNotNull(urbanDictionaryListModels[0].example)
        Assert.assertNotNull(urbanDictionaryListModels[0].word)
        Assert.assertNotNull(urbanDictionaryListModels[0].written_on)
    }
}
