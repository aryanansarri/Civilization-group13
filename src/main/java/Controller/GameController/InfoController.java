package Controller.GameController;

public class InfoController {
    public String getExploreInformation() {
        return GameDatabase.getGameDatabase().getCurrentCivilization().getCivilizationTechnology().toString();
    }

    public String getUnitsListPanel() {
        return "";
    }

    public String getCitiesListPanel() {
        return "";
    }

    public String getDiplomacyInformationPanel() {
        return "";
    }

    public String getVictoryProcess() {
        return "";
    }

    public String getDemographics() {
        return "";
    }

    public String getNotification() {
        return "";
    }

    public String getMilitary() {
        return "";
    }

    public String getEconomy() {
        return "";
    }

    public String getDiplomatics() {
        return "";
    }

    public String getDeal() {
        return "";
    }
}
