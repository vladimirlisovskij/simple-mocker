package com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes layout: Int): Fragment(layout)

