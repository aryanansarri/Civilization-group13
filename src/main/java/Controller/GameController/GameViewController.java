package Controller.GameController;

import View.Menu;

public class GameViewController {

    private InfoController infoController = new InfoController();
    public String exitGameMenu() {
        Menu.goToMenu(Menu.MAIN);
        return "You are taken to the Main Menu";
    }

    public String showCurrentMenu() {
        return "Game Menu";
    }

    public String goToNextMenu() {
        return "menu navigation is not possible";
    }

    public String nextTurn() {
//        to do
        return "next turn was done!";
    }

    public String invalidMessage() {
        return "invalid command";
    }

    public InfoController getInfoController() {
        return infoController;
    }

}
