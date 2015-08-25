package hinst.HDelphiCrawler.Pascal

val wordBreakers: String = " :;,-+*/&\r\n"

fun checkIfWordBreaker(character: Char) = wordBreakers.contains(character)