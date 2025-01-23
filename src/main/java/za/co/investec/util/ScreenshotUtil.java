package za.co.investec.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String takeScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Create Screenshots directory if it doesn't exist
        File screenshotsDir = new File("test-output/Screenshots");
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdirs();
        }

        // Save screenshot in the same directory as the report
        String destination = "Screenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File("test-output/" + destination);
        FileUtils.copyFile(source, finalDestination);

        // Return relative path for report
        return destination;
    }
}