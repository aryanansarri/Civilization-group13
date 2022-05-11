package Controller.GameController;

import Controller.User;

import java.util.ArrayList;

public class GameDatabase {

    private ArrayList<User> players;
//    private ArrayList<Civilization> civilizations;
    private int currentPlayerID;

    private static GameDatabase gameDatabase;
    public static GameDatabase getGameDatabase() {
        if (gameDatabase == null) {
            gameDatabase = new GameDatabase();
        }
        return gameDatabase;
    }

    public void PlayGame(ArrayList<User> players) {

    }

    public void nextTurn() {
//       to do
    }
}
