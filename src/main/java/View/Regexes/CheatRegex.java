package View.Regexes;

public class CheatRegex {
        public static String back = "(back){1}";
        public static String turnCheat = "(increase){1}(\\s+)(gold){1}(\\s+)(--number|-n){1}(\\s+)(?<amount>\\d+)";
        public static String goldCheat = "increase gold (?<amount>\\s+)";
        public static String addTech = "add technology (?<amount>\\S+)";
}
