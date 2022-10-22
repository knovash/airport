package com.solvd.airport;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UserTest {

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

    @Test(dataProvider = "InvalidFirstNames")
    public void verifyUserFirstNameErrorMessageExistTest(String firstName) {
        User user = new User();
//        user.setFirstName("firstname");
        user.setFirstName(firstName);

        UserService userService = new UserService();
        user = userService.createUser(user);

        SoftAssert sa = new SoftAssert();

        sa.assertEquals(user.getFirstName(), "Ivan", "User first name is not Ivan");
        sa.assertNotNull(user.getFirstName(), "User first name is null");
        sa.assertNotNull(user.getLastName(), "User last name is null");
        sa.assertEquals(user.getFirstName(), "Oleg", "User first name is not Oleg");

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

    @Test
    public void verifyUserIdNotEmptyOnCreateTest() {
        User user = new User();
        user.setFirstName("firstname");
        user.setLastName("lastname");

        UserService userService = new UserService();
        user = userService.createUser(user);

        Assert.assertNotNull(user.getId(), "User id is null");
//        if (user.getId() == null) {
//            throw new RuntimeException("User id is null");
//        }
    }
}
