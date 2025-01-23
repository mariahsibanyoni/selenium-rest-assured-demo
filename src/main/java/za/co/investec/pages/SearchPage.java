package za.co.investec.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By searchToggle = By.xpath("//*[@id='search-toggle']");
    private By searchInput = By.id("searchBarInput");
    private By searchButton = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Filter search results'])[1]/following::*[name()='svg'][1]");
    private By nameInput = By.xpath("//*[@name='name']");
    private By surnameInput = By.name("surname");
    private By emailInput = By.name("email");
    private By yearInput = By.name("year_of_birth");
    private By submitButton = By.xpath("//button[contains(text(),'Submit')]");
    private By thankYouMessage = By.xpath("//main[@id='content']/div/div[6]/div[2]/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/div/div/form/div[2]/div/div/div/h3");
    private By acceptAllCookies = By.xpath("//button[text()=\"Accept all cookies\"]");
    private By understandingInterestRateLink = By.xpath("//a[contains(@href,'understanding-interest-rates')]");
    private By signUpButton = By.xpath("//button[contains(text(),'Sign up')]");
    private By serviceDropdown = By.xpath("//div[contains(text(),'Please select (scroll down)')]/preceding-sibling::div");
    private By firstOption = By.xpath("//ul/li[contains(text(),'Investec client')]");

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptAllCookies)).click();
    }

    public void performSearch(String searchText) {
        wait.until(ExpectedConditions.elementToBeClickable(searchToggle)).click();
        driver.findElement(searchInput).sendKeys(searchText);
        driver.findElement(searchButton).click();
    }

    public void fillForm(String name, String surname, String email, String yearOfBirth) {
        driver.findElement(understandingInterestRateLink).click();
        driver.findElement(signUpButton).click();
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(serviceDropdown).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(firstOption).click();
        driver.findElement(yearInput).sendKeys(yearOfBirth);
        driver.findElement(submitButton).click();
    }

    public String getThankYouMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(thankYouMessage)).getText();
    }
}
