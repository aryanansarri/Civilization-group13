import View.Login;
import View.Menu;

public class Main {

    private static Login login = new Login();
    public static void main(String[] args) {
        do {
            login.run();
        }while (Menu.getMenu() != Menu.E);
    }
}

