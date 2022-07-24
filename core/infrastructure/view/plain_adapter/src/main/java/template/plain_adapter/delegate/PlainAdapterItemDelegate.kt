package template.plain_adapter.delegate

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import template.plain_adapter.event.PlainAdapterItemEvent
import template.plain_adapter.event.PlainItemClicked
import template.plain_adapter.extensions.PlainAdapterViewHolderExtensions.withAdapterPosition
import template.plain_adapter.item.PlainAdapterItem
import template.plain_adapter.listener.DebouncedOnClickListener
import template.plain_adapter.listener.PlainAdapterItemEventListener

abstract class PlainAdapterItemDelegate {

    lateinit var eventListener: PlainAdapterItemEventListener

    abstract fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    open fun onBindViewHolder(
        items: List<PlainAdapterItem>,
        position: Int,
        holder: RecyclerView.ViewHolder
    ) {
    }

    open fun onBindViewHolder(
        items: List<PlainAdapterItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: List<Any>
    ) {
        payloads.forEach { payload ->
            when (payload) {
                is Collection<*> -> payload.forEach { nestedPayload ->
                    nestedPayload?.let {
                        onBindViewHolder(items, position, holder, nestedPayload)
                    }
                }
                else -> onBindViewHolder(items, position, holder, payload)
            }
        }
    }

    open fun onBindViewHolder(
        items: List<PlainAdapterItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payload: Any
    ) {
    }

    open fun onViewRecycled(holder: RecyclerView.ViewHolder) {}

    open fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean = false

    open fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {}

    open fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {}

    fun sendEvent(event: PlainAdapterItemEvent) {
        eventListener.onItemEvent(event)
    }

    inline fun RecyclerView.ViewHolder.setItemViewClickListener() {
        itemView.clicks<PlainAdapterItem>(this) { item, position -> PlainItemClicked(item, position) }
    }

    inline fun RecyclerView.ViewHolder.setItemViewSingleClickListener() {
        itemView.singleClicks<PlainAdapterItem>(this) { item, position -> PlainItemClicked(item, position) }
    }

    inline fun <reified T : PlainAdapterItem> View.clicks(
        holder: RecyclerView.ViewHolder,
        crossinline event: (item: T, position: Int) -> PlainAdapterItemEvent
    ) {
        setOnClickListener {
            holder.withAdapterPosition<T> { item, position ->
                sendEvent(event.invoke(item, position))
            }
        }
    }

    inline fun <reified T : PlainAdapterItem> View.singleClicks(
        holder: RecyclerView.ViewHolder,
        crossinline event: (item: T, position: Int) -> PlainAdapterItemEvent
    ) {

        setOnClickListener(object: DebouncedOnClickListener() {
            override fun onDebouncedClick(v: View?) {
                holder.withAdapterPosition<T> { item, position ->
                    sendEvent(event.invoke(item, position))
                }
            }
        })
    }
}
