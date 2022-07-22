package com.high_quality_solution.simplemocker.aidl;

import com.high_quality_solution.simplemocker.aidl.MockRequestAidl;
import com.high_quality_solution.simplemocker.aidl.MockResponseAidl;



interface MockerBinder {
    MockResponseAidl getMockRequestParams(in MockRequestAidl request);
}