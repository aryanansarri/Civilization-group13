package View;

import Controller.MenuController.ProfileController;
import View.Regexes.ProfileMenuRegex;

public class ProfileMenu {
    private ProfileController profileController = new ProfileController();

    public void run() {
        while (Menu.getMenu() == Menu.PROFILE) {
            String cmd = Menu.input();
            if (Menu.checkMatching(ProfileMenuRegex.Exit, cmd)) {
                System.out.println(profileController.exitProfileMenu());
            }
            else if (Menu.checkMatching(ProfileMenuRegex.showCurrentMenu, cmd)) {
                System.out.println(profileController.showCurrentMenu());
            }
            else if (Menu.checkMatching(ProfileMenuRegex.goToMenu, cmd)) {
                System.out.println(profileController.goToNextMenu());
            }
            else if (Menu.checkMatching(ProfileMenuRegex.changeNickname, cmd)) {
                System.out.println(profileController.changeNickname(Menu.getMatcher()));
            }
            else if (Menu.checkMatching(ProfileMenuRegex.Abbreviation_changeNickname, cmd)) {
                System.out.println(profileController.changeNickname(Menu.getMatcher()));
            }
            else if (Menu.checkMatching(ProfileMenuRegex.changePassword, cmd)) {
                System.out.println(profileController.changePassword(Menu.getMatcher()));
            }
            else if (Menu.checkMatching(ProfileMenuRegex.Abbreviation_changePassword, cmd)) {
                System.out.println(profileController.changePassword(Menu.getMatcher()));
            }
            else {
                System.out.println(profileController.invalidMessage());
            }
        }
    }
}
