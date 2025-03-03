package utils;

import carmodel.CarInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarInfoFileReader {

    private static final Map<String, CarInfo> expectedDetailsMap = new HashMap<>();


    public static List<String> extractRegNumbers(String fileName) {
        List<String> regNumbers = new ArrayList<>();
        Path filePath = Path.of("src/test/resources/testresources/" + fileName);

        try (var lines = Files.lines(filePath)) {
            lines.skip(1)
                    .map(String::trim)
                    .filter(regNumber -> !regNumber.isEmpty())
                    .forEach(regNumbers::add);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + fileName, e);
        }
        return regNumbers;
    }


    /**
     * Reads vehicle details from car_output.txt and stores in a map for quick lookup.
     *
     * @param fileName The output file name.
     */
    public static void loadExpectedDetails(String fileName) {
        Path filePath = Path.of("src/test/resources/testresources/" + fileName);


        try (var lines = Files.lines(filePath)) {
            lines.skip(1)
                    .map(line -> line.split(",", -1))
                    .filter(parts -> parts.length >= 4)
                    .forEach(parts -> {
                        String regNumber = parts[0].trim();
                        String make = parts[1].trim();
                        String model = parts[2].trim();
                        String year = parts[3].trim();
                        CarInfo details = new CarInfo(make, model, year, "");
                        expectedDetailsMap.put(regNumber, details);
                    });
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + fileName, e);
        }
    }

    /**
     * Retrieves expected vehicle details for a given registration number.
     *
     * @param fileName  Name of the output file (only needed for first-time load).
     * @param regNumber Registration number to fetch details for.
     * @return VehicleDetails object with expected make, model, and year.
     */
    public static CarInfo getExpectedDetails(String fileName, String regNumber) {
        if (expectedDetailsMap.isEmpty()) {
            loadExpectedDetails(fileName);
        }

        CarInfo details = expectedDetailsMap.get(regNumber);
        if (details == null) {
            throw new IllegalArgumentException("No expected details found for reg number: " + regNumber);
        }
        return details;
    }
}
