package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.PropertiesManager;

public class CarValuationHomePage {

    private final WebDriver driver;
    private String homePageUrl;
    @FindBy(css = "#Registration")
    private WebElement regInput;

    //@FindBy(css = "#optionSelling.label")
    @FindBy(css = "label[for='optionSelling']")
    private WebElement sellingOption;

    @FindBy(css = "button.btn--primary")
    private WebElement searchButton;

    public CarValuationHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void searchCarByRegNumber(String regNumber) {
        regInput.sendKeys(regNumber);
        sellingOption.click();
        searchButton.click();
    }

    public void navigateToHomePage() {
        if (homePageUrl == null) {
            homePageUrl = PropertiesManager.getProperty("car.valuation.url");
        }
        driver.get(homePageUrl);
    }
}
