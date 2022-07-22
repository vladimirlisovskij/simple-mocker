package com.high_quality_solution.simplemocker.ui.request_editor

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class RequestEditorViewModel(
    val itemId: Long
): ViewModel() {

    fun onFileSelected(fileUri: Uri) {

    }


    class Factory() {
        fun create(itemId: Long) = RequestEditorViewModel(itemId)
    }
}