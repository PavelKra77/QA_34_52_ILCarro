package utils;

import dto.User;
import dto.UserLombok;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();

   public static UserLombok positiveUser(){
       UserLombok user = UserLombok.builder()
               .username(faker.internet().emailAddress())
               .password("Qwert123!")
               .firstName(faker.name().firstName())
               .lastName(faker.name().lastName())
               .build();
       return user;
   }

}
