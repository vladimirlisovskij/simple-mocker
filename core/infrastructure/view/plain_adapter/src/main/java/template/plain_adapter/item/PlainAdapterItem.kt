package template.plain_adapter.item

interface PlainAdapterItem {

    val uniqueProperty: Any

    val reusableId: String
        get() = this::class.qualifiedName ?: ""

    fun areItemsTheSame(other: PlainAdapterItem): Boolean =
        this::class == other::class && this.uniqueProperty == other.uniqueProperty

    fun areContentsTheSame(other: PlainAdapterItem): Boolean = this == other

    fun getChangePayload(other: PlainAdapterItem): Any = Unit
}
