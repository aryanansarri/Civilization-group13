package Controller.GameController;

import View.Menu;

public class GameViewController {
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

    public String researchInformation() {
        return "";
    }

    public String unitsInformation() {
        return "";
    }

    public String citiesInformation() {
        return "";
    }

    public String diplomacyInformation() {
        return "";
    }

    public String victoryInformation() {
        return "";
    }

    public String demographicInformation() {
        return "";
    }

    public String militaryInformation() {
        return "";
    }

    public String notification() {
        return "";
    }

    public String diplomaticInformation() {
        return "";
    }

    public String economicInformation() {
        return "";
    }

    public String dealInformation() {
        return "";
    }
}
