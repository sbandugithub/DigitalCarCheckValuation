package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarDetailsHomePage {

    private WebDriver driver;

    @FindBy(css = "input.homepage-input-vrn")
    private WebElement regInput;

    @FindBy(css = "button.fw-bold")
    private WebElement searchButton;

    public CarDetailsHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchCarByRegNumber(String regNumber) {
        regInput.sendKeys(regNumber);
        searchButton.click();
    }
}
