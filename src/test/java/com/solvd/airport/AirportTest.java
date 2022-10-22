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

import java.util.List;
import java.util.Optional;

public class AirportTest {

    @DataProvider(name = "PassportId")
    public Object[][] passportsId(){
        return new Object[][]{
                {1L},
                {2L},
                {3L}
        };
    }

    @DataProvider(name = "InvalidNames")
    public Object[][] invalidNames(){
        return new Object[][]{
                {"Oleeg"},
                {"Ivan"}
        };
    }

    @Test(testName = "Verifies passport data", dataProvider = "PassportId")
    public void verifyPassportReadTest(Long id) {
        PassportService passportService = new PassportServiceImpl();
        Passport passport = passportService.readById(id);
        System.out.println("PASSPORT " + passport);
        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(passport.getId(), "Passport id is null");
        sa.assertNotNull(passport.getNumber(), "Passport number is null");
        sa.assertAll();
    }

    @Test(testName = "Verifies passenger data", dataProvider = "InvalidNames")
    public void verifyPassengerReadTest(String invalidName) {
        PassengerService passengerService = new PassengerServiceImpl();
        Passenger passenger = passengerService.readById(2L);
        List<Passenger> passengers = passengerService.readAll();
        System.out.println("PASSENGER " + passenger);
        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(passenger.getId(), "Passenger id is null");
        sa.assertNotNull(passenger.getName(), "Passenger name is null");
        sa.assertEquals(passenger.getName(), invalidName, "Passenger name is invalid");

        passengers.forEach(passenger1 -> {
            sa.assertNotNull(passenger.getId(), "Passenger id is null");
            sa.assertNotNull(passenger.getName(), "Passenger name is null");
            sa.assertEquals(passenger.getName(), invalidName, "Passenger name is invalid");
        });

        sa.assertAll();
    }

    @Test(testName = "Verifies passport create")
    public void verifyPassportCreateTest() {
        PassportService passportService = new PassportServiceImpl();
        Passport passport;
        passport = new Passport();
        passport.setNumber((int) (Math.random() * 333333));
        System.out.println("PASSPORT to create:\n" + passport);

        passport = passportService.create(passport);

        System.out.println("PASSPORT created:\n" + passport);
        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(passport.getId(), "Passport id is null");
        sa.assertNotNull(passport.getNumber(), "Passport id is null");
        sa.assertAll();
    }
}
