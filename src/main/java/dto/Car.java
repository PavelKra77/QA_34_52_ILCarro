package dto;

import lombok.*;
import utils.enums.Fuel;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Car {
    private String location;
    private String manufacture;
    private String model;
    private String year;
    private Fuel fuel;
    private Integer seats;
    private String carClass;
    private String carRegistrationNumber;
    private Double price;
    private String about;
}
