package com.example.testmyapplication.extratask

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInfo(val name:String):Parcelable
