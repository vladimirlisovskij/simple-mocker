package template.plain_adapter.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import template.plain_adapter.exception.PlainAdapterDelegateNotFoundException
import template.plain_adapter.item.PlainAdapterItem
import template.plain_adapter.listener.PlainAdapterItemEventListener

class PlainAdapterDelegatesManager(
    private val plainAdapterItemEventListener: PlainAdapterItemEventListener,
    private val plainAdapterDelegatesFactory: PlainAdapterDelegatesFactory
) {

    private val delegates = mutableMapOf<Int, PlainAdapterItemDelegate>()

    private fun createDelegateIfNeeded(
        item: PlainAdapterItem,
        onCreate: ((PlainAdapterItemDelegate) -> Unit)? = null
    ): PlainAdapterItemDelegate {
        val viewType = viewTypeFromItem(item)
        var delegate = delegates[viewType]
        if (delegate == null) {
            delegate = plainAdapterDelegatesFactory.create(item)

            requireNotNull(delegate) {
                "No delegate defined for ${item::class.qualifiedName} at ${plainAdapterDelegatesFactory::class.qualifiedName}"
            }

            onCreate?.invoke(delegate)
            delegates[viewType] = delegate
        }
        return delegate
    }

    private fun getDelegateOrThrow(viewType: Int): PlainAdapterItemDelegate =
        delegates[viewType] ?: throw PlainAdapterDelegateNotFoundException()

    private fun viewTypeFromItem(plainAdapterItem: PlainAdapterItem): Int =
        plainAdapterItem.reusableId.hashCode()

    fun getItemViewTypeAndCreateDelegate(items: List<PlainAdapterItem>, position: Int): Int {
        val item = items[position]
        createDelegateIfNeeded(item) { delegate ->
            delegate.eventListener = plainAdapterItemEventListener
        }
        return viewTypeFromItem(item)
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getDelegateOrThrow(viewType).onCreateViewHolder(parent)

    fun onBindViewHolder(
        items: List<PlainAdapterItem>,
        position: Int,
        vh: RecyclerView.ViewHolder
    ) = getDelegateOrThrow(vh.itemViewType).onBindViewHolder(items, position, vh)

    fun onBindViewHolder(
        items: List<PlainAdapterItem>,
        position: Int,
        vh: RecyclerView.ViewHolder,
        payloads: List<Any>
    ) = getDelegateOrThrow(vh.itemViewType).onBindViewHolder(items, position, vh, payloads)

    fun onViewRecycled(vh: RecyclerView.ViewHolder) =
        getDelegateOrThrow(vh.itemViewType).onViewRecycled(vh)

    fun onFailedToRecycleView(vh: RecyclerView.ViewHolder): Boolean =
        getDelegateOrThrow(vh.itemViewType).onFailedToRecycleView(vh)

    fun onViewAttachedToWindow(vh: RecyclerView.ViewHolder) =
        getDelegateOrThrow(vh.itemViewType).onViewAttachedToWindow(vh)

    fun onViewDetachedFromWindow(vh: RecyclerView.ViewHolder) =
        getDelegateOrThrow(vh.itemViewType).onViewDetachedFromWindow(vh)
}
