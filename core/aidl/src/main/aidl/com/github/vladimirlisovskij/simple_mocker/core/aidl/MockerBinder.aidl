package com.github.vladimirlisovskij.simple_mocker.core.aidl;

import com.github.vladimirlisovskij.simple_mocker.core.aidl.MockRequestAidl;
import com.github.vladimirlisovskij.simple_mocker.core.aidl.MockResponseAidl;



interface MockerBinder {
    MockResponseAidl getMockRequestParams(in MockRequestAidl request);
}