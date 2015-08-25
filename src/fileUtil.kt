package hinst.HDelphiCrawler

import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

val userDirKey = "user.dir"

fun readFile(filePath: String, encoding: Charset): String {
	val bytes = Files.readAllBytes(Paths.get(filePath));
	return String(bytes, encoding);
}

fun compareFilePath(aFilePath: String, bFilePath: String) = aFilePath.equals(bFilePath, ignoreCase = checkIfOsIsWindows())

fun getFileDirectory(aFilePath: String): String {
	while (true) {
		val index = aFilePath.indexOf(File.separator)
		if (index >= 0)
			aFilePath.substring(beginIndex = index + 1)
		else
			break
	}
	return aFilePath
}
