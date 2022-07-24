package template.plain_adapter.delegate

import androidx.recyclerview.widget.RecyclerView
import template.plain_adapter.item.PlainAdapterItem

abstract class PlainAdapterDelegate<T: PlainAdapterItem, V: RecyclerView.ViewHolder>: PlainAdapterItemDelegate() {

    final override fun onBindViewHolder(
        items: List<PlainAdapterItem>,
        position: Int,
        holder: RecyclerView.ViewHolder
    ) {
        bind(items, items[position] as T, position, holder as V)
    }

    final override fun onBindViewHolder(
        items: List<PlainAdapterItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payload: Any
    ) {
        bind(items, items[position] as T, position, holder as V, payload)
    }

    final override fun onBindViewHolder(
        items: List<PlainAdapterItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: List<Any>
    ) {
        super.onBindViewHolder(items, position, holder, payloads)
    }

    open fun bind(items: List<PlainAdapterItem>, item: T, position: Int, holder: V) {}

    open fun bind(items: List<PlainAdapterItem>, item: T, position: Int, holder: V, payload: Any) {}
}
