# Digital Car Check and Valuation Automation

An automated testing framework for validating vehicle details and valuations using Selenium WebDriver and Cucumber.

# Table of Contents

1. [Overview](#overview)
2. [Prerequisites](#prerequisites)
3. [Project Setup](#project-setup)
4. [Project Structure](#project-structure)
5. [Key Features](#key-features)
6. [Test Scenarios](#test-scenarios)
7. [Configuration](#configuration)
8. [Input Files](#input-files)
9. [Running Tests](#running-tests)
    - [Full Test Suite](#running-tests)
    - [Specific Tests](#running-specific-tests)
10. [Test Reports](#test-reports)
11. [Logging](#logging)
12. [Dependencies](#dependencies)
13. [Troubleshooting](#troubleshooting)
14. [Known Issues](#known-issues)


## Overview

This project automates the process of checking vehicle details and valuations against a car validation website. It uses a BDD approach with Cucumber features and supports validation of multiple vehicle registration numbers.

## Prerequisites

- Java JDK 21
- Maven 3.x
- Chrome Browser
- IntelliJ IDEA (recommended)

## Project Setup

1. Clone the repository

```https://github.com/sbandugithub/DigitalCarCheckValuation.git```
 


## Project Structure

```
digital-car-check/
├── src/
│   ├── main/                      # Main application code
│   │   ├── java/                  # Java source files
│   │   │   ├── carmodel/             # Data models/entities
│   │   │   ├── pages/             # Page objects (likely for UI testing)
│   │   │   └── utils/             # Utility classes
│   │   └── resources/             # Application resources
│   └── test/                      # Test code
│       ├── java/                  # Java test files
│       │   ├── runner/            # Test runners (likely for Cucumber)
│       │   └── stepdefinitions/   # Step definitions for BDD tests
│       └── resources/             # Test resources
│           ├── feature/           # Cucumber feature files
│           └── testresources/     # Other test resources
└── pom.xml                        # Maven project configuration
```
## Key Features

- Vehicle details validation
- Car valuation checks
- Support for multiple registration numbers
- Invalid registration handling
- Detailed test reporting

## Test Scenarios

1. Validate car registration details from input file
2. Validate car registration details for invalid numbers (Wait few seconds for the page to close in order to proceed for next test)
3. Validate car valuations less than 3000 pounds

## Configuration
Location: `src/test/resources/config.properties'
```
car.validation.url=https://car-checking.com/
car.valuation.url=https://carvaluation.confused.com/
browser=chrome
```


## Input Files
 1) car_input-V6.txt: Valid registration numbers
 2) car_output-V6.txt: Expected vehicle details
 3) invalid-car-inputs.txt: Invalid registration numbers

## Running Tests
 ``` mvn clean install```

## Running Specific Tests
``` mvn test -Dcucumber.filter.tags="@car-details"```

## Test Reports
Test reports are generated in the below directory:

Note: The report is generated after running the tests

 ``` digital-car-check/target/cucumber-reports.html ```

## Logging 

The framework uses SLF4J for logging. Configure logging by adding appropriate SLF4J binding to pom.xml. 

## Dependencies

1) Selenium WebDriver
2) Cucumber
3) JUnit
4) SLF4J
5) Log4j2

## Troubleshooting

1) Check input files for correct data
2) Verify website accessibility
3) Update Chrome Driver if needed
4) Check logs for detailed error messages

## Known Issues

1) SLF4J warnings due to missing provider
2) CDP implementation warnings for Chrome Driver

