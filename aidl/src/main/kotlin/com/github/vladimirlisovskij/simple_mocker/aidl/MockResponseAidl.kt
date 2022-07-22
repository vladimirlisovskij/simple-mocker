package com.github.vladimirlisovskij.simple_mocker.aidl

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MockResponseAidl(
    val bodyUri: Uri
): Parcelable