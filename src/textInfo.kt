package hinst.HDelphiCrawler

import java.util.*

open class TextInfoBlock(val position: Int) {
	open fun valueEquals(block: TextInfoBlock): Boolean = false
}

open class TextInfo<TBlock: TextInfoBlock> {
	var items: MutableList<TBlock> = LinkedList()
	fun add(block: TBlock) {
		if (items.count() == 0) {
			items.add(block)
		} else {
			val lastBlock = items.last()
			if (false == block.valueEquals(lastBlock)) {
				items.add(block)
			}
		}
	}
}

class TextLineInfoBlock(position: Int, val lineNumber: Int) : TextInfoBlock(position)

class TextLineInfo: TextInfo<TextLineInfoBlock>() {
	override fun valueEquals(block: TextLineInfo) {

	}
}

class TextCommentInfoBlock(position: Int, val isComment: Boolean) : TextInfoBlock(position)
class TextFileInfoBlock(position: Int, val filePath: String): TextInfoBlock(position)
