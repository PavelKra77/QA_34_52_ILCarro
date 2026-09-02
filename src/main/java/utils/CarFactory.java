package utils;

import dto.Car;
import net.datafaker.Faker;
import utils.enums.Fuel;

import java.time.LocalDate;

public class CarFactory {
    static Faker faker = new Faker();

    public static Car positiveCar(){
        return Car.builder()
                .carRegistrationNumber(faker.vehicle().licensePlate())
                .location("Tel-Aviv")
                .manufacture(faker.vehicle().manufacturer())
                .model(faker.vehicle().model())
                .year(Integer.toString(faker.number().numberBetween(0, LocalDate.now().getYear())))
                .fuel(faker.options().option(Fuel.values()))
                .seats(faker.number().numberBetween(2, 20))
                .carClass(faker.vehicle().carType())
                .price(faker.number().randomDouble(2, 0, 1000))
                .about(faker.text().text(0,500))
                .build();
    }
}
//Integer.toString(...) — преобразует число в String, так как private String year; ожидает строку (например, "2015", а не число 2015).
// faker.options().option(...) — метод библиотеки Datafaker, который принимает массив или список и случайно выбирает из него один элемент.
