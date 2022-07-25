package com.github.vladimirlisovskij.simple_mocker.core.infrastructure.view.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(@LayoutRes layout: Int): AppCompatActivity(layout)