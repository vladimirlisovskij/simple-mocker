package com.github.vladimirlisovskij.simple_mocker.ui.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.github.vladimirlisovskij.simple_mocker.ui.R

object Ext {
    inline fun<reified VM: ViewModel> Fragment.simpleViewModels(crossinline creator: () -> ViewModel): Lazy<VM> {
        return viewModels(
            factoryProducer = {
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return creator.invoke() as T
                    }
                }
            }
        )
    }

    fun createAfterTextChangedTextWatcher(
        block: String.() -> Unit
    ) = object : TextWatcher {
        override fun afterTextChanged(s: Editable) {
            block.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    fun EditText.setTextWithoutTextWatcher(
        watcher: TextWatcher,
        text: String
    ) {
        val savedSelection = selectionStart
        removeTextChangedListener(watcher)
        setText(text)
        setSelection(savedSelection)
        addTextChangedListener(watcher)
    }

    fun Context.createProgressDialog() = MaterialAlertDialogBuilder(this)
        .setView(R.layout.dialog_progress)
        .setCancelable(false)
        .create()
}