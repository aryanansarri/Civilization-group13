package View.Regexes;

public class MainMenuRegex {
    public static String Exit = "(menu){1}(\\s+)(exit){1}";
    public static String showCurrentMenu = "(menu){1}(\\s+)(show-current){1}";
    public static String logout = "(user){1}(\\s+)(logout){1}";
    public static String goToMenu = "(menu){1}(\\s+)(enter){1}(\\s+)([a-zA-Z ]+)";
    public static String playGame = "play game(?<command>( --player\\d+ \\S+)+)";
}
