package hinst.HDelphiCrawler

import java.util.*

abstract class TextInfo<T> {

	public inner data class Item(val position: Int, val value: T)
	class SealedException(val sealed: Boolean): Exception(sealed.toString())

	val itemList: MutableList<Item> = LinkedList<Item>()
	var items: Array<Item> = emptyArray()

	public fun add(item: Item) {
		if (itemList.count() == 0 || itemList.last().equals(item)) {
			itemList.add(item)
		}
	}

	public fun add(position: Int, value: T) {
		add(Item(position, value))
	}

	public fun seal() {
		items = itemList.toTypedArray()
		itemList.clear()
	}

	protected abstract fun getDefaultValue(): T

	// Get line number
	public fun get(position: Int): T {
		var result = getDefaultValue()
		for (item in items) {
			if (item.position <= position)
				result = item.value
			else
				break
		}
		return result
	}

}

class TextLineInfo : TextInfo<Int>() {
	override fun getDefaultValue(): Int = 0
}
class TextCommentInfo : TextInfo<Boolean>() {
	override fun getDefaultValue(): Boolean = false
}
class TextFileInfo : TextInfo<String>() {
	override fun getDefaultValue(): String = ""
}