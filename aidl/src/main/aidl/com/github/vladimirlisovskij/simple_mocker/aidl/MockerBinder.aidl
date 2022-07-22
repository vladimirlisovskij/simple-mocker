package com.github.vladimirlisovskij.simple_mocker.aidl;

import com.github.vladimirlisovskij.simple_mocker.aidl.MockRequestAidl;
import com.github.vladimirlisovskij.simple_mocker.aidl.MockResponseAidl;



interface MockerBinder {
    MockResponseAidl getMockRequestParams(in MockRequestAidl request);
}