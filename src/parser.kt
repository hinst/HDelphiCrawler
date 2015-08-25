package hinst.HDelphiCrawler

import hinst.HDelphiCrawler.Pascal

class PreParser (includeFileSearchPath: Array<String>) {

	var directive: String = ""
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
	var previousCharacterIsOpenCurlyBrace = false
	var insideCompilerDirective = false
	var previousCharacterIsAsterisk = false

	public fun parseFile(filePath: String) {
		this.filePath = filePath

	}

	fun parseText(text: String) {
	}

	fun parse(character: Char) {
		if (insideComment) {
			text.append(character)
			if (insideCurlyComment && character == '}')
				insideCurlyComment = false
			if (insideRoundComment && previousCharacterIsAsterisk && character == ')')
				insideRoundComment = false
			if (insideSlashyComment && character == '\n')
				insideSlashyComment = false
			previousCharacterIsAsterisk = character == '*'
		} else {
			if (previousCharacterIsOpenBrace && character == '*')
				insideRoundComment = true
			if (previousCharacterIsOpenCurlyBrace && character == '$')
				insideCompilerDirective = true
			previousCharacterIsOpenBrace = character == '('
			previousCharacterIsOpenCurlyBrace = character == '{'
		}
	}

}