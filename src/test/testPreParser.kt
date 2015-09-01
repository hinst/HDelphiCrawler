package hinst.HDelphiCrawler.test

import hinst.HDelphiCrawler.*
import java.io.File

class TestPreParser : HasLog {

	fun go() {
		preProcessFile("only_cc")
		preProcessFile("double_cc")
		preProcessFile("two_cc")
		preProcessFile("more_cc")
	}

	fun debugReversePreParser(a: PreParser): String {
		val outputText = StringBuilder()
		val sourceText = a.text
		var commentStatus = false
		for (i in sourceText.indices) {
			val newCommentStatus = a.comments[i]
			if (commentStatus != newCommentStatus) {
				outputText.append(if (newCommentStatus) "{comment.start}" else "{comment.end}")
				commentStatus = newCommentStatus
			}
			outputText.append(sourceText[i])
		}
		return outputText.toString()
	}
	fun preProcessFile(fileSubPath: String) {
		val pre = initialWorkingDirectory + File.separator + testDataSubfolder + File.separator + fileSubPath
		val filePath = pre + ".pas"
		val processedFilePath = pre + "_pd.pas"
		val processedStoredFilePath = pre + "_psd.pas"
		val preParser = PreParser()
		preParser.parseFile(filePath)
		val processedText = debugReversePreParser(preParser)
		writeStringToFile(processedFilePath, processedText, Charsets.UTF_8)
		val processedStoredText = readFileToString(processedStoredFilePath, Charsets.UTF_8)
		assert(processedStoredText != null, "for '" + fileSubPath + "' file: no stored content file '" + processedStoredFilePath + "'")
		val matched = processedStoredText == processedText
		getLogger().info(fileSubPath + " matched: " + matched)
		assert(matched, "file content mismatch '" + fileSubPath + "' compared to stored file")
	}

}