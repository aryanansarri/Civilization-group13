package View.Regexes;

public class LoginRegex {
    public static String EXIT = "(menu){1}(\\s+)(exit){1}";
    public static String LOGIN = "(user){1}(\\s+)(login){1}(\\s+)(--{1}\\S+)(\\s+)(\\S+)(\\s+)(--{1}\\S+)(\\s+)(\\S+)";
    public static String Abbreviation_login = "(user){1}(\\s+)(login){1}(\\s+)(-{1}\\S)(\\s+)(\\S+)(\\s+)(-{1}\\S)(\\s+)(\\S+)";
    public static String showCurrentMenu = "(menu){1}(\\s+)(show-current){1}";
    public static String Register = "(user){1}(\\s+)(create){1}(\\s+)(--\\S+)(\\s+)(\\S+)(\\s+)(--\\S+)(\\s+)(\\S+)(\\s+)(--\\S+)(\\s+)(\\S+)";
    public static String Abbreviation_Register = "(user){1}(\\s+)(create){1}(\\s+)(-\\S)(\\s+)(\\S+)(\\s+)(-\\S)(\\s+)(\\S+)(\\s+)(-\\S)(\\s+)(\\S+)";
    public static String goToMenu = "(menu){1}(\\s+)(enter){1}(\\s+)([a-zA-Z ]+)";
}
