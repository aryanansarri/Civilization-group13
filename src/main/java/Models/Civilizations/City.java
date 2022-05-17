package Models.Civilizations;

import java.util.ArrayList;

import Controller.GameController.GameDatabase;
import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Building.BuildingType;
import Models.Connect;
import Models.Info.CityFood;
import Models.Info.CityGold;
import Models.Info.CityProduct;
import Models.Info.CityScience;
import Models.Select;
import Models.Units.*;


public class City implements Select {



    //////////////////Numberal    Properties
    private int happiness;
    private int food;
    private int gold;
    private boolean isHappy;
    private int production;
    private double health = 40;
    private int fightingPower;
    private double scienceValue;
    private String name;
    private CityProduct cityProduct;
    private CityFood cityFood;
    private CityGold cityGold;
    private CityScience cityScience;
    private ArrayList<BuildingType> buildings=new ArrayList<>();
    private Connect<Double,BuildingType> addingBuilding;
    private Connect<Double,UnitType> addingUnit;

    //////////////////////////




    //////////////////Object properties
    private Civilization ownership;
    private MilitaryUnit garrisonUnit;
    private Tile location;
    private ArrayList<Citizen> citizens = new ArrayList<>();
    private ArrayList<Tile> tiles = new ArrayList<>();
    private boolean beingcapital;
    ////////////////////////////////



    public City(int gold, int food, Tile location) {
        this.gold = gold;
        this.food = food;
        this.scienceValue = scienceValue;
        this.location = location;
        this.fightingPower=0;
        ///////still labouring doubts about fighting power
    }

    public Tile getCityLocation(){
        return this.location;
    }
    ////////////////////////////////getters
    public int getFood() {
        return food;
    }

    public ArrayList<Citizen> getCitizens() {
        return citizens;
    }

