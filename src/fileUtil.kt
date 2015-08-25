package hinst.HDelphiCrawler

import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

val userDirKey = "user.dir"

fun readFile(filePath: String, encoding: Charset): String
{
	val bytes = Files.readAllBytes(Paths.get(filePath));
	return String(bytes, encoding);
}

fun compareFilePath(aFilePath: String, bFilePath: String) = aFilePath.equals(bFilePath, ignoreCase = checkIfOsIsWindows())
