package hinst.HDelphiCrawler.test

import kotlin.test.*
import org.junit.Test
import hinst.HDelphiCrawler.*

val testDataSubfolder = "testData"

public class Test: HasLog {

	public Test fun test0() {
		getLogger().error(userDirKey + " = " + System.getProperty(userDirKey))
		getLogger().info(println(osNameKey + " = " + System.getProperty(osNameKey)))
	}

	public Test fun testPreParser() {
		TestPreParser().go()
	}

}
