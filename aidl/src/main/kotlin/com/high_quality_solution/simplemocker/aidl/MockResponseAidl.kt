package com.high_quality_solution.simplemocker.aidl

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MockResponseAidl(
    val bodyUri: Uri
): Parcelable