package hinst.HDelphiCrawler

import hinst.HDelphiCrawler.Pascal
import java.util.*

class PreParser() {

	var directive: String = ""
	// { comment }
	var insideCurlyComment = false
	// (* comment *)
	var insideRoundComment = false
	// // comment
	var insideSlashyComment = false
	var insideComment = false
		get() = insideCurlyComment || insideRoundComment || insideSlashyComment
	private val textBuilder: StringBuilder = StringBuilder()
	val text: String
		get() = textBuilder.toString()
	var filePath: String = ""
	var previousCharacterIsOpenBrace = false
	var previousCharacterIsOpenCurlyBrace = false
	var insideCompilerDirective = false
	var previousCharacterIsAsterisk = false

	val lines = TextLineInfo()
	val comments = TextCommentInfo()
	val files = TextFileInfo()
	fun seal() {
		lines.seal()
		comments.seal()
		files.seal()
	}

	public val includeFileSearchPath: MutableList<String> = LinkedList<String>()

	public fun parseFile(filePath: String) {
		this.filePath = filePath
		val text = readFileToString(filePath, Charsets.UTF_8)
		parseText(text)
	}

	fun parseText(text: String) {
		for (character in text)
			parse(character)
	}

	fun parse(character: Char) {
		if (insideComment) {
			textBuilder.append(character)
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
				if (character == '$') {
					insideCompilerDirective = true
				} else {
					insideComment = true
					textBuilder.append('{')
				}
			if (character != '{')
				textBuilder.append(character);
			previousCharacterIsOpenBrace = character == '('
			previousCharacterIsOpenCurlyBrace = character == '{'
		}
		comments.add(textBuilder.length() - 1, insideComment)
	}

}