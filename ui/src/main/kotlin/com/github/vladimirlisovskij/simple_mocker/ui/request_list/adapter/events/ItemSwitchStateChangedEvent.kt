package com.github.vladimirlisovskij.simple_mocker.ui.request_list.adapter.events

import template.plain_adapter.event.PlainAdapterItemEvent

class ItemSwitchStateChangedEvent(val id: Long, val isEnable: Boolean): PlainAdapterItemEvent