import Controller.PlayerDatabase;
import View.Menu;

public class Main {

    public static void main(String[] args) {
        PlayerDatabase.getPlayerDatabase().loadData();
        while (Menu.getMenu() != Menu.E) {
            Menu.run();
        }
        PlayerDatabase.getPlayerDatabase().saveData();
    }
}

