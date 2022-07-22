package com.github.vladimirlisovskij.simple_mocker.ui.request_editor

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.vladimirlisovskij.simple_mocker.ui.R
import com.github.vladimirlisovskij.simple_mocker.ui.databinding.ScreenRequestEditorBinding
import com.github.vladimirlisovskij.simple_mocker.ui.di.UiDiResolver
import com.github.vladimirlisovskij.simple_mocker.ui.utils.Constants
import com.github.vladimirlisovskij.simple_mocker.ui.utils.Ext.createAfterTextChangedTextWatcher
import com.github.vladimirlisovskij.simple_mocker.ui.utils.Ext.createProgressDialog
import com.github.vladimirlisovskij.simple_mocker.ui.utils.Ext.setTextWithoutTextWatcher
import com.github.vladimirlisovskij.simple_mocker.ui.utils.Ext.simpleViewModels
import kotlinx.coroutines.launch

class RequestEditorFragment : Fragment(R.layout.screen_request_editor) {
    private val viewComponent by lazy(UiDiResolver::getApi)
    private val binding by viewBinding(ScreenRequestEditorBinding::bind)
    private val viewModel by simpleViewModels<RequestEditorViewModel> {
        viewComponent
            .requestEditorViewModelFactory()
            .create(arguments?.getLong(ARG_ITEM_ID) ?: Constants.NO_ID)
    }

    private val filePickerLauncher = createFilePickerLauncher()
    private val hostFieldWatcher by lazy { createAfterTextChangedTextWatcher(viewModel::onHostChanged) }
    private val pathFieldWatcher by lazy { createAfterTextChangedTextWatcher(viewModel::onPathChanged) }
    private val argsFieldWatcher by lazy { createAfterTextChangedTextWatcher(viewModel::onParamsChanged) }
    private val progressDialog by lazy { requireContext().createProgressDialog() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
        viewModel.onViewCreated()
    }

    private fun createFilePickerLauncher(): ActivityResultLauncher<String> {
        return registerForActivityResult(ActivityResultContracts.GetContent()) { fileUri ->
            fileUri?.let(viewModel::onFileSelected)
        }
    }

    private fun initListeners() {
        with(binding) {
            mbSelectFile.setOnClickListener { filePickerLauncher.launch(Constants.JSON_MIME) }
            mbViewFile.setOnClickListener { viewModel.onFileShowClicked() }
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
            toolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.actionSave -> viewModel.onSaveClicked()
                }

                true
            }
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.screenState.collect(::updateViewState)
                }

                launch {
                    viewModel.screenEvent.collect { event ->
                        when (event) {
                            is RequestEditorViewModel.ShowFileEvent -> startJsonViewerActivity(event.intent)

                            is RequestEditorViewModel.ToastEvent -> Toast.makeText(
                                requireContext(),
                                event.text,
                                Toast.LENGTH_LONG
                            ).show()

                            is RequestEditorViewModel.NavigationBackEvent -> requireActivity().onBackPressed()
                        }
                    }
                }
            }
        }
    }

    private fun startJsonViewerActivity(intent: Intent) {
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast
                .makeText(requireContext(), R.string.error_acivity_not_found, Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun updateViewState(state: RequestEditorViewModel.ViewState) {
        with(binding) {
            etArgs.setTextWithoutTextWatcher(argsFieldWatcher, state.params)
            etHost.setTextWithoutTextWatcher(hostFieldWatcher, state.host)
            etPath.setTextWithoutTextWatcher(pathFieldWatcher, state.path)
            mbViewFile.isVisible = state.isShowButtonVisible
            tvFileName.text = state.fileName
        }

        if (state.isLoading) progressDialog.show()
        else progressDialog.dismiss()
    }

    companion object {
        private const val ARG_ITEM_ID = "itemId"
    }
}

