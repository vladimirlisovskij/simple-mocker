package template.plain_adapter.listener

import template.plain_adapter.event.PlainAdapterItemEvent

fun interface PlainAdapterItemEventListener {
    fun onItemEvent(plainAdapterItemEvent: PlainAdapterItemEvent)
}
