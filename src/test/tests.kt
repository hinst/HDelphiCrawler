package hinst.HDelphiCrawler.test

import kotlin.test.*
import org.junit.Test
import hinst.HDelphiCrawler.*

public class Test {

	public Test fun test0() {
		val userDirKey = "user.dir"
		println(userDirKey + " = " + System.getProperty(userDirKey))
	}

	public Test fun testSimpleComment() {
		val preparser = PreParser()
	}

}
