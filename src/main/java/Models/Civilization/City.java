package Models.Civilization;

import java.util.ArrayList;

import Models.Block.TerrainFeature;
import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Units.MilitaryUnit;

public class City {


    //////////////////Numberal    Properties
    private int happiness;
    private int food;
    private int gold;
    private int production;
    private int health;
    private int fightingPower;
    private double scienceValue;
    private boolean isCapital;
    private String name;
    //////////////////////////




    //////////////////Object properties
    private Civilization ownership;
    private MilitaryUnit garrisonUnit;
    private Tile location;
    private ArrayList<Citizen> citizens = new ArrayList<>();
    private ArrayList<Tile> tiles = new ArrayList<>();
    private boolean beingcapital;
    ////////////////////////////////



    public City(int gold, int food,double science, Tile location) {
        this.gold = gold;
        this.food = food;
        this.scienceValue = science;
        this.location = location;
        this.fightingPower=0;
        ///////still labouring doubts about fighting power
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

    public int getHealth() {
        return health;
    }

    public int getProduction() {
        return production;
    }

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
    //////////////////////////////////////////

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
}