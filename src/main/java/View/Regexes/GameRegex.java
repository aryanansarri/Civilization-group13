package View.Regexes;

public class GameRegex {
    public static String Exit = "(menu){1}(\\s+)(exit){1}";
    public static String showCurrentMenu = "(menu){1}(\\s+)(show-current){1}";
    public static String goToMenu = "(menu){1}(\\s+)(enter){1}(\\s+)([a-zA-Z ]+)";

    public static String showMap = "(show){1}(\\s+)(map){1}";
    public static String showInfo = "(show){1}(\\s+)(info){1}";
    public static String selectCityByCoordinate = "(select){1}(\\s+)(city){1}(\\s+)(--coordinates|-c){1}(\\s+)" + "(-x){1}(\\s+)(?<x>-?[\\d]+)(\\s+)(-y){1}(\\s+)(?<y>-?[\\d]+)";
    public static String selectCityByCoordinateType2 = "(select){1}(\\s+)(city){1}(\\s+)(--coordinates|-c){1}(\\s+)" + "(-y){1}(\\s+)(?<y>-?[\\d]+)(\\s+)(-x){1}(\\s+)(?<x>-?[\\d]+)";
    public static String selectMilitaryUnit = "^select military unit (--coordinates|-c) -x (?<x>-?[\\\\d]+) -y (?<y>-?[\\\\d]+)$";
    public static String selectSettler = "";
    public static String selectWorker = "";
    public static String technologyMenu = "";
    public static String cheatMenu = "";
    public static String buildMenu = "";
    public static String nextTurn = "";

}
