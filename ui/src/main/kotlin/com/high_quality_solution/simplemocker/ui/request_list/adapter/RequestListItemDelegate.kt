package com.high_quality_solution.simplemocker.ui.request_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.high_quality_solution.simplemocker.ui.databinding.ItemRequestInfoBinding
import com.high_quality_solution.simplemocker.ui.request_list.adapter.events.ItemClickedEvent
import com.high_quality_solution.simplemocker.ui.request_list.adapter.events.ItemSwitchStateChangedEvent
import template.plain_adapter.delegate.PlainAdapterDelegate
import template.plain_adapter.holder.PlainAdapterBaseViewHolder
import template.plain_adapter.item.PlainAdapterItem

class RequestListItemDelegate() :
    PlainAdapterDelegate<RequestListItem, RequestListItemDelegate.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRequestInfoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun bind(
        items: List<PlainAdapterItem>,
        item: RequestListItem,
        position: Int,
        holder: ViewHolder
    ) {
        holder.bind(item)
    }

    override fun bind(
        items: List<PlainAdapterItem>,
        item: RequestListItem,
        position: Int,
        holder: ViewHolder,
        payload: Any
    ) {
        if (payload is RequestListItem.SwitchStateChanged) {
            holder.updateSwitchState(payload)
        } else {
            super.bind(items, item, position, holder, payload)
        }
    }

    inner class ViewHolder(
        private val binding: ItemRequestInfoBinding
    ) : PlainAdapterBaseViewHolder(binding.root) {
        private var switchListener: CompoundButton.OnCheckedChangeListener? = null

        fun bind(data: RequestListItem) {
            with(binding) {
                smIsEnabled.setOnCheckedChangeListener(null)
                tvRequest.text = data.request
                smIsEnabled.isChecked = data.isChecked
                root.setOnClickListener { sendEvent(ItemClickedEvent(data.id)) }
                switchListener = CompoundButton.OnCheckedChangeListener { _, isChecked ->
                    sendEvent(
                        ItemSwitchStateChangedEvent(data.id, isChecked)
                    )
                }

                smIsEnabled.setOnCheckedChangeListener(switchListener)
            }
        }

        fun updateSwitchState(data: RequestListItem.SwitchStateChanged) {
            with(binding.smIsEnabled) {
                setOnCheckedChangeListener(null)
                isChecked = data.isEnable
                setOnCheckedChangeListener(switchListener)
            }
        }
    }
}

