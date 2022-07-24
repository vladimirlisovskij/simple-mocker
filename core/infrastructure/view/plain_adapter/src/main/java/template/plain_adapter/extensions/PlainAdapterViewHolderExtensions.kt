package template.plain_adapter.extensions

import androidx.recyclerview.widget.RecyclerView
import template.plain_adapter.adapter.PlainAdapter
import template.plain_adapter.item.PlainAdapterItem

object PlainAdapterViewHolderExtensions {

    inline fun RecyclerView.ViewHolder.items(): List<PlainAdapterItem>? {
        val parent = itemView.parent as? RecyclerView ?: return null
        val adapter = parent.adapter as? PlainAdapter ?: return null
        return adapter.currentList
    }

    inline fun <reified T : PlainAdapterItem> RecyclerView.ViewHolder.withAdapterPosition(
        block: (item: T, position: Int) -> Unit
    ) {
        with(bindingAdapterPosition) {
            if (this != RecyclerView.NO_POSITION) {
                val items = items() ?: return
                val realPosition = this % items.size
                if (realPosition >= 0 && realPosition < items.size) {
                    block.invoke(items[realPosition] as T, realPosition)
                }
            }
        }
    }
}