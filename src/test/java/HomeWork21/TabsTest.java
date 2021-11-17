package HomeWork21;

import UIDemoTests.BaseBrowserTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class TabsTest extends BaseBrowserTest {

    @Test
    public void tabsActions() {
        String url = "https://demoqa.com/browser-windows";
        String samplePageText = "This is a sample page";

        driver.get(url);

        // Click 'New Tab' button
        driver.findElement(By.id("tabButton")).click();
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // Verify that text in a new tab is: "This is a sample page"
        Assert.assertTrue(driver.findElement(By.id("sampleHeading")).getText().contains(samplePageText));

        // In the first tab click 'New Tab' button
        driver.switchTo().window(tabs.get(0));
        driver.findElement(By.id("tabButton")).click();
        ArrayList<String> newTabs = new ArrayList<String> (driver.getWindowHandles());

        // Verify that 3 tabs are currently open in browser
        Assert.assertTrue(newTabs.size() == 3);

        // Close tabs that are sample pages
        closeAllExtraTabs(newTabs, 2);

        // Switch to the initial tab
        driver.switchTo().window(newTabs.get(0));

        // Verify that you are where you're expected to be
        Assert.assertEquals(driver.getTitle(), "ToolsQA");

        // Verify that only 1 tab is open
        ArrayList<String> initialTab = new ArrayList<String> (driver.getWindowHandles());
        Assert.assertTrue(initialTab.size() == 1);
    }

    private void closeAllExtraTabs(ArrayList<String> windowHandles, int numberOfTabsToBeClosed) {
        for (int i = numberOfTabsToBeClosed; i >= 1; i--) {
            driver.switchTo().window(windowHandles.get(i)).close();
        }
    }
}
