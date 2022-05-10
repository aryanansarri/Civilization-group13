package Controller.GameController;

import View.Menu;

import java.util.regex.Matcher;

public class CheatController {
    public String increaseTurn(Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("amount"));
        for (int i = 0; i < amount; i++) {
            Menu.getGameView().getGameViewController().nextTurn();
        }
        return "turn increased " + amount + " times.";
    }

    public String increaseGold(Matcher matcher) {
        int number = Integer.parseInt(matcher.group("amount"));
        return "";
    }
}
