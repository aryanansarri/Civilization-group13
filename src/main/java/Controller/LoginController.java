package Controller;

import View.Menu;

import java.util.regex.Matcher;

public class LoginController {
    public String exitGame() {
        Menu.goToMenu(Menu.E);
        return "You have successfully exited the game!";
    }

    public String showCurrentMenu() {
        return "Login Menu";
    }

    public String invalidMassage() {
        return "invalid Command";
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
            PlayerDatabase.getPlayerDatabase().Login(user);
            return "user logged in successfully!";
        }
        else {
            return "Username and password didn’t match!";
        }
    }

    public String RegisterToGame(Matcher matcher) {
        String name = "";
        String nickname = "";
        String password = "";
        String first = matcher.group(5);
        String second = matcher.group(9);
        String third = matcher.group(13);
        if (first.equals("--username") || first.equals("-u")) {
            name = matcher.group(7);
        }
        else if (second.equals("--username") || second.equals("-u")) {
            name = matcher.group(11);
        }
        else if (third.equals("--username") || third.equals("-u")) {
            name = matcher.group(15);
        }

        if (first.equals("--nickname") || first.equals("-n")) {
            nickname = matcher.group(7);
        }
        else if (second.equals("--nickname") || second.equals("-n")) {
            nickname = matcher.group(11);
        }
        else if (third.equals("--nickname") || third.equals("-n")) {
            nickname = matcher.group(15);
        }

        if (first.equals("--password") || first.equals("-p")) {
            password = matcher.group(7);
        }
        else if (second.equals("--password") || second.equals("-p")) {
            password = matcher.group(11);
        }
        else if (third.equals("--password") || third.equals("-p")) {
            password = matcher.group(15);
        }

        if (name.equals("") || nickname.equals("") || password.equals("")) {
            return "invalid command";
        }

        if (PlayerDatabase.getPlayerDatabase().getUser(name) != null) {
            return "user with username " + name + " already exists";
        }

        if (PlayerDatabase.getPlayerDatabase().getUserByNickname(nickname) != null) {
            return "user with nickname " + nickname + " already exists";
        }

        PlayerDatabase.getPlayerDatabase().addUser(new User(name, nickname, password));
        return "user created successfully!";
    }
    public String goToMenu(Matcher matcher) {
        String menu = matcher.group(5);
        if (PlayerDatabase.getPlayerDatabase().getLoggedInUser() == null) {
            return "please login first";
        }
        if (menu.equals("Main Menu"))
        {
            Menu.goToMenu(Menu.MAIN);
            return "go to Main Menu";
        }
        return "invalid command";
    }
}
