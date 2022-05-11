package Models.Civilization;

import Models.Info.CivilizationGold;
import Models.Info.CivilizationTechnology;
import Models.Resources.Resource;
import Models.Units.Unit;
import Models.War;

import java.util.ArrayList;

public class Civilization {
    private String civilizationName;
    private ArrayList<City> cities;
    private ArrayList<Resource> resources;
    private ArrayList<Unit> units;
    private ArrayList<War> wars;

    private CivilizationTechnology civilizationTechnology;
    private CivilizationGold civilizationGold;

}