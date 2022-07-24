package template.plain_adapter.delegate

import template.plain_adapter.item.PlainAdapterItem

interface PlainAdapterDelegatesFactory{

    fun create(plainAdapterItem: PlainAdapterItem) : PlainAdapterItemDelegate?
}
