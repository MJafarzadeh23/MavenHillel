package TestNgDemoTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import practice.ReverseString;
import practice.SumMinMaxArrayElements;

public class ReverseStringTest extends AdditionalTest {

    ReverseString reverseStr = new ReverseString();

    @DataProvider(name = "reverseStrTestDataProvider")
    public Object[][] sumTests() {
        return new Object[][] {
                {"java", "avaj"},
                {"Hillel", "lellih"},
                {"level", "level"},
                {"123456789","987654321"}
        };
    }

    @Test(dataProvider = "reverseStrTestDataProvider")
    public void testReverseStrWithDataProvider(String given, String reverse) {
        System.out.println("All test cases with DataProvider");
        String actualResult = reverseStr.reverseString(given).toLowerCase();

        Assert.assertEquals(actualResult,reverse, "String is reversed in wrong way!");
    }
}
