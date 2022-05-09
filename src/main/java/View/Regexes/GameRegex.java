package View.Regexes;

public class GameRegex {
    public static String Exit = "(menu){1}(\\s+)(exit){1}";
    public static String showCurrentMenu = "(menu){1}(\\s+)(show-current){1}";
    public static String goToMenu = "(menu){1}(\\s+)(enter){1}(\\s+)([a-zA-Z ]+)";

    public static String SHOW_MAP = "(show){1}(\\s+)(map){1}";
    public static String SHOW_INFO = "(show){1}(\\s+)(info){1}";
    public static String SELECT_CITY_BY_COORDINATE = "^select city (--coordinates|-c) -x (?<x>-?[\\d]+) -y (?<y>-?[\\d]+)$";
    public static String SELECT_CITY_BY_COORDINATE_yFirst = "^select city (--coordinates|-c) -y (?<y>-?[\\d]+)-x (?<x>-?[\\d]+)$";
}
