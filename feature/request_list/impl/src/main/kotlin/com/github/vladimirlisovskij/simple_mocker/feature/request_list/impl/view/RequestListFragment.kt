package com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.view

import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.base.BaseFragment
import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.navigation_factory.NavigationFactory
import com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view_model_factory.ViewModelFactory
import com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.R
import com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.databinding.ScreenRequestListBinding
import javax.inject.Inject

class RequestListFragment: BaseFragment(R.layout.screen_request_list) {
    private val binding by viewBinding(ScreenRequestListBinding::bind)

    @Inject
    lateinit var navigationFactory: NavigationFactory

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

//    private val viewModel by viewModels()

    companion object {
        fun newInstance() = RequestListFragment()
    }
}