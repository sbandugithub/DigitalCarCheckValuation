package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import carmodel.CarInfo;
import org.openqa.selenium.NoSuchElementException;
import pages.CarDetailsReportPage;
import pages.CarDetailsHomePage;
import utils.*;

import java.util.ArrayList;
import java.util.List;

public class CarDetailsSteps extends CarTestBase {
    private List<String> regNumbers;
    CarDetailsReportPage carDetailsReportPage;
    CarDetailsHomePage carDetailsHomePage;
    private final List<String> mismatches = new ArrayList<>();
    private final List<String> invalidVehicles = new ArrayList<>();

    @After
    public void tearDown() {
        super.resetDriver();
    }

    @Given("I have a file named {string} with vehicle registration numbers")
    public void iHaveAFileNamedWithVehicleRegistrationNumbers(String fileName) {
        regNumbers = CarInfoFileReader.extractRegNumbers(fileName);
    }

    @Given("I have a file named {string} with invalid vehicle registration numbers")
    public void iHaveAFileNamedWithInvalidVehicleRegistrationNumbers(String fileName) {
        regNumbers = CarInfoFileReader.extractRegNumbers(fileName);
    }

    @Given("I am on the car details validation home page")
    public void iAmOnTheCarDetailsValidationHomePage() {
        setupDriver();
        String carValidationUrl = PropertiesManager.getProperty("car.validation.url");
        driver.get(carValidationUrl);
        carDetailsHomePage = new CarDetailsHomePage(driver);
    }

    @When("I search each vehicle registration on car validation website")
    public void iSearchEachVehicleRegistrationOnCarValidationWebsite() {
        for (String regNumber : regNumbers) {
            driver.get(PropertiesManager.getProperty("car.validation.url"));
            carDetailsHomePage = new CarDetailsHomePage(driver);
            carDetailsHomePage.searchCarByRegNumber(regNumber);
            carDetailsReportPage = new CarDetailsReportPage(driver);
            CarInfo actualDetails = getCarDetails(regNumber);

            if (actualDetails != null) {
                CarInfo expectedDetails = CarInfoFileReader.getExpectedDetails("car_output-V6.txt", regNumber);
                try {
                    CarInfoUtil.compareCarDetails(expectedDetails, actualDetails);
                } catch (AssertionError e) {
                    mismatches.add("Mismatch for " + regNumber + ": " + e.getMessage());
                }
            }
        }
    }

    private CarInfo getCarDetails(String regNumber) {
        try {
            return carDetailsReportPage.getCarDetails();
        } catch (NoSuchElementException e) {
            invalidVehicles.add(regNumber);
        }
        return null;
    }

    @Then("I should see all vehicle details matched correctly")
    public void iShouldSeeAllVehicleDetailsMatchedCorrectly() {
        if (mismatches.isEmpty()) {
            Log.pass("All vehicle details matched correctly.");
        } else {
            mismatches.forEach(System.err::println);
            throw new AssertionError("Test failed due to mismatched vehicle details.");
        }
    }

    @Then("I should see vehicle details are not found")
    public void iShouldSeeVehicleDetailsAreNotFound() {
        if (!invalidVehicles.isEmpty()) {
            Log.pass("The license plate number is not recognised for vehicles: " + invalidVehicles);
        }
    }
}