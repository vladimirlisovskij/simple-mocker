package template.plain_adapter.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

open class PlainAdapterBaseViewHolder : RecyclerView.ViewHolder {

    constructor(view: View) : super(view)

    constructor(
        parent: ViewGroup,
        @LayoutRes layoutId: Int
    ) : super(LayoutInflater.from(parent.context).inflate(layoutId, parent, false))
}
