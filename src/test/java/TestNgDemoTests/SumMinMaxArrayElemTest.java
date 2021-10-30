package TestNgDemoTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import practice.SumMinMaxArrayElements;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SumMinMaxArrayElemTest extends AdditionalTest {

    SumMinMaxArrayElements minMaxArrayElem = new SumMinMaxArrayElements();

    @DataProvider(name = "arrayTestDataProvider")
    public Object[][] arrayTests() {
        return  new Object[][]{
                {new int[]{1, 2, 6, 0, 1, 6}, 6},
                {new int[]{1, 12, 6, 1, 1, 6}, 13},
                {new int[]{1, 2, 6, 0, -2, 6}, 4}
        };
    }

    @Test(dataProvider = "arrayTestDataProvider")
    public void testSumMinMuxWithDataProvider(int[] array, int expectedResult) {
        System.out.println("All sum test cases");
        int actualResult = minMaxArrayElem.sumMinMaxElements(array);

        Assert.assertEquals(actualResult,expectedResult,"Actual result didn't match expected");
    }

    @Test
    public void testSumMinMaxArray1() {
        System.out.println("1st test run");
        int expectedResult = 6;
        int actualResult = minMaxArrayElem.sumMinMaxElements(new int[]{1, 2, 6, 0, 1, 6});

        Assert.assertEquals(actualResult, expectedResult, "Actual result didn't match expected");
    }

    @Test
    public void testSumMinMaxArray2() {
        System.out.println("2st test run with negative array element");
        int expectedResult = 3;
        int actualResult = minMaxArrayElem.sumMinMaxElements(new int[]{1, 12, 6, 1, -9, 6});

        Assert.assertEquals(actualResult, expectedResult, "Actual result didn't match expected");
    }

    @Test
    public void testSumMinMaxArray3() {
        System.out.println("3rd test run with abs(negative array element)");
        int expectedResult = 21;
        int actualResult = minMaxArrayElem.sumMinMaxElements(new int[]{1, 12, 6, 1, -9, 6});

        Assert.assertNotEquals(actualResult, expectedResult, "Actual result didn't match expected");
    }

    @Test
    public void testSumMinMaxArray4() {
        System.out.println("4th NotEquals test run ");
        int expectedResult = 2;
        int actualResult = minMaxArrayElem.sumMinMaxElements(new int[]{0});

        Assert.assertNotEquals(actualResult, expectedResult, "Actual result didn't match expected");
    }
}
