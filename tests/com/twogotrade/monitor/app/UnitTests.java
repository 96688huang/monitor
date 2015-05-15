package com.twogotrade.monitor.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * This test case aggregates all unit test cases.
 *
 * @author Scofield
 * @version 1.0
 */
public class UnitTests extends TestCase {

	/**
	 * 生成所有单元测试用例的套件
	 *
	 * @return 所有单元测试用例的套件
	 */
	public static Test suite() {
		final TestSuite suite = new TestSuite();
		// suite.addTestSuite(Xxx.class);
		return suite;
	}

}
