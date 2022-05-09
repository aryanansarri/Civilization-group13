package Controller.GameController;

import Controller.User;
import Models.BaseMap;
import Models.Civilization;

import java.util.ArrayList;

public class GameDatabase {

    private BaseMap baseMap;
    private ArrayList<User> players;
    private ArrayList<Civilization> civilizations;
    private int currentPlayerID;

    private static GameDatabase gameDatabase;
    public static GameDatabase getGameDatabase() {
        if (gameDatabase == null) {
            gameDatabase = new GameDatabase();
        }
        return gameDatabase;
    }

    public void PlayGame(ArrayList<User> players) {
        this.players = players;
        civilizations = new ArrayList<>();
        for (User user : players) {
            civilizations.add(new Civilization(user.getUsername()));
        }
        currentPlayerID = 0;
        baseMap = new BaseMap();
        // to do something
    }

    public void nextTurn() {
//        to do
    }
}
