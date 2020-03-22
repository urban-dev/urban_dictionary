package com.app.urbandictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.urbandictionary.model.UrbanDictionaryModel
import com.app.urbandictionary.repository.UrbanDictionaryRepository

class UrbanDictionaryViewModel : ViewModel() {
    private var urbanDictionaryRepository: UrbanDictionaryRepository? = null
    private var modelMutableLiveData: MutableLiveData<UrbanDictionaryModel>? = null

    fun init(searchTerm: String?) {
        urbanDictionaryRepository = UrbanDictionaryRepository.instance
        modelMutableLiveData = urbanDictionaryRepository?.getDictionaryResults(searchTerm)
    }

    val urbanDictionaryResults: LiveData<UrbanDictionaryModel>?
        get() = modelMutableLiveData
}
