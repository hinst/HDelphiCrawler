package hinst.HDelphiCrawler.test

import hinst.HDelphiCrawler.*
import java.io.File

class TestPreParser : HasLog {

	fun go() {
		preProcessFile("only_cc")
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
		getLogger().info(fileSubPath)
		val pre = initialWorkingDirectory + File.separator + testDataSubfolder + File.separator + fileSubPath
		val filePath = pre + ".pas"
		val processedFilePath = pre + "_pd.pas"
		val processedStoredFilePath = pre + "_psd.pas"
		val preParser = PreParser()
		preParser.parseFile(filePath)
		val reversedText = debugReversePreParser(preParser)
		writeStringToFile(processedFilePath, reversedText, Charsets.UTF_8)
	}

}