package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLockedOutUser() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(
                loginPage.getErrorMessage().contains("locked out"),
                "Locked-out user should see an error message"
        );
    }
}
