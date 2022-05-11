package Controller.GameController;

import Controller.User;
import Models.Civilization.Civilization;
import Models.OriginalMap;
import Models.Select;

import java.util.ArrayList;

public class GameDatabase {
    private static GameDatabase gameDatabase;
    public static GameDatabase getGameDatabase() {
        if (gameDatabase == null) {
            gameDatabase = new GameDatabase();
        }
        return gameDatabase;
    }

    private ArrayList<User> players;
    private ArrayList<Civilization> civilizations;
    private int currentPlayerID;
    private OriginalMap originalMap;
    private Select selected;

    public User getCurrentPlayer() {
        return players.get(currentPlayerID % players.size());
    }

    public Civilization getCurrentCivilization() {
        return civilizations.get(currentPlayerID % civilizations.size());
    }

    public void backstageOfGame(ArrayList<User> players) {
        players = players;
        civilizations = new ArrayList<>();
        loadData();
    }

    public void loadData() {
        for (int i = 0; i < players.size(); i++) {

        }
    }
}
