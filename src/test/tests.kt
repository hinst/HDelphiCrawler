package hinst.HDelphiCrawler.test

import kotlin.test.*
import org.junit.Test
import hinst.HDelphiCrawler.*

val testDataSubfolder = "testData"

public class Test {

	public Test fun test0() {
		println(userDirKey + " = " + System.getProperty(userDirKey))
		println(osNameKey + " = " + System.getProperty(osNameKey))
	}

	public Test fun testPreParser() {
		TestPreParser().go()
	}

}
