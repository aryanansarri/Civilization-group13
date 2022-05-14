package View.Regexes;

public class ProfileMenuRegex {
    public static String Exit = "(menu){1}(\\s+)(exit){1}";
    public static String showCurrentMenu = "(menu){1}(\\s+)(show-current){1}";
    public static String goToMenu = "(menu){1}(\\s+)(enter){1}(\\s+)([a-zA-Z ]+)";
    public static String changeNickname = "(profile){1}(\\s+)(change){1}(\\s+)(--nickname){1}(\\s+)(\\S+)";
    public static String Abbreviation_changeNickname = "(profile){1}(\\s+)(change){1}(\\s+)(-n){1}(\\s+)(\\S+)";
    public static String changePassword = "(profile){1}(\\s+)(change){1}(\\s+)(password){1}(\\s+)(--\\S+)(\\s+)(\\S+)(\\s+)(--\\S+)(\\s+)(\\S+)";
    public static String Abbreviation_changePassword = "(profile){1}(\\s+)(change){1}(\\s+)(password){1}(\\s+)(-\\S)(\\s+)(\\S+)(\\s+)(-\\S)(\\s+)(\\S+)";
}
