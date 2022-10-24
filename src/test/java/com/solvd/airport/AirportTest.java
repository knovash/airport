package com.solvd.airport;

import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.service.PassengerService;
import com.solvd.airport.service.PassportService;
import com.solvd.airport.service.impl.PassengerServiceImpl;
import com.solvd.airport.service.impl.PassportServiceImpl;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class AirportTest {

    @DataProvider(name = "PassportNumber")
    public Object[][] passportNumber() {
        return new Object[][]{
                {(int) (Math.random() * 1111111)},
                {(int) (Math.random() * 2222222)},
                {(int) (Math.random() * 3333333)}
        };
    }

    @Test(testName = "Verifies passport create", dataProvider = "PassportNumber", enabled = true)
    public void verifyPassportCreateTest(int number) {
        PassportService passportService = new PassportServiceImpl();
        Passport passport = new Passport();
        passport.setNumber(number);
        passport = passportService.create(passport);
        System.out.println(passport);

        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(passport.getId(), "Passport id is null");
        sa.assertNotNull(passport.getNumber(), "Passport id is null");
        sa.assertAll();
    }

    @DataProvider(name = "PassportId")
    public Object[][] passportId() {
        return new Object[][]{
                {1L},
                {2L},
                {3L}
        };
    }

    @Test(testName = "Verifies passport read", dataProvider = "PassportId", enabled = true)
    public void verifyPassportReadTest(Long id) {
        PassportService passportService = new PassportServiceImpl();
        Passport passport = passportService.readById(id);
        System.out.println(passport);

        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(passport.getNumber(), "Passport number is null");
        sa.assertAll();
    }

    @DataProvider(name = "PassportNumberNew")
    public Object[][] passportNumberNew() {
        return new Object[][]{
                {1L, 453444},
                {2L, 1728},
                {3L, 45344324}
        };
    }

    @Test(testName = "Verifies passport update", dataProvider = "PassportNumberNew", enabled = true)
    public void verifyPassportUpdateTest(Long id, int newNumber) {
        PassportService passportService = new PassportServiceImpl();
        Passport passport;
        passport = passportService.readById(id);
        passport.setNumber(newNumber);
        passportService.update(passport);
        passport = passportService.readById(passport.getId());
        System.out.println(passport);

        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(passport.getId(), "Passport id is null");
        sa.assertNotNull(passport.getNumber(), "Passport id is null");
        sa.assertNotEquals(Optional.ofNullable(passport.getNumber()), newNumber, "Passport number not updated");
        sa.assertAll();
    }

    @Test(testName = "Verifies passport delete", enabled = true)
    public void verifyPassportDeleteTest() {
        PassportService passportService = new PassportServiceImpl();
        List<Passport> passports;
        passports = passportService.readAll();
        int sizeBefore = passports.size();
        Passport passportToDelete = passports.get(passports.size() - 1);
        System.out.println(passports);
        passportService.deleteById(passportToDelete.getId());
        passports = passportService.readAll();
        int sizeAfter = passports.size();
        System.out.println(passports);
        System.out.println("before=" + sizeBefore + " after=" + sizeAfter);
        System.out.println(passports.stream().filter(passport1 -> passport1.equals(passportToDelete)).findAny().isEmpty());

        SoftAssert sa = new SoftAssert();
        sa.assertTrue(passports.stream().filter(passport1 -> passport1.equals(passportToDelete)).findAny().isEmpty());
        sa.assertNotEquals(Optional.ofNullable(sizeBefore), sizeAfter, "Passport number not updated");
        sa.assertAll();
    }

    @Test(testName = "Verifies passports numbers is 7 digits")
    public void verifyPassportsNumbers7Digits() {
        PassportService passportService = new PassportServiceImpl();
        List<Passport> passports;
        passports = passportService.readAll();

        SoftAssert sa = new SoftAssert();
        passports.forEach(passport -> {
            sa.assertNotNull(passport.getId(), "Passport id is null");
            sa.assertNotNull(passport.getNumber(), "Passport number is null");
            sa.assertTrue((Pattern.matches("[0-9]{7}", passport.getNumber().toString())), "Passport number " + passport.getNumber() + " is not of 7 digits");
        });
        sa.assertAll();
    }

    @DataProvider(name = "InvalidNames")
    public Object[][] invalidNames() {
        return new Object[][]{
                {"Oleeg"},
                {"Ivaan"},
                {"Anna"}
        };
    }

    @Test(testName = "Verifies passenger data", dataProvider = "InvalidNames")
    public void verifyPassengerReadTest(String invalidName) {
        PassengerService passengerService = new PassengerServiceImpl();
        Passenger passenger = passengerService.readById(2L);
        System.out.println("PASSENGER " + passenger);
        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(passenger.getId(), "Passenger id is null");
        sa.assertNotNull(passenger.getName(), "Passenger name is null");
        sa.assertNotEquals(passenger.getName(), invalidName, "Passenger name is invalid " + passenger.getName());
        sa.assertEquals(passenger.getName(), "Ivan", "Passenger name is invalid " + passenger.getName());
        sa.assertAll();
    }

    @Test(testName = "Verifies passengers data")
    public void verifyPassengersValidNamesTest() {
        PassengerService passengerService = new PassengerServiceImpl();
        List<Passenger> passengers = passengerService.readAll();
        System.out.println("PASSENGER " + passengers);
        SoftAssert sa = new SoftAssert();
        passengers.forEach(passenger -> {
            sa.assertTrue((Pattern.matches("[a-zA-Z]*", passenger.getName())), "Passenger name contains forbidden characters in " + passenger.getName());
        });
        sa.assertAll();
    }
}
