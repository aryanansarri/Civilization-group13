package ControllerTest;

import Controller.MenuController.MainViewController;
import View.Menu;
import View.Regexes.MainMenuRegex;
import org.junit.Assert;
import org.junit.Test;

public class MainControlerTest {

    MainViewController mainViewController = new MainViewController();
    @Test
    public void ExitTextTest() {
        Assert.assertEquals(mainViewController.exit(), "You can use 'user logout' command");
    }

    @Test
    public void ShowCurrentTest() {
        Assert.assertEquals(mainViewController.showCurrentMenu(), "Main Menu");
    }

    @Test
    public void LogoutTest() {
        Assert.assertEquals(mainViewController.Logout(), "user logged out successfully!");
    }

    @Test
    public void goToMenuTest() {
        Menu.checkMatching(MainMenuRegex.goToMenu, "menu enter Game Menu");
        Assert.assertEquals(mainViewController.goToNextMenu(Menu.getMatcher()), "You can use play game command");
    }

    @Test
    public void checkInvalidMess() {
        Assert.assertEquals(mainViewController.invalidMessage(), "invalid command");
    }

    @Test
    public void PlayGameTest() {
        Menu.checkMatching(MainMenuRegex.playGame, "play game --player1 aryan --player2 Mrx");
        Assert.assertEquals(mainViewController.PlayGame(Menu.getMatcher()), "Game started successfully!");
    }


}
