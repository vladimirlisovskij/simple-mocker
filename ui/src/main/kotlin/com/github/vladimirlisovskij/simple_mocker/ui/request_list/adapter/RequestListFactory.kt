package com.github.vladimirlisovskij.simple_mocker.ui.request_list.adapter

import template.plain_adapter.delegate.PlainAdapterDelegatesFactory
import template.plain_adapter.delegate.PlainAdapterItemDelegate
import template.plain_adapter.item.PlainAdapterItem

class RequestListFactory: PlainAdapterDelegatesFactory {
    override fun create(plainAdapterItem: PlainAdapterItem): PlainAdapterItemDelegate? {
        return when(plainAdapterItem) {
            is RequestListItem -> RequestListItemDelegate()
            else -> null
        }
    }
}