package com.solvd.airport;

import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.service.PassengerService;
import com.solvd.airport.service.PassportService;
import com.solvd.airport.service.impl.PassengerServiceImpl;
import com.solvd.airport.service.impl.PassportServiceImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Optional;

public class UserTest2 {

    @DataProvider(name = "InvalidFirstNames")
    public Object[][] invalidFirstNames(){
        return new Object[][]{
                {"a"},
                {"abcdef"},
                {"abc4"},

                {"Igor"},

                {"Ivan"},
                {"abc!"},
        };
    }

    @Test
    public void verifyPassportDataTest() {
        PassportService passportService = new PassportServiceImpl();
        Passport passport = passportService.readById(2L);
        System.out.println("PASSPORT " + passport);
        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(passport.getId(), "Passport id is not null");
        sa.assertNotNull(passport.getNumber(), "Passport number is not null");
        sa.assertAll();
    }

    @Test
    public void verifyPassengerDataTest() {
        PassengerService passengerService = new PassengerServiceImpl();
        Passenger passenger = passengerService.readById(2L);
        System.out.println("PASSENGER " + passenger);
        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(passenger.getId(), "Passenger id is not null");
        sa.assertNotNull(passenger.getName(), "Passenger name is not null");
        sa.assertAll();
    }


    @Test(testName = "Verifies that first name not null")
    public void verifyUserFirstNameNotEmptyTest() {
        User user = new User();
        user.setFirstName("firstname");
        user.setLastName("lastname");

        UserService userService = new UserService();
        user = userService.createUser(user);

        SoftAssert sa = new SoftAssert();

        sa.assertEquals(user.getFirstName(), "Ivan", "User first name is not Ivan");
        sa.assertNotNull(user.getFirstName(), "User first name is null");
        sa.assertNotNull(user.getLastName(), "User last name is null");
        sa.assertEquals(user.getFirstName(), "Oleg", "User first name is not Oleg");

        sa.assertAll();

//        Assert.assertEquals(user.getFirstName(), "Ivan", "User first name is not Ivan");
//        Assert.assertNotNull(user.getFirstName(), "User first name is null");
//        Assert.assertNotNull(user.getLastName(), "User last name is null");
//        if (user.getFirstName() == null) {
//            throw new RuntimeException("User first name is null");
//        }
    


    }
}
