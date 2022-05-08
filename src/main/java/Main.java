import View.Login;
import View.MainView;
import View.Menu;

public class Main {

    private static Login login = new Login();
    private static MainView mainView = new MainView();
    public static void main(String[] args) {
        while (Menu.getMenu() != Menu.E)
            RUN();
    }

    private static void RUN() {
        login.run();
        mainView.run();
    }
}

