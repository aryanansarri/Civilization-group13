import View.Login;
import View.Menu;

public class Main {

    private static Login login = new Login();
    public static void main(String[] args) {
        while (Menu.getMenu() != Menu.E)
            RUN();
    }

    private static void RUN() {
        login.run();
    }
}

