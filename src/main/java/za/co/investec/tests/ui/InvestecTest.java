package za.co.investec.tests.ui;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.co.investec.base.BaseUITest;
import za.co.investec.pages.SearchPage;
import za.co.investec.util.ScreenshotUtil;

import java.io.IOException;

public class InvestecTest extends BaseUITest {

    @Test
    public void testSearchAndSubmitForm() throws IOException {
        test.info("Starting test execution");

        driver.get("https://www.investec.com/en_za.html");
        test.info("Navigated to Investec homepage");

        SearchPage searchPage = new SearchPage(driver);
        searchPage.acceptCookies();
        test.info("Performed accept cookies");
        searchPage.performSearch("Understanding cash investment interest rates");
        test.info("Performed search");

        searchPage.fillForm("Test", "Automation", "test@investec.co.za", "1994");
        test.info("Filled form details");

        String thankYouMessage = searchPage.getThankYouMessage();
        Assert.assertEquals(thankYouMessage, "Thank you");
        test.info("Verified thank you message");

        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, "test_completion_");
        test.log(Status.PASS, "Final Screenshot: " + test.addScreenCaptureFromPath(screenshotPath));
    }
}
