package com.app.urbandictionary.model

import android.os.Parcel
import android.os.Parcelable


class UrbanDictionaryListModel(
    val definition: String?, val permLink: String?, val thumbs_up: Int,
    val sound_url: ArrayList<String>, val word: String?, val current_vote
    : String?, val defid: String?, val written_on: String?, val example: String?,
    val thumbs_down: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        arrayListOf<String>().apply {
            parcel.readArrayList(String::class.java.classLoader)
        },
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(definition)
        dest.writeString(permLink)
        dest.writeInt(thumbs_up)
        dest.writeStringList(sound_url)
        dest.writeString(word)
        dest.writeString(defid)
        dest.writeString(current_vote)
        dest.writeString(written_on)
        dest.writeString(example)
        dest.writeInt(thumbs_down)
    }

    companion object {
        val CREATOR: Parcelable.Creator<UrbanDictionaryListModel?> =
            object : Parcelable.Creator<UrbanDictionaryListModel?> {
                override fun createFromParcel(`in`: Parcel): UrbanDictionaryListModel? {
                    return UrbanDictionaryListModel(`in`)
                }

                override fun newArray(size: Int): Array<UrbanDictionaryListModel?> {
                    return arrayOfNulls(size)
                }
            }
    }


}