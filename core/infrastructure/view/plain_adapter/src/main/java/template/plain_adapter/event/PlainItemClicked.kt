package template.plain_adapter.event

import template.plain_adapter.item.PlainAdapterItem

data class PlainItemClicked(val item: PlainAdapterItem, val position: Int) : PlainAdapterItemEvent
