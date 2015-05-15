package com.twogotrade.monitor.app.stresstests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * This test case aggregates all Stress test cases.
 *
 * @author 2GoTrade
 * @version 1.0
 */
public class StressTests extends TestCase {

	/**
	 * 生成所有性能测试用例的套件
	 *
	 * @return 所有性能测试用例的套件
	 */
	public static Test suite() {
		final TestSuite suite = new TestSuite();
		// suite.addTestSuite(Xxx.class);
		return suite;
	}
}
