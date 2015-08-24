package hinst.HDelphiCrawler

import hinst.HDelphiCrawler.Pascal

class PreParser (includeFileSearchPath: Array<String>) {

	var word: String = ""
	// { comment }
	var insideCurlyComment = false
	// (* comment *)
	var insideRoundComment = false
	// // comment
	var insideSlashyComment = false
	var insideComment = false
		get() = insideCurlyComment || insideRoundComment || insideSlashyComment
	val text: StringBuilder = StringBuilder()
	var filePath: String = ""
	var previousCharacterIsOpenBrace = false

	public fun parseFile(filePath: String) {
		this.filePath = filePath
	}

	fun parseText(text: String) {
	}

	fun parse(character: Char) {
		if (insideComment) {
			text.append(character)
		} else {
			if (character == '{')
				insideCurlyComment = true
			if (previousCharacterIsOpenBrace && character == '*')
				insideRoundComment = true
			previousCharacterIsOpenBrace = character == '('
		}
	}

}