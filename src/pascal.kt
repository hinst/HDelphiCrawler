package hinst.HDelphiCrawler.Pascal

val wordBreakers: String = " :;,-+*/&"

fun checkIfWordBreaker(character: Char) = wordBreakers.contains(character)