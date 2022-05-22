package ControllerTest;

import Controller.MenuController.LoginController;
import Controller.PlayerDatabase;
import Controller.User;
import View.Login;
import View.Menu;
import View.Regexes.LoginRegex;
import org.junit.Assert;
import org.junit.Test;

public class LoginTest {
    @Test
    public void LoginTest() {
        LoginController loginController = new LoginController();
        Menu.checkMatching(LoginRegex.Abbreviation_login, "user login -u aryan -p 12345ab");
        Assert.assertEquals(loginController.LoginToGame(Menu.getMatcher()), "user logged in successfully!");
    }

    @Test
    public void RegisterTest() {
        LoginController loginController = new LoginController();
        Menu.checkMatching(LoginRegex.Abbreviation_Register, "user create -u aryan3 -n ary3 -p 12345ab");
        Assert.assertEquals(loginController.RegisterToGame(Menu.getMatcher()), "user created successfully!");
    }

    @Test
    public void goToMenuTest() {
        LoginController loginController = new LoginController();
        Menu.checkMatching(LoginRegex.goToMenu, "menu enter Main Menu");
        PlayerDatabase.getPlayerDatabase().setLoggedInUser(new User("aryan", "ary", "12345ab"));
        System.out.println(loginController.goToMenu(Menu.getMatcher()));
        Assert.assertEquals(Menu.MAIN, Menu.getMenu());
    }

    @Test
    public void exitGameTest() {
        LoginController loginController = new LoginController();
        Assert.assertEquals(loginController.exitGame(), "You have successfully exited the game!");
    }

    @Test
    public void showCurrrentTest() {
        LoginController loginController = new LoginController();
        Assert.assertEquals(loginController.showCurrentMenu(), "Login Menu");
    }

    @Test
    public void invalidTest() {
        LoginController loginController = new LoginController();
        Assert.assertTrue(loginController.invalidMassage().equals("invalid Command"));
    }
}
