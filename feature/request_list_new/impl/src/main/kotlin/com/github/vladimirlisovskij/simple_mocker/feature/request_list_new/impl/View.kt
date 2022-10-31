package com.github.vladimirlisovskij.simple_mocker.feature.request_list_new.impl

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
// import androidx.compose.material3.Icon
// import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.github.vladimirlisovskij.simple_mocker.core.design.AppTheme

@Composable
fun ItemRequestInfo(
    text: String,
    isEnabled: Boolean,
    onEnableChange: (Boolean) -> Unit
) {
    Row {
        // Text(text = text)
        // Icon(imageVector = Icons.Default.Delete, contentDescription = null)
        // Switch(checked = isEnabled, onCheckedChange = null)
    }
}

@Preview
@Composable
fun ItemRequestInfoPreview() {
    AppTheme {
        var state by remember { mutableStateOf(false) }
        ItemRequestInfo(
            text = "*/my/custom/request",
            isEnabled = true,
            onEnableChange = { state = it; println(it) }
        )
    }
}
