package com.app.urbandictionary.network

import com.app.urbandictionary.model.UrbanDictionaryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UrbanDisctionalryListService {
    @Headers(
        *["x-rapidapi-host: mashape-community-urban-dictionary.p.rapidapi.com", "x-rapidapi-key:" +
                " 5562e53b7cmshefa13cba0068776p19dbd7jsn43807cbe41fb"]
    )
    @GET("define")
    fun getDictionaryResults(@Query("term") term: String?): Call<UrbanDictionaryModel>
}
