package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CarValuationHomePage;
import pages.CarValuationReportPage;
import utils.CarTestBase;

public class CarValuationSteps extends CarTestBase {
    CarValuationReportPage carValuationReportPage;
    CarValuationHomePage carValuationHomePage;
    private String regNumber;

    @After
    public void tearDown() {
        super.resetDriver();
    }

    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        setupDriver();
        carValuationHomePage = new CarValuationHomePage(driver);
        carValuationHomePage.navigateToHomePage();
    }

    @When("I search each vehicle registration on car valuation website")
    public void iSearchEachVehicleRegistrationOnCarValuationWebsite() {
        carValuationHomePage.searchCarByRegNumber(regNumber);
        carValuationReportPage = new CarValuationReportPage(driver);
    }

    @Then("I should see all vehicle valuations less than 3000 pounds")
    public void iShouldSeeAllVehicleValuationsLessThan3000Pounds() {
    }

    @Given("I have a car with vehicle registration number {string}")
    public void iHaveACarWithVehicleRegistrationNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    @Then("I should see vehicle valuation is less than {double} pounds")
    public void iShouldSeeVehicleValuationIsLessThanPounds(double vehiclePartExchangeCost) {
        String actualCost = carValuationReportPage.getVehiclePartExchangeCost();
        // Remove the currency symbol
        String numericValue = actualCost.replace("£", "");
        // Parse the remaining string into a double
        double actualCostDbl = Double.parseDouble(numericValue);
        if (actualCostDbl >= vehiclePartExchangeCost) {
            throw new AssertionError("Vehicle valuation is not less than " + vehiclePartExchangeCost + " pounds");
        }
    }
}