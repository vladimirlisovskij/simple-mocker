package template.plain_adapter.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import template.plain_adapter.delegate.PlainAdapterDelegatesFactory
import template.plain_adapter.delegate.PlainAdapterDelegatesManager
import template.plain_adapter.diff.PlainAdapterItemDiffCallback
import template.plain_adapter.event.PlainAdapterItemEvent
import template.plain_adapter.item.PlainAdapterItem
import template.plain_adapter.listener.PlainAdapterItemEventListener

open class PlainAdapter(
    factory: PlainAdapterDelegatesFactory,
    private var externalListener: PlainAdapterItemEventListener? = null
) : ListAdapter<PlainAdapterItem, RecyclerView.ViewHolder>(PlainAdapterItemDiffCallback()) {

    private val eventListenerWrapper: PlainAdapterItemEventListener =
        PlainAdapterItemEventListener { event -> externalListener?.onItemEvent(event) }

    private val manager = PlainAdapterDelegatesManager(eventListenerWrapper, factory)

    fun setItemEventListener(listener: PlainAdapterItemEventListener) {
        externalListener = listener
    }

    override fun getItemViewType(position: Int): Int =
        manager.getItemViewTypeAndCreateDelegate(currentList, position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        manager.onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        manager.onBindViewHolder(currentList, position, holder)

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: List<Any>
    ) {
        if (payloads.isEmpty()) manager.onBindViewHolder(currentList, position, holder)
        else manager.onBindViewHolder(currentList, position, holder, payloads)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) =
        manager.onViewAttachedToWindow(holder)

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) =
        manager.onViewDetachedFromWindow(holder)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) =
        manager.onViewRecycled(holder)

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean =
        manager.onFailedToRecycleView(holder)
}
