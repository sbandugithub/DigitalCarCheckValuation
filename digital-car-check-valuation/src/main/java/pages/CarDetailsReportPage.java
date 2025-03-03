package pages;

import carmodel.CarInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CarDetailsReportPage {

    private WebDriver driver;


    @FindBy(css = ".general-information .table.table-responsive")
    private WebElement carDetailsTable;

    public CarDetailsReportPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CarInfo getCarDetails() {
        String make = getTableCellValue(carDetailsTable, "Make");
        String model = getTableCellValue(carDetailsTable, "Model");
        String year = getTableCellValue(carDetailsTable, "Manufacture year");
        return new CarInfo(make, model, year, "");  // Value/Price can be empty if not shown
    }

    private String getTableCellValue(WebElement table, String label) {
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() == 2) {
                String cellLabel = cells.get(0).getText().trim();
                if (cellLabel.equalsIgnoreCase(label)) {
                    return cells.get(1).getText().trim();
                }
            }
        }
        throw new NoSuchElementException("The table row not found for the label: " + label);
    }

}
