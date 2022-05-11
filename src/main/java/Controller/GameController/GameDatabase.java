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

    public ArrayList<User> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<User> players) {
        this.players = players;
    }

    public ArrayList<Civilization> getCivilizations() {
        return civilizations;
    }

    public void setCivilizations(ArrayList<Civilization> civilizations) {
        this.civilizations = civilizations;
    }

    public OriginalMap getOriginalMap() {
        return originalMap;
    }

    public void setOriginalMap(OriginalMap originalMap) {
        this.originalMap = originalMap;
    }

    public Select getSelected() {
        return selected;
    }

    public void setSelected(Select selected) {
        this.selected = selected;
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
