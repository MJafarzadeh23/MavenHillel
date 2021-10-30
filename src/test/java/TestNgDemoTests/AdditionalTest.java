package TestNgDemoTests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class AdditionalTest {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("I run before SUITE");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("I run after SUITE");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("I run before TEST");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("I run after TEST");
    }
}
