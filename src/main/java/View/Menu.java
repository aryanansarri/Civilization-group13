package View;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Menu {
//    states
    LOGIN, MAIN, PROFILE, GAME, NULL;

    private static Menu current = Menu.LOGIN;
    private static Matcher matcher;

    private static Login login = new Login();
    private static MainView mainView = new MainView();
    private static ProfileMenu profileMenu = new ProfileMenu();
    private static GameView gameView = new GameView();
    private static Scanner in = new Scanner(System.in);


    public static Menu getMenu() {
        return current;
    }

    public static void goToMenu(Menu menu) {
        current = menu;
    }

    public static String input() {
        return in.nextLine().trim();
    }

    public static boolean checkMatching(String regex, String input) {
        Pattern p = Pattern.compile(regex);
        matcher = p.matcher(input);
        return matcher.matches();
    }

    public static Matcher getMatcher() {
        return matcher;
    }

    public static void run() {
        login.run();
        mainView.run();
        profileMenu.run();
        gameView.run();
    }
}
