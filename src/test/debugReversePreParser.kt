package hinst.HDelphiCrawler.test

import hinst.HDelphiCrawler.*

fun debugReversePreParser(a: PreParser): String {
	val outputText = StringBuilder()
	val sourceText = a.text
	var commentStatus = false
	for (i in sourceText.indices) {
		val newCommentStatus = a.comments[i]
		if (commentStatus != newCommentStatus) {
			outputText.append(if (newCommentStatus) "{comment.start}" else "{comment.end")
			commentStatus = newCommentStatus
		}
	}
	return outputText.toString()
}