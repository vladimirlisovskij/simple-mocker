package com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.di.modules

import com.github.vladimirlisovskij.simple_mocker.feature.request_list.impl.view.RequestListFragment
import dagger.Module
import dagger.Provides

@Module
internal class FragmentModule {
    @Provides
    fun provideMainFragment() = RequestListFragment.newInstance()
}

