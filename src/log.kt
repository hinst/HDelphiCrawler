package hinst.HDelphiCrawler

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.lang.ref.WeakReference
import java.util.*

val loggers: MutableMap<Any, WeakReference<Logger>> = HashMap()

interface HasLog {}

private fun HasLog.getExistingLogger(): Logger? {
	val weakLogger = loggers.get(this)
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

fun HasLog.getLogger(): Logger {
	var logger = getExistingLogger()
	if (logger == null) {
		logger = LogManager.getLogger()
		loggers.put(this, WeakReference(logger))
	}
	return logger!!
}