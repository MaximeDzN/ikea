package io.codoctet.ikea.service;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;


@Suite
@SuiteDisplayName("A demo Test Suite")
@SelectClasses({FoodServiceTest.class, FurnitureServiceTest.class, OrderServiceTest.class, QuoteServiceTest.class})
public class SuiteTests {
}
