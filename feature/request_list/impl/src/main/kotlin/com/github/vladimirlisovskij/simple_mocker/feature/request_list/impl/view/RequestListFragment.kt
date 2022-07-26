package com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.view

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.base.BaseFragment
import com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.R
import com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.databinding.ScreenRequestListBinding
import com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.di.DaggerRequestListImplComponent
import com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.di.RequestListImplDependencies
import com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.view_model.RequestListViewModel

class RequestListFragment : BaseFragment(R.layout.screen_request_list) {
    private val binding by viewBinding(ScreenRequestListBinding::bind)
    private val dependencies by lazy {
        DaggerRequestListImplComponent.factory().create(RequestListImplDependencies.get())
    }

    private val navigationFactory by lazy { dependencies.navigationFactory }

    private val viewModel by viewModels<RequestListViewModel>(
        factoryProducer = { dependencies.viewModelFactory }
    )

    companion object {
        fun newInstance() = RequestListFragment()
    }
}