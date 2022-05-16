package Models.Block;

import java.util.ArrayList;

import Models.Coordinates;
import Models.Improvment.Improvement;
import Models.Civilization.Citizen;
import Models.Civilization.City;
import Models.Civilization.Civilization;
import Models.Resources.Resource;
import Models.Units.CivilianUnit;
import Models.Units.MilitaryUnit;
import Models.Units.Worker;

public class Tile {
    private ArrayList<Border> borders=new ArrayList<Border>();
    private ArrayList<Resource> resources;
    private TerrainType terraintype;
    private TerrainFeature terrainFeature;
    private ArrayList<TerrainFeature> terrainFeatures;
    private Improvement improvement;

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

    public void setTerraintype(TerrainType terraintype) {
        this.terraintype = terraintype;
    }

    ////////////getting productive values
    public int getFood(){
        if (terrainFeature== TerrainFeature.Forest) return 1;
        int getfoodT=terraintype.food;
        if (terrainFeature!=null) getfoodT+=terrainFeature.getFood();
        return getfoodT;
    }
    public int getProduction(){
        if (terrainFeature== TerrainFeature.Forest) return 1;
        int getProT=terraintype.production;
        if (terrainFeature!=null) getProT+=terrainFeature.getProduction();
        return getProT;
    }
    public int getGold(){
        int getGoldT=terraintype.getGold();
        if (terrainFeature==null) getGoldT+=terrainFeature.gold;
        getGoldT+=countRivers();
        return getGoldT;
    }

    public City getCity() {
        return city;
    }
    ///////////////////////////

    public double getCombatModifier() {
        double modif = this.terraintype.getMP();
        if (terrainFeature != null) modif += terrainFeature.getCombatModifier();
        return modif;
    }

    public double getMovementCost() {
        double cost = this.terraintype.getMP();
        if (terrainFeature != null) cost += terrainFeature.getMovementcost();
        return cost;
    }

    public void setFeature(TerrainFeature terrainFeature) {
        this.terrainFeature = terrainFeature;
    }

    public TerrainFeature getTerrainFeature() {
        return terrainFeature;
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
//        solve it
//        if (civilianUnit instanceof Worker == true)
//            return (Worker) civilianUnit;
//        else
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
        return (this.terraintype.getMP()>50 ||terrainFeature.getMP()>50);
    }
    public boolean canSeeOver(){
        if (! (terrainFeature.getMP()>50)) return false;
        else
            return terrainFeature==null || terrainFeature.getIsvisible();
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

    public TerrainType getTerraintype() {
        return terraintype;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "borders=" + borders +
                ", resources=" + resources +
                ", terraintype=" + terraintype +
                ", improvement=" + improvement +
                ", X=" + X +
                ", Y=" + Y +
                ", owner=" + owner +
                ", militaryUnit=" + militaryUnit +
                ", civilianUnit=" + civilianUnit +
                ", workingCitizen=" + workingCitizen +
                ", city=" + city +
                ", ismovingpossible=" + ismovingpossible +
                '}';
    }

    public ArrayList<Border> getBorders() {
        return borders;
    }

    public void setBorders(ArrayList<Border> borders) {
        this.borders = borders;
    }

    public void setTerrainFeature(TerrainFeature terrainFeature) {
        this.terrainFeature = terrainFeature;
    }

    public ArrayList<TerrainFeature> getTerrainFeatures() {
        return terrainFeatures;
    }

    public void setTerrainFeatures(ArrayList<TerrainFeature> terrainFeatures) {
        this.terrainFeatures = terrainFeatures;
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }

    public void setWorkingCitizen(Citizen workingCitizen) {
        this.workingCitizen = workingCitizen;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public boolean isIsmovingpossible() {
        return ismovingpossible;
    }

    public void setIsmovingpossible(boolean ismovingpossible) {
        this.ismovingpossible = ismovingpossible;
    }

    public ArrayList<Tile> getAdjacentTiles() {
        return null;
    }


    public Coordinates getCordination() {
        return null;
//        to do
    }

//    to do bellow function as use in Map use in setTileVisibleForCivilization
    public Iterable<? extends Tile> getSurroundingTerrain() {
        return null;
    }
}