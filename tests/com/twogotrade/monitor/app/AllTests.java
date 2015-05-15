package com.twogotrade.monitor.app;

import com.twogotrade.monitor.app.integrationtests.IntegrationTests;
import com.twogotrade.monitor.app.stresstests.StressTests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * This test case aggregates all test cases.
 *
 * @author 2GoTrade
 * @version 1.0
 */
public class AllTests extends TestCase {

	/**
	 * 产生所有测试用例（包括单元测试、性能测试、集成测试）的套件
	 *
	 * @return 所有测试用例的套件
	 */
	public static Test suite() {
		final TestSuite suite = new TestSuite();

		// unit tests
		suite.addTest(UnitTests.suite());

		// stress tests
		suite.addTest(StressTests.suite());

		// integration tests
		suite.addTest(IntegrationTests.suite());

		return suite;
	}

}
