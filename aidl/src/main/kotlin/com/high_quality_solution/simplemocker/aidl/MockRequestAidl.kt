package com.high_quality_solution.simplemocker.aidl

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MockRequestAidl(
    val appPackage: String,
    val path: String,
    val params: String? = null,
    val host: String? = null
): Parcelable