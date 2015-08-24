package hinst.HDelphiCrawler

import java.util.*

class TextLineInfo {

	data class Item(val position: Int, val lineNumber: Int)
	class SealedException(val sealed: Boolean): Exception(sealed.toString())

	val itemList: MutableList<Item> = LinkedList<Item>()
	var items: Array<Item> = emptyArray()

	public fun add(item: Item) {
		if (itemList.count() == 0 || itemList.last().lineNumber != item.lineNumber) {
			itemList.add(item)
		}
	}

	public fun seal() {
		items = itemList.toTypedArray()
		itemList.clear()
	}

	// Get line number
	public fun get(position: Int): Int {
		var result = 0
		for (item in items) {
			if (item.position <= position)
				result = 0
			else
				break
		}
		return result
	}

}