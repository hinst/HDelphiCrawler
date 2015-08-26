package hinst.HDelphiCrawler

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.lang.ref.WeakReference
import java.util.*

val logs: Map<Any, WeakReference<Logger>> = HashMap()

private fun Any.getExistingLogger(): Logger? {
	val weakLogger = logs.get(this)
	if (weakLogger != null) {
		val logger = weakLogger.get()
		if (logger != null) {
			return logger
		} else {
			return null
		}
	} else {
		return null
	}
}

fun Any.log(): Logger {
}