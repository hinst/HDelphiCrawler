package hinst.HDelphiCrawler

val osNameKey = "os.name"
val Windows = "Windows"

fun checkIfOsIsWindows() = System.getProperty(osNameKey).startsWith(Windows, ignoreCase = true)