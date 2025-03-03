package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarValuationReportPage {

    private WebDriver driver;
    private String homePageUrl;
    @FindBy(css = ".option-panel:nth-of-type(2) .option-panel__value")
    private WebElement regInput;

    public CarValuationReportPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getVehiclePartExchangeCost() {
        return regInput.getText();
    }
}
