package com.github.vladimirlisovskij.simple_mocker.aidl

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MockRequestAidl(
    val appPackage: String,
    val path: String,
    val params: String? = null,
    val host: String? = null
): Parcelable