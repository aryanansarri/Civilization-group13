package Controller.GameController;

import Models.Civilization.City;
import Models.Units.Unit;
import Models.Units.UnitType;

public class InfoController {
    public String getExploreInformation() {
        return GameDatabase.getGameDatabase().getCurrentCivilization().getCivilizationTechnology().toString();
    }

    public String getUnitsListPanel() {
        String units = "";
        for (Unit unit : GameDatabase.getGameDatabase().getCurrentCivilization().getUnits()) {
            units += units.toString() + "\n";
        }
        return units;
    }

    public String getCitiesListPanel() {
        String cities = "";
        for (City city : GameDatabase.getGameDatabase().getCurrentCivilization().getCities()) {
            cities += city.toString() + "\n";
        }
        return cities;
    }

    public String getDiplomacyInformationPanel() {
        return "to do";
    }

    public String getVictoryProcess() {
        return "to do";
    }

    public String getDemographics() {
        return GameDatabase.getGameDatabase().getCurrentCivilization().getDemographics();
    }

    public String getNotification() {
        return "now you don't have notifications";
    }

    public String getMilitary() {
        String military = "";
        for (Unit unit : GameDatabase.getGameDatabase().getCurrentCivilization().getUnits()) {
            if (UnitType.getMilitaryUnits().contains(unit.getType())) {
                military += unit.toString() + "\n";
            }
        }
        return military;
    }

    public String getEconomy() {
        String eco = GameDatabase.getGameDatabase().getCurrentCivilization().toString() + "\n";
        for (City city : GameDatabase.getGameDatabase().getCurrentCivilization().getCities()) {
            eco += city.toString() + "\n";
        }
        return eco;
    }

    public String getDiplomatics() {
//        to do
        return "";
    }

    public String getDeal() {
//        to do
        return "don't have deal now";
    }
}
