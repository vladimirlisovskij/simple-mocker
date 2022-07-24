package template.plain_adapter.adapter

import androidx.recyclerview.widget.RecyclerView
import template.plain_adapter.delegate.PlainAdapterDelegatesFactory
import template.plain_adapter.item.PlainAdapterItem
import template.plain_adapter.listener.PlainAdapterItemEventListener

class InfinitePlainAdapter(
    factory: PlainAdapterDelegatesFactory,
    externalListener: PlainAdapterItemEventListener? = null
) : PlainAdapter(factory, externalListener) {

    override fun getItemCount(): Int = Integer.MAX_VALUE

    override fun getItem(position: Int): PlainAdapterItem = super.getItem(getRealPosition(position))

    override fun getItemViewType(position: Int): Int = super.getItemViewType(getRealPosition(position))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, getRealPosition(position))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        super.onBindViewHolder(holder, getRealPosition(position), payloads)
    }

    fun getRealPosition(position: Int): Int = position % currentList.size
}