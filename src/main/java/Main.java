import Controller.PlayerDatabase;
import View.Login;
import View.MainView;
import View.Menu;
import View.ProfileMenu;

public class Main {

    private static Login login = new Login();
    private static MainView mainView = new MainView();
    private static ProfileMenu profileMenu = new ProfileMenu();
    public static void main(String[] args) {
        PlayerDatabase.getPlayerDatabase().loadData();
        while (Menu.getMenu() != Menu.E)
            RUN();
        PlayerDatabase.getPlayerDatabase().saveData();
    }

    private static void RUN() {
        login.run();
        mainView.run();
        profileMenu.run();
        PlayerDatabase.getPlayerDatabase().saveData();
    }
}

