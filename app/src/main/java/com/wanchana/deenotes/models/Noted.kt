package com.wanchana.deenotes.models

import android.os.Parcel
import android.os.Parcelable

class Noted(val name: String, val content: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(content)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Noted> {
        override fun createFromParcel(parcel: Parcel): Noted {
            return Noted(parcel)
        }

        override fun newArray(size: Int): Array<Noted?> {
            return arrayOfNulls(size)
        }
    }
}