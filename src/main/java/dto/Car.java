package dto;

import lombok.*;

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
    private String seats;
    private String carClass;
    private String carRegistrationNumber;
    private String price;
    private String about;
}
