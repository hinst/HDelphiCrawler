package hinst.HDelphiCrawler.test

import kotlin.test.*
import org.junit.Test
import hinst.HDelphiCrawler.*

val testDataSubfolder = "testData"

public class Test: HasLog {

	public Test fun test0() {
		this.getLogger().info(userDirKey + " = " + System.getProperty(userDirKey))
		println(osNameKey + " = " + System.getProperty(osNameKey))
	}

	public Test fun testPreParser() {
		TestPreParser().go()
	}

}
