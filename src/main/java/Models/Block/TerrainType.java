package Models.Block;

import Models.Resources.Resource;

import java.util.ArrayList;
public enum TerrainType {
    DESERT(0, 0, 0, -33, 1, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.RIVER); ////  ------>  ////////Sobhan jan add this;
            add(TerrainFeature.Oasis);
            add(TerrainFeature.FloodPlains);
        }
    }, new ArrayList<Resource>() {
        {
            add(Resource.Iron);
            add(Resource.Gold);
            add(Resource.Silver);
            add(Resource.Jewel);
            add(Resource.Marble);
            add(Resource.Cotton);
            add(Resource.Incense);
            add(Resource.Sheep);
        }
    }),
    GRASSLLAND(2, 0, 0, -33, 1, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.RIVER);    ////  ------>  ////////Sobhan jan add this;
            add(TerrainFeature.Forest);
            add(TerrainFeature.Marsh);
        }
    }, new ArrayList<Resource>() {
        {
            add(Resource.Iron);
            add(Resource.Horse);
            add(Resource.Coal);
            add(Resource.Cow);
            add(Resource.Gold);
            add(Resource.Jewel);
            add(Resource.Marble);
            add(Resource.Cotton);
            add(Resource.Sheep);
        }
    }),
    HILLS(0, 2, 0, 25, 2, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.River); ////  ------>  ////////Sobhan jan add this;
            add(TerrainFeature.Forest);
            add(TerrainFeature.Jungle);
        }
    }, new ArrayList<Resource>() {
        {
            add(Resource.Iron);
            add(Resource.Coal);
            add(Resource.Gazelle);
            add(Resource.Gold);
            add(Resource.Silver);
            add(Resource.Jewel);
            add(Resource.Marble);
            add(Resource.Sheep);
        }
    }),
    MOUNTAIN(0, 0, 0, 25, Integer.MAX_VALUE,  new ArrayList<TerrainFeature>(),  new ArrayList<Resource>()),
    OCEAN(1, 0, 1, 0, Integer.MAX_VALUE, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.Ice);
        }
    }, new ArrayList<Resource>() {
        {

        }
    }),
    PLAIN(1, 1, 0, -33, 1, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.River);////  ------>  ////////Sobhan jan add this;
            add(TerrainFeature.Forest);
            add(TerrainFeature.Jungle);
        }
    }, new ArrayList<Resource>() {
        {
            add(Resource.Iron);
            add(Resource.Horse);
            add(Resource.Coal);
            add(Resource.Wheat);
            add(Resource.Gold);
            add(Resource.Jewel);
            add(Resource.Marble);
            add(Resource.Ivory);
            add(Resource.Cotton);
            add(Resource.Incense);
            add(Resource.Sheep);
        }
    }),
    SNOW(0, 0, 0, -33, 1, new ArrayList<TerrainFeature>(), new ArrayList<Resource>() {
        {
            add(Resource.Iron);
        }
    }),
    TUNDRA(1, 0, 0, -33, 1, new ArrayList<TerrainFeature>() {
        {
            add(TerrainFeature.Forest);
        }
    }, new ArrayList<Resource>() {
        {
            add(Resource.Iron);
            add(Resource.Horse);
            add(Resource.Gazelle);
            add(Resource.Silver);
            add(Resource.Jewel);
            add(Resource.Marble);
            add(Resource.Fur);
        }
    });

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
}