    public String getName() {
        return name;
    }
    public void setIsHappy(boolean isHappy){
        this.isHappy = isHappy;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Civilization getOwnership() {
        return ownership;
    }
    public double getFightingPower(int turn) {
        double fightingPower=this.fightingPower;
        if (this.getLocation().getMilitaryUnit().isOnGarrison()){
            for (int i = this.getLocation().getMilitaryUnit().getLastActionTurn();i<turn;i++){
                fightingPower = 1.25 * fightingPower;
            }
        }
        return fightingPower;
    }
    public int numberOfCitizens(){return citizens.size();}


    public int getGold() {
        return gold;
    }

    public int getHappiness() {
        return happiness;
    }

    public double getHealth() {
        return health;
    }

    public int getProduction() {
        return production;
    }

    private boolean beingCapital() {return beingcapital;}

    public MilitaryUnit getGarrisonUnit() {
        return garrisonUnit;
    }

    public Tile getLocation() {
        return location;
    }

    public double getScienceValue() {
        return scienceValue;
    }

    public void setScienceValue(double scienceValue) {
        this.scienceValue = scienceValue;
    }

    public int getFightingPower() {
        return fightingPower;
    }
    public Civilization getOwnerShip (){return ownership;}
    //////////////////////////////////




    ////////////remove and add citizen

    public void removeCitizen(Citizen citizen) {
        citizens.remove(citizen);
    }
    public void addCitizen() {
        citizens.add(new Citizen(this));
    }

    //////////////////////////////////



    public void addTile(Tile tile) {
        tiles.add(tile);
    }
    public boolean tileHavingCheck(Tile tile){return tiles.contains(tile);}



    ///////////////////////assigning
    public void relieveCitizenFromWork(Citizen citizen){
        citizen.setonTile(null);
        citizen.getonTile().setCitizen(null);
    }

    private Citizen findCitizenToAssign(){
        int index = (int)(Math.random() * citizens.size());
        if(citizens.get(index).isWorking())
            return findCitizenToAssign();
        return citizens.get(index);
    }
    public void assignCitizen(Tile tile) {
        Citizen citizen = findCitizenToAssign();
        citizen.assignToTile(tile);
    }

    public void setCityScience(CityScience cityScience) {
        this.cityScience = cityScience;
    }

    public void setCityGold(CityGold cityGold) {
        this.cityGold = cityGold;
    }

    public void setCityProduct(CityProduct cityProduct) {
        this.cityProduct = cityProduct;
    }

    public void setCityFood(CityFood cityFood) {
        this.cityFood = cityFood;
    }

    public void setHappy(boolean happy) {
        isHappy = happy;
    }

    public void setHealth(double health) {
        this.health = health;
    }
    //////////////////////////////////////////

    public void GoldHandlerForUnit(UnitType unitType){
        GameDatabase.getGameDatabase().getCurrentCivilization().getCivilizationGold().setGoldAmount(
                GameDatabase.getGameDatabase().getCurrentCivilization().getCivilizationGold().getGoldAmount() - unitType.getCost());
    }
    public void GoldHandlerForBuilding(BuildingType buildingType){
        GameDatabase.getGameDatabase().getCurrentCivilization().getCivilizationGold().setGoldAmount(
                GameDatabase.getGameDatabase().getCurrentCivilization().getCivilizationGold().getGoldAmount() - buildingType.getCost());
    }

    public CityProduct getCityProduct() {
        return cityProduct;
    }

    public ArrayList<Tile> getTile(){
        return  tiles;
    }

    public String getDemographic() {
        String Demographic = "";
        int id = 0;
        for (Citizen citizen : citizens) {
            if (citizen == null) {
                Demographic += "citizen " + id + " now have work to do";
            }
            else {
                Demographic += "citizen " + id + " on " + citizen.getonTile().getX() + ", " + citizen.getonTile().getY();
//                Demographic += " pay: " + citizen.showGoldProductFood();
            }
            ++id;
        }
        return Demographic;
    }
    @Override
    public String toString() {
        return "City{" +
                "happiness=" + happiness +
                ", food=" + food +
                ", gold=" + gold +
                ", production=" + production +
                ", health=" + health +
                ", fightingPower=" + fightingPower +
                ", scienceValue=" + scienceValue +
                ", name='" + name + '\'' +
                ", ownership=" + ownership +
                ", garrisonUnit=" + garrisonUnit +
                ", location=" + location +
                ", citizens=" + citizens +
                ", tiles=" + tiles +
                ", beingcapital=" + beingcapital +
                '}';
    }
    public void addUnit(UnitType unitType){
        if(addingUnit == null && addingBuilding == null){
            System.err.println("can't build right now!");
        }
        else if(unitType.getPrerequisiteTechs()!= null && !(Unit.getCivilization().getCivilizationTechnology().getPassedTechnology().contains(unitType.getPrerequisiteTechs()))){
            System.err.println("The intended Tech doesn't exist!");
        }
        else addingUnit = new Connect<>((double) unitType.getCost(),unitType);
    }

    public void addUnitFast(UnitType unitType, Tile tile){
        if(addingUnit == null && addingBuilding == null)
            System.err.println("can't build right now!");

        else if(unitType.getPrerequisiteTechs()!= null && !(Unit.getCivilization().getCivilizationTechnology().getPassedTechnology().contains(unitType.getPrerequisiteTechs())))
            System.err.println("THe intended Tech doesn't exist!");
        if(tile.getCivilianUnit() == null && UnitType.SETTLER == unitType)
            new SettlerUnit(this.ownership,unitType,tile);
        if(tile.getCivilianUnit() == null && UnitType.WORKER == unitType)
            new Worker(tile, getCivilization());
        if(tile.getMilitaryUnit() == null && UnitType.getMilitaryUnits().contains(unitType))
            new MilitaryUnit(this.ownership,unitType,tile);
        if(tile.getMilitaryUnit() == null && UnitType.getSiegeUnits().contains(unitType)){
            new SiegeUnit(this.ownership,unitType,tile);
        }
        Civilization.getResources().remove(unitType.getPrerequisiteResource());
        GoldHandlerForUnit(unitType);
    }

    public void addBuilding(BuildingType buildingType,TerrainType terrainType){
        if(addingUnit == null && addingBuilding == null){
            System.err.println("can't build right now!");
        }
        else if(buildingType.getPrerequisiteTechnology()!= null && !(Unit.getCivilization().getCivilizationTechnology().getPassedTechnology().contains(buildingType.getPrerequisiteTechnology()))){
            System.err.println("The intended Tech doesn't exist!");
        }
        else addingBuilding = new Connect<>((double) buildingType.getCost(),buildingType);
    }

    public void addBuildingFast(BuildingType buildingType, TerrainType terrainType){
        if(addingUnit == null && addingBuilding == null){
            System.err.println("can't build right now!");
        }
        else if(buildingType.getPrerequisiteTechnology()!= null && !(Unit.getCivilization().getCivilizationTechnology().getPassedTechnology().contains(buildingType.getPrerequisiteTechnology()))){
            System.err.println("The intended Tech doesn't exist!");
        }
        buildings.add(buildingType);
        GoldHandlerForBuilding(buildingType);
    }

    public Civilization getCivilization() {
        for (Civilization civil : GameDatabase.getGameDatabase().getCivilizations()) {
            for (City city : civil.getCities()) {if (city == this) return civil;}
        }return null;
    }

    public void setCivilization(Civilization civilization,Tile tile) {
        for(Civilization civil : GameDatabase.getGameDatabase().getCivilizations()){
            for(City city : civil.getCities()){city.getTiles().remove(tile);}
        }
        civilization.addNewCity(this);
    }

    public City(City city) {
        this.beingcapital = city.beingCapital();
        this.addingUnit = city.getAddingUnit();
        this.addingBuilding = city.getAddingBuilding();
        this.citizens = city.getCitizens();
        this.buildings=city.buildings;
        this.health = 40;
        this.cityProduct = city.getProduct();
        this.cityFood =city.getCityFood();
        this.cityGold = city.getCityGold();
        this.cityScience = city.getCityScience();
        this.tiles = city.getTiles();
    }


    private CityScience getCityScience() {
        return cityScience;
    }

    private CityGold getCityGold() {
        return cityGold;
    }

    private CityFood getCityFood() {
        return cityFood;
    }

    private CityProduct getProduct() {
        return cityProduct;
    }

    public ArrayList<BuildingType> getBuildings() {
        return buildings;
    }

    private Connect<Double,BuildingType> getAddingBuilding() {
        return addingBuilding;
    }

    private Connect<Double, UnitType> getAddingUnit() {
        return addingUnit;
    }

    public City() {
        this.beingcapital = false;
        this.addingUnit = null;
        this.addingBuilding = null;
        this.citizens = new ArrayList<>();
        this.citizens.add(null);
        this.health = 40;
        this.cityProduct = new CityProduct();
        this.cityFood = new CityFood();
        this.cityGold = new CityGold();
        this.cityScience = new CityScience();
        this.tiles = new ArrayList<>();
    }


    public void defending(Combatble enemy) {
        attacking(enemy);
    }

    public void attacking(Combatble enemy){
        if(enemy instanceof City){
            System.err.println("can't attack!");
        }
        else if(enemy instanceof MilitaryUnit){
            ((MilitaryUnit) enemy).setHP((int) (((MilitaryUnit) enemy).getHp() - 20));
            if( ((MilitaryUnit) enemy).getHP() > 0){
                ((MilitaryUnit) enemy).deleteUnit();
                Unit temp = (Unit) enemy;
                temp.capturedBy(this.getCivilization());
            }
            else{((MilitaryUnit) enemy).deleteUnit();}
        }
    }
    public String displayUnitMakingSituation() {
        if (addingUnit == null && addingBuilding == null)
            return "Doesnt build anything";
        if (addingBuilding != null)
            return addingBuilding.getFirst() + " product remains for " + addingBuilding.getSecond();
        return addingUnit.getFirst() + " product remains for " + addingUnit.getSecond();
    }
    public void refresh() {
        cityProduct.setProductAmount(5);
        cityFood.setFoodAmount(5);
        cityGold.setGoldAmount(5);
        cityScience.setScienceAmount(5);
        citizenJob();
        if (addingUnit != null)
            if (addingUnit.getSecond() == UnitType.SETTLER) cityFood.increaseFood(-2);
    }
    public void capturedBy(Civilization civilization,Tile tile){
        setCivilization(civilization,tile);
        setIsHappy(false);
    }
    public void removeCity(Tile tile){
        for(Civilization civil : GameDatabase.getGameDatabase().getCivilizations()){
            for(City city : civil.getCities()){
                city.getTiles().remove(tile);
            }
        }
    }

    private void establishBuilding() {
        buildings.add(addingBuilding.getSecond());
        addingBuilding = null;
    }

    public void establishUnit(Tile tile){
        UnitType unitType = addingUnit.getSecond();
        if (unitType == UnitType.SETTLER && TerrainType.getCivilianUnit() == null) {
            new SettlerUnit( getCivilization(),UnitType.SETTLER,tile);
            addingUnit = null;}
        if (UnitType.getSiegeUnits().contains(unitType) && tile.getMilitaryUnit() == null) {
            new SiegeUnit(getCivilization(),addingUnit.getSecond(), tile);
            TerrainType.getResources().remove(unitType.getPrerequisiteResource());
            addingUnit = null;}
        if (unitType == UnitType.WORKER && TerrainType.getCivilianUnit() == null) {
            new Worker(tile, getCivilization());
            addingUnit = null;}
        if (UnitType.getMilitaryUnits().contains(unitType) && tile.getMilitaryUnit() == null) {
            new MilitaryUnit(getCivilization(),addingUnit.getSecond(), tile);
            TerrainType.getResources().remove(unitType.getPrerequisiteResource());
            addingUnit = null;}
    }
    public ArrayList<UnitType> possibleUnits(){
        ArrayList<UnitType> units = new ArrayList<>();
        ArrayList<UnitType> all = UnitType.getAllUnits();
        for (UnitType unit : all) {
            if ((getCivilization().getCivilizationTechnology().getPassedTechnology().contains(unit.getPrerequisiteTechs()) || unit.getPrerequisiteTechs() == null)
                    && (getCivilization().getResources().contains(unit.getPrerequisiteResource()) || unit.getPrerequisiteResource() == null)) {
                units.add(unit);}}return units;
    }
    public ArrayList<BuildingType> possibleBuildings() {
        ArrayList<BuildingType> buildings = new ArrayList<>();
        for (BuildingType building : BuildingType.createArrayList()) {
            if (getCivilization().getCivilizationTechnology().getPassedTechnology().contains(building.getPrerequisiteTechnology()) || building.getPrerequisiteTechnology() == null) {buildings.add(building);}
        }return buildings;
    }
    public void moveCitizen(){
    }
    public void nextTurn() {
    }
    public void citizenJob(){
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public boolean isHappy() {
        return isHappy;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public void setFightingPower(int fightingPower) {
        this.fightingPower = fightingPower;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addBuilding(BuildingType building) {
        this.buildings.add(building);
    }

    public void setAddingBuilding(Connect<Double, BuildingType> addingBuilding) {
        this.addingBuilding = addingBuilding;
    }

    public void setAddingUnit(Connect<Double, UnitType> addingUnit) {
        this.addingUnit = addingUnit;
    }

    public void setOwnership(Civilization ownership) {
        this.ownership = ownership;
    }

    public void setGarrisonUnit(MilitaryUnit garrisonUnit) {
        this.garrisonUnit = garrisonUnit;
    }

    public void setLocation(Tile location) {
        this.location = location;
    }

    public void setCitizens(ArrayList<Citizen> citizens) {
        this.citizens = citizens;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public boolean isBeingcapital() {
        return beingcapital;
    }

    public void setBeingcapital(boolean beingcapital) {
        this.beingcapital = beingcapital;
    }
}