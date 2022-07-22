package com.high_quality_solution.simplemocker.ui.request_editor

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.high_quality_solution.simplemocker.ui.R
import com.high_quality_solution.simplemocker.ui.databinding.ScreenRequestEditorBinding
import com.high_quality_solution.simplemocker.ui.di.UIComponent
import com.high_quality_solution.simplemocker.ui.utils.Constants
import com.high_quality_solution.simplemocker.ui.utils.Ext.simpleViewModels

class RequestEditorFragment : Fragment(R.layout.screen_request_editor) {
    private val viewComponent by lazy(UIComponent::create)
    private val binding by viewBinding(ScreenRequestEditorBinding::bind)
    private val viewModel by simpleViewModels<RequestEditorViewModel> {
        viewComponent
            .requestEditorViewModelFactory()
            .create(arguments?.getLong(ARG_ITEM_ID) ?: Constants.NO_ID)
    }

    private val filePickerLauncher = createFilePickerLauncher()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun createFilePickerLauncher(): ActivityResultLauncher<String> {
        return registerForActivityResult(ActivityResultContracts.GetContent()) { fileUri ->
            fileUri?.let(viewModel::onFileSelected)
        }
    }

    private fun initListeners() {
        with(binding) {
            mbSelectFile.setOnClickListener { filePickerLauncher.launch(JSON_FILE_TYPE) }
        }
    }

    companion object {
        private const val ARG_ITEM_ID = "itemId"
        private const val JSON_FILE_TYPE = "application/json"
    }
}

