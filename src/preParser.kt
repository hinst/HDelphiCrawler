package hinst.HDelphiCrawler

import hinst.HDelphiCrawler.Pascal
import java.util.*

class PreParser () {

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
	object textInfo {
		val lines = TextLineInfo()
		val comments = TextCommentInfo()
		val files = TextFileInfo()
		fun seal() {
			lines.seal()
			comments.seal()
			files.seal()
		}
	}
	public val includeFileSearchPath: MutableList<String> = LinkedList<String>()

	public fun parseFile(filePath: String) {
		this.filePath = filePath
		val text = readFile(filePath, Charsets.UTF_8)
		parseText(text)
	}

	fun parseText(text: String) {
		for (character in text)
			parse(character)
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
			if (previousCharacterIsOpenCurlyBrace)
				if (character == '$')
					insideCompilerDirective = true
				else
					insideComment = true
			previousCharacterIsOpenBrace = character == '('
			previousCharacterIsOpenCurlyBrace = character == '{'
		}
		textInfo.comments.add(text.length() - 1, insideComment)
	}

}