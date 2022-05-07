package View;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Menu {
//    states
    LOGIN, MAIN, PROFILE, GAME, E;

    private static Menu current = Menu.LOGIN;
    private static Scanner in = new Scanner(System.in);


    public static Menu getMenu() {
        return current;
    }

    public static void goToMenu(Menu menu) {
        current = menu;
    }

    public static Matcher getCommand(String regex) {
        String input = in.nextLine().trim();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        if (m.matches()) {
            return m;
        }
        else {
            return null;
        }
    }

    public static Matcher getCommand(String regex, String input) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        if (m.matches()) {
            return m;
        }
        else {
            return null;
        }
    }
}