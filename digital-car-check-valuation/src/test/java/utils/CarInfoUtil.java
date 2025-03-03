package utils;

import carmodel.CarInfo;
import org.junit.Assert;

public class CarInfoUtil {


        public static void compareCarDetails(CarInfo expected, CarInfo actual) {
        Assert.assertEquals("Make of the car doesn't match", expected.getMake(), actual.getMake());
        Assert.assertEquals("Model of the car doesn't match", expected.getModel(), actual.getModel());
        Assert.assertEquals("Year of manufacture doesn't match", expected.getYear(), actual.getYear());
        Assert.assertEquals("Value of the car doesn't match", expected.getValue(), actual.getValue());
    }



}
