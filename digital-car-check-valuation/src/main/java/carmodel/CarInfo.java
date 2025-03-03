package carmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class CarInfo {

    private String make;
    private String model;
    private String year;
    private String value;

}
