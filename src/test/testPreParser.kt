package hinst.HDelphiCrawler.test

import hinst.HDelphiCrawler.*
import java.io.File

class TestPreParser {

	fun go() {
	}

	fun preProcessFile(fileSubPath: String) {
		val pre = testDataSubfolder + File.separator + fileSubPath
		val filePath = pre + ".pas"
		val processedFilePath = pre + "_pd.pas"
		val processedStoredFilePath = pre + "_psd.pas"
		val preParser = PreParser()
		preParser.parseFile(filePath)
		val reversedText = debugReversePreParser(preParser)
		writeStringToFile(processedFilePath, reversedText, Charsets.UTF_8)
	}

}