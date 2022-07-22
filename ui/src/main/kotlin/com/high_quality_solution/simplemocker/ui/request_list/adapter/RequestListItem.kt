package com.high_quality_solution.simplemocker.ui.request_list.adapter

import template.plain_adapter.item.PlainAdapterItem

data class RequestListItem(
    val id: Long,
    val request: String,
    val isChecked: Boolean
) : PlainAdapterItem {
    override val uniqueProperty = "RequestListItem $id"

    override fun getChangePayload(other: PlainAdapterItem): Any {
        return if (other is RequestListItem && other.isChecked != isChecked) {
            SwitchStateChanged(other.isChecked)
        } else {
            super.getChangePayload(other)
        }
    }

    class SwitchStateChanged(val isEnable: Boolean)
}
