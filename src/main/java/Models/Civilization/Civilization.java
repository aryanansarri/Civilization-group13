package Models.Civilization;

import Models.Block.TerrainFeature;
import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Block.TileVisitingKind;
import Models.Info.CivilizationGold;
import Models.Info.CivilizationHappiness;
import Models.Info.CivilizationScience;
import Models.Info.CivilizationTechnology;
import Models.Map;
import Models.Resources.Resource;
import Models.Units.Unit;
import Models.War;

import java.util.ArrayList;

public class Civilization {
    private String civilizationName;
    private Map civilizationMap;
    private ArrayList<City> cities;
    private ArrayList<Resource> resources;
    private ArrayList<Unit> units;
    private ArrayList<War> wars;

    private CivilizationTechnology civilizationTechnology;
    private CivilizationGold civilizationGold;
    private CivilizationScience civilizationScience;
    private CivilizationHappiness civilizationHappiness;


    public Civilization(String civilizationName) {
        this.civilizationName = civilizationName;
        this.civilizationMap = new Map(this);
        this.cities = new ArrayList<>();
        this.resources = new ArrayList<>();
        this.units = new ArrayList<>();
        this.wars = new ArrayList<>();
        this.civilizationTechnology = new CivilizationTechnology();
        this.civilizationGold = new CivilizationGold();
        this.civilizationGold.setGoldAmount(50);
        this.civilizationScience = new CivilizationScience();
        this.civilizationHappiness = new CivilizationHappiness();
    }

    public Civilization(String civilizationName, Map civilizationMap, ArrayList<City> cities, ArrayList<Resource> resources, ArrayList<Unit> units, ArrayList<War> wars, CivilizationTechnology civilizationTechnology, CivilizationGold civilizationGold, CivilizationScience civilizationScience, CivilizationHappiness civilizationHappiness) {
        this.civilizationName = civilizationName;
        this.civilizationMap = civilizationMap;
        this.cities = cities;
        this.resources = resources;
        this.units = units;
        this.wars = wars;
        this.civilizationTechnology = civilizationTechnology;
        this.civilizationGold = civilizationGold;
        this.civilizationScience = civilizationScience;
        this.civilizationHappiness = civilizationHappiness;
    }

    public String getCivilizationName() {
        return civilizationName;
    }

    public void setCivilizationName(String civilizationName) {
        this.civilizationName = civilizationName;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public ArrayList<War> getWars() {
        return wars;
    }

    public void setWars(ArrayList<War> wars) {
        this.wars = wars;
    }

    public CivilizationTechnology getCivilizationTechnology() {
        return civilizationTechnology;
    }

    public void setCivilizationTechnology(CivilizationTechnology civilizationTechnology) {
        this.civilizationTechnology = civilizationTechnology;
    }

    public CivilizationGold getCivilizationGold() {
        return civilizationGold;
    }

    public void setCivilizationGold(CivilizationGold civilizationGold) {
        this.civilizationGold = civilizationGold;
    }

    public CivilizationScience getCivilizationScience() {
        return civilizationScience;
    }

    public void setCivilizationScience(CivilizationScience civilizationScience) {
        this.civilizationScience = civilizationScience;
    }

    public CivilizationHappiness getCivilizationHappiness() {
        return civilizationHappiness;
    }

    public void setCivilizationHappiness(CivilizationHappiness civilizationHappiness) {
        this.civilizationHappiness = civilizationHappiness;
    }

    public Map getCivilizationMap() {
        return civilizationMap;
    }

    public void setCivilizationMap(Map civilizationMap) {
        this.civilizationMap = civilizationMap;
    }

    public void addNewCity(City city) {
        cities.add(city);
    }

    public void removeCity(City city) {
        cities.remove(city);
    }

    public void addNewUnit(Unit unit) {
        units.add(unit);
    }

    public void removeUnit(Unit unit) {
        units.remove(unit);
    }

    public TileVisitingKind getTileVisitingKind(int x, int y) {
        return civilizationMap.getTileVisitingKinds()[x][y];
    }

    public void refreshScience() {
        civilizationScience.setScienceValue(50);
        civilizationScience.increaseScienceValue(civilizationScience.getCheatedScienceValue());
        for (City city : cities) {
            civilizationScience.increaseScienceValue(city.getScienceValue());
        }
    }

    public void refreshResource() {
        resources = new ArrayList<>();
        for (City city : cities) {
            for (Tile tile : city.getTiles()) {
                for (Resource resource : tile.getResources()) {
                    if (civilizationTechnology.getPassedTechnology().contains(resource.getRequiredTechnology()) && tile.getImprovement() == resource.getRequiredImprovement()) {
                        resources.add(resource);
                    }
                }
            }
        }
    }

    public void refreshResearch() {
        civilizationTechnology.currentTeachTechnologyProgress();
    }

    public void refreshGold() {
        civilizationGold.setGoldAmount(0);
        civilizationGold.increaseAddedGoldAmount(civilizationGold.getCheatedGoldAmount());
        for (City city : cities) {
            civilizationGold.increaseGoldAmount(city.getGold());
        }
    }
    public void refreshHappiness() {
        civilizationHappiness.setHappinessValue(10);
        civilizationHappiness.increaseHappinessValue(civilizationHappiness.getCheatedHappinessValue());
        ArrayList<Resource> luxuryResource = Resource.getAllResources();
        for (int i = 0; i < luxuryResource.size(); i++) {
            if (luxuryResource.get(i).getGold() <= 0) {
                luxuryResource.remove(i);
            }
        }
        for (Resource resource : this.resources) {
            if (!luxuryResource.contains(resource)) continue;
            civilizationHappiness.increaseHappinessValue(4);
            luxuryResource.remove(resource);
        }
    }

    public void Refresh() {
        refreshGold();
        refreshHappiness();
        refreshResource();
        refreshResearch();
        refreshScience();
    }
    @Override
    public String toString() {
        return "Civilization{" +
                "civilizationName='" + civilizationName + '\'' +
                "Gold Amount='" + getCivilizationGold().getGoldAmount() + '\'' +
                "added Gold Amount='" + getCivilizationGold().getAddedGoldAmount() + '\'' +
                "happiness value'" + getCivilizationHappiness().getHappinessValue() + '\'' +
                "science value'" + getCivilizationScience().getScienceValue() + '\'' +
                '}';
    }

    public String getDemographics() {
        String Demographic = "";
        for (City city : cities) {
            Demographic += "city: " + city.getName();
            Demographic += "Demographic: " + city.getDemographic() + "\n";
        }
        return Demographic;
    }

    public void nextTurn() {
        Refresh();
        civilizationHappiness.nextTurn();
        civilizationGold.increaseGoldAmount(civilizationGold.getAddedGoldAmount());
        if (civilizationGold.getAddedGoldAmount() < 0)
        {
            civilizationScience.increaseScienceValue(getCivilizationGold().getAddedGoldAmount());
            getCivilizationGold().setGoldAmount(0);
        }
    }
}