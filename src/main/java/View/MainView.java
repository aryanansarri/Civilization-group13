package View;

import Controller.MainViewController;

public class MainView {
    private MainViewController mainViewController = new MainViewController();

    public void run() {
        while (Menu.getMenu() == Menu.MAIN) {
            String cmd = Menu.input();
//            to do
        }
    }
}
