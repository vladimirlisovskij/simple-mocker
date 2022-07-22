package com.github.vladimirlisovskij.simple_mocker.ui.request_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.vladimirlisovskij.simple_mocker.ui.R
import com.github.vladimirlisovskij.simple_mocker.ui.databinding.ScreenRequestListBinding
import com.github.vladimirlisovskij.simple_mocker.ui.di.UiDiResolver
import com.github.vladimirlisovskij.simple_mocker.ui.request_list.adapter.RequestListFactory
import com.github.vladimirlisovskij.simple_mocker.ui.request_list.adapter.events.ItemClickedEvent
import com.github.vladimirlisovskij.simple_mocker.ui.request_list.adapter.events.ItemDeleteClickedEvent
import com.github.vladimirlisovskij.simple_mocker.ui.request_list.adapter.events.ItemSwitchStateChangedEvent
import com.github.vladimirlisovskij.simple_mocker.ui.utils.Ext.simpleViewModels
import kotlinx.coroutines.launch
import template.plain_adapter.adapter.PlainAdapter

class RequestListFragment : Fragment(R.layout.screen_request_list) {
    private val viewComponent by lazy(UiDiResolver::getApi)
    private val binding by viewBinding(ScreenRequestListBinding::bind)
    private val viewModel by simpleViewModels<RequestListViewModel> {
        viewComponent.requestListViewModel()
    }

    private val adapter by lazy { PlainAdapter(RequestListFactory()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initListeners()
        initObservers()
    }

    private fun initRecycler() {
        binding.rvRequestList.adapter = adapter
    }

    private fun initListeners() {
        adapter.setItemEventListener { event ->
            when (event) {
                is ItemClickedEvent -> viewModel.onItemClicked(event.id)
                is ItemDeleteClickedEvent -> viewModel.onItemRemoveClicked(event.id)
                is ItemSwitchStateChangedEvent -> viewModel.onItemEnabledStateChanged(event.id, event.isEnable)
            }
        }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.actionCreate -> viewModel.onAddRequestClicked()
            }

            true
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.requestsList.collect(adapter::submitList)
                }

                launch {
                    viewModel.screenEvents.collect { event ->
                        when (event) {
                            is RequestListViewModel.ShowRequestEditScreen -> {
                                val direction = RequestListFragmentDirections
                                    .actionRequestListFragmentToRequestEditorFragment(event.requestId)

                                findNavController().navigate(direction)
                            }
                        }
                    }
                }
            }
        }
    }
}

