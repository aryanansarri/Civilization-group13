package Models.Improvment;

import Controller.GameController.GameDatabase;
import Models.Block.TerrainFeature;
import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Resources.Resource;
import Models.Technology.Technology;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Models.Block.TerrainType.*;
import static Models.Technology.Technology.*;


public enum Improvement {

    REMOVE_MARSH("REMOVE_MARSH", 0, 0, 0,6, null, null, null,null),
    REMOVE_JUNGLE("REMOVE_JUNGLE", 0, 0, 0,6, Mathematics, null,null,null),
    REMOVE_FOREST("REMOVE_FOREST", 0, 0, 0,3, Bronze_Working, null,null,null),
    REMOVE_ROUTE("REMOVE_ROUTE", 0, 0, 0,0, null, null, null,null),
    REPAIR("REPAIR", 0, 0, 0, 3, null, null,null,null),
    DRAINMARSH("DRAINMARSH", 0, 0, 0,5, null, null, null,null),
    ROAD("Road", 0, 0, 0,3, null, null, null,null),
    Camp("Camp",0,0,0,6,Trapping ,new ArrayList<Resource>(Arrays.asList(Resource.Ivory,Resource.Fur,Resource.Gazelle)),new ArrayList<TerrainFeature>(List.of(TerrainFeature.Forest)),new ArrayList<TerrainType>(Arrays.asList(TUNDRA,PLAIN,HILLS))),
    Farm("Farm",1,0,0,6,Agriculture,new ArrayList<Resource>(List.of()),new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GRASSLLAND,PLAIN,HILLS))),
    Lumber_Mill("Lumber_Mill",0,1,0,6,Engineering,new ArrayList<Resource>(List.of()),new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GRASSLLAND,PLAIN,DESERT))),
    Mine("Mine",0,1,0,6,Mining,new ArrayList<Resource>(List.of()),new ArrayList<TerrainFeature>(Arrays.asList(TerrainFeature.Forest,TerrainFeature.Jungle)),new ArrayList<TerrainType>(Arrays.asList(PLAIN,HILLS,DESERT,TUNDRA,SNOW))),
    Pasture("Pasture",0,0,0,7,Animal_Husbandry,new ArrayList<Resource>(List.of()),new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GRASSLLAND,PLAIN,DESERT,TUNDRA,HILLS))),
    Plantation("Plantation",0,0,0,5,Calendar,new ArrayList<Resource>(List.of()),new ArrayList<TerrainFeature>(Arrays.asList(TerrainFeature.Forest,TerrainFeature.Marsh,TerrainFeature.FloodPlains,TerrainFeature.Jungle)),new ArrayList<TerrainType>(Arrays.asList(GRASSLLAND,PLAIN,DESERT))),
    Quarry("Quarry",0,0,0,7,Masonry,new ArrayList<Resource>(List.of()),new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GRASSLLAND,PLAIN,DESERT,TUNDRA,HILLS))),
    Trading_Post("Trading_Post",0,0,1,8,Trapping,new ArrayList<Resource>(List.of()),new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GRASSLLAND,PLAIN,DESERT,TUNDRA))),
    Manufactory("Manufactory",0,2,0,0,Engineering,new ArrayList<Resource>(List.of()),new ArrayList<TerrainFeature>(List.of()),new ArrayList<TerrainType>(Arrays.asList(GRASSLLAND,PLAIN,DESERT,SNOW,TUNDRA)));

    private final int food;
    private final int gold;
    private final int production;
    private final int turn;
    private final Technology neededTech;
    private final ArrayList<Resource> resourceImprovement;
    private final ArrayList<TerrainFeature> possibleTerrainFeatures;
    private final ArrayList<TerrainType> possibleTerrainTypes;

    private String type;


    Improvement(String Type, int food, int production, int gold , int turn , Technology neededTech, ArrayList<Resource> resourceImprovement, ArrayList<TerrainFeature> possibleTerrainFeatures, ArrayList<TerrainType> possibleTerrainTypes){
        this.type=type;
        this.food=food;
        this.gold=gold;
        this.turn = turn;
        this.production=production;
        this.neededTech=neededTech;
        this.resourceImprovement = resourceImprovement;
        this.possibleTerrainFeatures = possibleTerrainFeatures;
        this.possibleTerrainTypes = possibleTerrainTypes;

    }
    public static ArrayList<Improvement> getAllImprovements(){
        return new ArrayList<>(Arrays.asList(Improvement.class.getEnumConstants()));
    }

    public String getType() {
        return type;
    }

    public int getProduction() {
        return production;
    }

    public int getGold() {
        return gold;
    }

    public int getFood() {
        return food;
    }

    public ArrayList<TerrainType> getPossibleTerrains() {
        return possibleTerrainTypes;
    }

    public Technology getNeededTech() {
        return neededTech;
    }

    public int getNeededturns() {
        return turn;
    }
    public boolean checkIsPossible(Tile tile) {
        if (!(GameDatabase.getCurrentCivilization().getCivilizationTechnology().
                getPassedTechnology().contains(neededTech) || neededTech == null))
            return false;
        if (possibleTerrainTypes.contains(tile.getTerraintype())) {
            return true;
        }
        for (TerrainFeature terrainFeature : tile.getTerrainFeatures()) {
            if (possibleTerrainTypes.contains(terrainFeature)) {
                return true;
            }
        }
        return false;
    }
}