package Models.Block;

import java.util.ArrayList;

import Models.Building.Improvement;
import Models.Civilization.Citizen;
import Models.Civilization.City;
import Models.Civilization.Civilization;
import Models.Cordination;
import Models.Resources.Resource;
import Models.Units.CivilianUnit;
import Models.Units.MilitaryUnit;
import Models.Units.Unit;
import Models.Units.Worker;

public class Tile {
    private ArrayList<Border> borders=new ArrayList<Border>();
    private ArrayList<Resource> resources;
    private TerrainType terraintype;
    private Improvement improvement;
    private TerrainType type;
    private ArrayList<TerrainFeature> terrainFeatures;
    private int X;
    private int Y;
    private Civilization owner;
    private MilitaryUnit militaryUnit;
    private CivilianUnit civilianUnit;
    private Citizen workingCitizen;
    private City city;
    private boolean ismovingpossible;

    ////////////constructor
    public Tile(TerrainType terraintype,int X,int Y) {
        this.X=X;
        this.Y=Y;
        this.terraintype = terraintype;
    }

    public ArrayList<TerrainFeature> getTerrainFeatures() {
        return terrainFeatures;
    }

    public TerrainType getTerraintype() {
        return terraintype;
    }
    public Cordination getCordination() {
        return new Cordination(getX(), getY());
    }
    ////////////getting productive values
    public int getFood(){
        if (this.terraintype.getTerrainfeature()== TerrainFeature.Forest) return 1;
        int getfoodT= terraintype.getFood();
        if (this.terraintype.getTerrainfeature()!=null) getfoodT+=this.terraintype.getTerrainfeature().getFood();
        return getfoodT;
    }
    public int getProduction(){
        if (this.terraintype.getTerrainfeature()== TerrainFeature.Forest) return 1;
        int getProT= terraintype.getProduction();
        if (this.terraintype.getTerrainfeature()!=null) getProT+=this.terraintype.getTerrainfeature().getProduction();
        return getProT;
    }
    public int getGold(){
        int getGoldT=terraintype.getGold();
        if (terraintype.getTerrainfeature()!=null) getGoldT+=terraintype.getTerrainfeature().gold;
        getGoldT+=countRivers();
        return getGoldT;
    }

    public City getCity() {
        return city;
    }
    ///////////////////////////

    public double getCombatModifier() {
        double modif = this.terraintype.getCombatmodifier();
        if (this.terraintype.getTerrainfeature() != null) modif += this.terraintype.getTerrainfeature().getCombatmodifier();
        return modif;
    }

    public double getMovementCost() {
        double cost = this.terraintype.getMovementcost();
        if (this.terraintype.getTerrainfeature() != null) cost += this.terraintype.getTerrainfeature().getMovementcost();
        return cost;
    }

    public void setMilitaryUnit(MilitaryUnit militaryUnit) {
        this.militaryUnit = militaryUnit;
    }

    public void setCivilianUnit(CivilianUnit civilianUnit) {
        this.civilianUnit = civilianUnit;
    }

    public MilitaryUnit getMilitaryUnit() {
        return militaryUnit;
    }

    public CivilianUnit getCivilianUnit() {
        return civilianUnit;
    }

    public Citizen getWorkingCitizen() {
        return workingCitizen;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public Worker getWorker() {
        if (civilianUnit instanceof Worker == true)
            return (Worker) civilianUnit;
        else
            return null;
    }

    ///////////////////owner getters and setters
    public Civilization getOwner() {
        return owner;
    }

    public void setOwner(Civilization owner) {
        this.owner = owner;
///////////////////////////////////




///////////////////////////landscape
    }
    private int countRivers(){
        int riverscount=0;
        for (Border Border : borders)
            if (Border.getisRiver()) riverscount++;
        return riverscount;
    }
    public boolean canPass() {
        return (this.terraintype.getIsmovingpossible() || this.terraintype.getTerrainfeature().getIsmovingpossible());
    }
    public boolean canSeeOver(){
        if (!terraintype.getIsvisible()) return false;
        else
            return terraintype.getTerrainfeature()==null || terraintype.getTerrainfeature().getIsvisible();
    }
    public Tile getAdjTile(int i)
    { return borders.get(i).getOtherSide(this);}
//////////////////////////////////////



    public void setCitizen(Citizen workingCitizen) {
        this.workingCitizen = workingCitizen;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    public Improvement getImprovement() {
        return improvement;
    }

    public void setImprovement(Improvement improvement) {
        this.improvement = improvement;
    }

    public TerrainType getType() {
        return type;
    }
}
