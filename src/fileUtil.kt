package hinst.HDelphiCrawler

import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

val userDirKey = "user.dir"
val initialWorkingDirectory = System.getProperty(userDirKey)

fun readFileToString(filePath: String, encoding: Charset): String? {
	var result: String? = null
	var bytes: ByteArray? = null
	try {
		bytes = Files.readAllBytes(Paths.get(filePath));
	} catch (e: java.nio.file.NoSuchFileException) {
	}
	if (bytes != null) {
		result = String(bytes, encoding);
	}
	return result
}

fun writeStringToFile(filePath: String, text: String, encoding: Charset) {
	val bytes = text.toByteArray(encoding)
	Files.write(Paths.get(filePath), bytes)
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
