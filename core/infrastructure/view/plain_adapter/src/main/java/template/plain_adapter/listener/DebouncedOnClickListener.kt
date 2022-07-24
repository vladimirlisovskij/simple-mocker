package template.plain_adapter.listener

import android.os.SystemClock
import android.view.View
import java.util.*
import kotlin.math.abs

abstract class DebouncedOnClickListener : View.OnClickListener {
	private val minimumIntervalMillis: Long = 1000
	private val lastClickMap: WeakHashMap<View, Long> = WeakHashMap()

	/**
	 * Implement this in your subclass instead of onClick
	 *
	 * @param v The view that was clicked
	 */
	abstract fun onDebouncedClick(v: View?)

	override fun onClick(clickedView: View?) {
		val previousClickTimestamp = lastClickMap[clickedView]
		val currentTimestamp = SystemClock.uptimeMillis()
		lastClickMap[clickedView] = currentTimestamp
		if (previousClickTimestamp == null || abs(currentTimestamp - previousClickTimestamp) > minimumIntervalMillis) {
			onDebouncedClick(clickedView)
		}
	}
}