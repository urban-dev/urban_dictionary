package com.app.urbandictionary.model

import android.os.Parcel
import android.os.Parcelable

class UrbanDictionaryModel(val list: ArrayList<UrbanDictionaryListModel>?) : Parcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeList(list)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UrbanDictionaryModel> {
        override fun createFromParcel(parcel: Parcel): UrbanDictionaryModel {
            return UrbanDictionaryModel(parcel)
        }

        override fun newArray(size: Int): Array<UrbanDictionaryModel?> {
            return arrayOfNulls(size)
        }
    }

    constructor(parcel: Parcel) : this(
        arrayListOf<UrbanDictionaryListModel>().apply {
            parcel.readArrayList(UrbanDictionaryListModel::class.java.classLoader)
        })

}
