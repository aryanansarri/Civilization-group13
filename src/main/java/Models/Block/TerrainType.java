package Models.Block;

import Models.Coordinates;
import Models.Resources.Resource;
import Models.Units.CivilianUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static Models.Block.TerrainFeature.*;
import static Models.Resources.Resource.*;

public enum TerrainType {
    DESERT(0, 0, 0, -33, 1, new ArrayList<TerrainFeature>(Arrays.asList(RIVER,Oasis,FloodPlains)), new ArrayList<Resource>(Arrays.asList(Iron,Silver,Jewel,Marble,Cotton,Incense,Sheep)) ),
    GRASSLLAND(2, 0, 0, -33, 1, new ArrayList<TerrainFeature>(Arrays.asList(RIVER,Forest,Marsh)) , new ArrayList<Resource>(Arrays.asList(Iron,Horse,Coal,Cow,Gold,Jewel,Marble,Cotton,Sheep)) ),
    HILLS(0, 2, 0, 25, 2, new ArrayList<TerrainFeature>(Arrays.asList(RIVER,Forest,Jungle)), new ArrayList<Resource>(Arrays.asList(Iron,Coal,Gazelle,Gold,Silver,Jewel,Marble,Sheep))),
    MOUNTAIN(0, 0, 0, 25, Integer.MAX_VALUE,  new ArrayList<TerrainFeature>(),  new ArrayList<Resource>()),
    OCEAN(1, 0, 1, 0, Integer.MAX_VALUE, new ArrayList<TerrainFeature>(Arrays.asList(Ice)) , new ArrayList<Resource>() ),
    PLAIN(1, 1, 0, -33, 1, new ArrayList<TerrainFeature>(Arrays.asList(RIVER,Forest,Jungle)) , new ArrayList<Resource>(Arrays.asList(Iron,Horse,Coal,Wheat,Gold,Jewel,Marble,Ivory,Cotton,Incense,Sheep))),
    SNOW(0, 0, 0, -33, 1, new ArrayList<TerrainFeature>(), new ArrayList<Resource>(Arrays.asList(Iron)) ),
    TUNDRA(1, 0, 0, -33, 1, new ArrayList<TerrainFeature>(Arrays.asList(Forest)) , new ArrayList<Resource>(Arrays.asList(Iron,Horse,Gazelle,Silver,Jewel,Marble,Fur)));

    TerrainType(int food, int product, int gold, int combatModifier, int MP, ArrayList<TerrainFeature> possibleFeatures, ArrayList<Resource> possibleResources) {
        this.food = food;
        this.production = product;
        this.gold = gold;
        this.MP = MP;
        this.combatModifier = combatModifier;
        this.possibleFeatures = possibleFeatures;
        this.possibleResources = possibleResources;
    }

    final int food;
    final int production;
    final int gold;
    final int MP;
    final int combatModifier;
    final ArrayList<TerrainFeature> possibleFeatures;
    final ArrayList<Resource> possibleResources;
    private boolean hasRode=false;

//    to do this function
    public static Collection<Object> getResources() {
        return null;
    }


//    to do this function as establishUnit in City class
    public static CivilianUnit getCivilianUnit() {
        return null;
    }

    public int getFood() {
        return food;
    }

    public int getProduction() {
        return production;
    }

    public int getGold() {
        return gold;
    }

    public int getMP() {
        return MP;
    }

    public int getCombatModifier() {
        return combatModifier;
    }

    public ArrayList<TerrainFeature> getPossibleFeatures() {
        return possibleFeatures;
    }

    public ArrayList<Resource> getPossibleResources() {
        return possibleResources;
    }


    public void setHasroad(boolean b) {
       this.hasRode=b;
    }
}