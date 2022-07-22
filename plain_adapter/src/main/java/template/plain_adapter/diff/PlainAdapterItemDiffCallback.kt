package template.plain_adapter.diff

import androidx.recyclerview.widget.DiffUtil
import template.plain_adapter.item.PlainAdapterItem

class PlainAdapterItemDiffCallback : DiffUtil.ItemCallback<PlainAdapterItem>() {

    override fun areItemsTheSame(oldItem: PlainAdapterItem, newItem: PlainAdapterItem): Boolean =
        oldItem.areItemsTheSame(newItem)

    override fun areContentsTheSame(oldItem: PlainAdapterItem, newItem: PlainAdapterItem): Boolean =
        oldItem.areContentsTheSame(newItem)

    override fun getChangePayload(oldItem: PlainAdapterItem, newItem: PlainAdapterItem): Any =
        oldItem.getChangePayload(newItem)
}
