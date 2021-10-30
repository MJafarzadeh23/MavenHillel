package TestNgDemoTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import practice.IsLeapYear;

public class IsLeapYearTest extends AdditionalTest {

    IsLeapYear leapYear = new IsLeapYear();

    @Test
    public void testLeapYear1() {
        System.out.println("Leap  year");
        boolean isLeap = leapYear.isLeapYear(2020);

        Assert.assertTrue(isLeap,"Not a leap Year");
    }

    @Test
    public void testLeapYear2() {
        System.out.println("Leap  year");
        boolean isLeap = leapYear.isLeapYear(2019);

        Assert.assertFalse(isLeap,"Not a leap Year");
    }

    @Test
    public void testLeapYear3() {
        System.out.println("Not a Leap  year");
        boolean isLeap = leapYear.isLeapYear(1800);

        Assert.assertFalse(isLeap,"Not a leap Year");
    }
}
