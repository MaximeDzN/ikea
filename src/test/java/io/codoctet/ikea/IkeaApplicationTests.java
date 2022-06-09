package io.codoctet.ikea;

import io.codoctet.ikea.service.SuiteTests;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class IkeaApplicationTests {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(SuiteTests.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println(result.wasSuccessful());
	}

}
