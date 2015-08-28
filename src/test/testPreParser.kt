package hinst.HDelphiCrawler.test

import hinst.HDelphiCrawler.*
import java.io.File

class TestPreParser : HasLog {

	fun go() {
		preProcessFile("only_cc")
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