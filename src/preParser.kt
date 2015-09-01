package hinst.HDelphiCrawler

import hinst.HDelphiCrawler.Pascal
import java.util.*

class PreParser() : HasLog {

	var directive: String = ""
	// { comment }
	var insideCurlyComment = false
	// (* comment *)
	var insideRoundComment = false
	// // comment
	var insideSlashyComment = false
	val insideComment: Boolean
		get() = insideCurlyComment || insideRoundComment || insideSlashyComment
	var wasInsideComment: Boolean = false
	private val textBuilder: StringBuilder = StringBuilder()
	var text: String = ""
	var filePath: String = ""
	var previousCharacterIsOpenBrace = false
	var previousCharacterIsOpenCurlyBrace = false
	var insideCompilerDirective = false
	var previousCharacterIsAsterisk = false

	val lines = TextLineInfo()
	val comments = TextCommentInfo()
	val files = TextFileInfo()
	fun seal() {
		text = textBuilder.toString()
		lines.seal()
		comments.seal()
		files.seal()
	}

	public val includeFileSearchPath: MutableList<String> = LinkedList<String>()

	public fun parseFile(filePath: String) {
		this.filePath = filePath
		val text = readFileToString(filePath, Charsets.UTF_8)
		parseText(text!!)
		seal()
	}

	fun parseText(text: String) {
		for (character in text)
			parse(character)
	}

	fun parse(character: Char) {
		fun processCommentExit() {
			if (wasInsideComment && false == insideComment) {
				comments.push(textBuilder.length() - 1, insideComment)
				wasInsideComment = false
			}
		}
		if (insideComment) {
			textBuilder.append(character)
			if (insideCurlyComment && character == '}') {
				insideCurlyComment = false
				wasInsideComment = true
			}
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
					insideCurlyComment = true
					textBuilder.append('{')
					comments.push(textBuilder.length() - 1, insideComment)
				}
			if (character != '{') {
				textBuilder.append(character)
				processCommentExit()
			}
			previousCharacterIsOpenBrace = character == '('
			previousCharacterIsOpenCurlyBrace = character == '{'
		}
	}

}