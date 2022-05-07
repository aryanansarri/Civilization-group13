package Controller;

import View.Menu;

import java.util.regex.Matcher;

public class LoginController {
    public String exitGame() {
        Menu.goToMenu(Menu.E);
        return "You have successfully exited the game!";
    }

    public String LoginToGame(Matcher matcher) {
        String first = matcher.group(5);
        String second = matcher.group(9);
        String username = "";
        String password = "";
        if (first.equals("--username") || first.equals("-u")) {
            username = matcher.group(7);
        }
        else if (second.equals("--username") || second.equals("-u")) {
            username = matcher.group(11);
        }
        if (first.equals("--password") || first.equals("-p")) {
            password = matcher.group(7);
        }
        else if (second.equals("--password") || second.equals("-p")) {
            password = matcher.group(11);
        }
        if (first.equals("") || second.equals("")) {
            return "invalid command";
        }
        User user = PlayerDatabase.getPlayerDatabase().getUser(username);
        if (user == null) {
            return "Username and password didn’t match!";
        }
        if (user.getPassword().equals(password)) {
            Menu.goToMenu(Menu.MAIN);
            return "user logged in successfully!";
        }
        else {
            return "Username and password didn’t match!";
        }
    }
}
