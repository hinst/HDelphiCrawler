package hinst.HDelphiCrawler

import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

fun readFile(filePath: String, encoding: Charset): String
{
	val bytes = Files.readAllBytes(Paths.get(filePath));
	return String(bytes, encoding);
}