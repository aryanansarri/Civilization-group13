package View;

public enum Menu {
    LOGIN,
    MAIN,
    PROFILE,
    GAME,
    E;
    private static Menu current = Menu.LOGIN;

    public static Menu getMenu() {
        return current;
    }

    public static void goToMenu(Menu menu) {
        current = menu;
    }
}
