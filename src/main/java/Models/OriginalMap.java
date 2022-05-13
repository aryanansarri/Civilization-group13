package Models;

import Models.Block.TerrainFeature;
import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Resources.Resource;

import java.util.ArrayList;
import java.util.Random;

public class OriginalMap extends Map{
    private final Tile[][] allTiles = new Tile[super.getRows()][super.getColumns()];
    private final ArrayList<Coordinates> drought = new ArrayList<>();
    private final int numberOfDrought = 40;

    public Tile[][] getAllTiles() {
        return allTiles;
    }
    public OriginalMap(){
        Random random = new Random();
        int startingX = random.nextInt();
        int startingY = random.nextInt();
        for (int i = 0; i < super.getRows(); i++) {
            for (int j = 0; j < super.getColumns(); j++) {
                allTiles[i][j] = new Tile(randomTerrainType(random, i, j),i,j);
                allTiles[i][j].setFeature(randomTerrainFeature(random, allTiles[i][j]));
                allTiles[i][j].setResources(randomResources(random, allTiles[i][j]));
            }

        }
    }
    private boolean isCoordinationUsed(int x, int y) {
        for (Coordinates coordination : drought) {
            if (coordination.getX() == x && coordination.getY() == y) {
                return true;
            }
        }
        return false;
    }
    private void randomDrought(Random random, int x, int y) {
        drought.add(new Coordinates(x, y));
        if (drought.size() >= numberOfDrought)
            return;
        for (int i = 0; i < 6; i++) {
            boolean state = random.nextBoolean();
            if (state) {
                if (y % 2 == 0) {
                    if (i == 0 && x - 1 >= 0 && y - 1 >= 0 && !isCoordinationUsed(x - 1, y - 1)) {
                        randomDrought(random, x - 1, y - 1);
                    }
                    if (i == 1 && x - 1 >= 0 && !isCoordinationUsed(x - 1, y)) {
                        randomDrought(random, x - 1, y);
                    }
                    if (i == 2 && x - 1 >= 0 && y + 1 < super.getColumns() && !isCoordinationUsed(x - 1, y + 1)) {
                        randomDrought(random, x - 1, y + 1);
                    }
                    if (i == 3 && y - 1 >= 0 && !isCoordinationUsed(x, y - 1)) {
                        randomDrought(random, x, y - 1);
                    }
                    if (i == 4 && x + 1 <super.getRows() && !isCoordinationUsed(x + 1, y)) {
                        randomDrought(random, x + 1, y);
                    }
                    if (i == 5 && y + 1 <super.getColumns() && !isCoordinationUsed(x, y + 1)) {
                        randomDrought(random, x, y + 1);
                    }
                }
                if (y % 2 == 1) {
                    if (i == 0 && y - 1 >= 0 && !isCoordinationUsed(x, y - 1)) {
                        randomDrought(random, x, y - 1);
                    }
                    if (i == 1 && x - 1 >= 0 && !isCoordinationUsed(x - 1, y)) {
                        randomDrought(random, x - 1, y);
                    }
                    if (i == 2 && y + 1 <super.getColumns()&& !isCoordinationUsed(x, y + 1)) {
                        randomDrought(random, x, y + 1);
                    }
                    if (i == 3 && y - 1 >= 0 && x + 1 < super.getRows() && !isCoordinationUsed(x + 1, y - 1)) {
                        randomDrought(random, x + 1, y - 1);
                    }
                    if (i == 4 && x + 1 < super.getRows() && !isCoordinationUsed(x + 1, y)) {
                        randomDrought(random, x + 1, y);
                    }
                    if (i == 5 && x + 1 < super.getRows() && y + 1 < super.getColumns() && !isCoordinationUsed(x + 1, y + 1)) {
                        randomDrought(random, x + 1, y + 1);
                    }
                }
            }
        }
    }
    private TerrainType randomTerrainType(Random random, int x, int y) {
        if (!isCoordinationUsed(x, y))
            return TerrainType.OCEAN;
        ArrayList<TerrainType> types = new ArrayList<TerrainType>() {
            {
//                add(new TerrainType(TerrainType.DESERT));
//                add(TerrainType.HILL);
//                add(TerrainType.GRASSLLAND);
//                add(TerrainType.MOUNTAIN);
//                add(TerrainType.PLAIN);
//                add(TerrainType.SNOW);
//                add(TerrainType.TUNDRA);
            }
        };
        int whichType = random.nextInt(7);
        return types.get(whichType);
    }

    private ArrayList<TerrainFeature> randomTerrainFeature(Random random, Tile terrain) {
        ArrayList<TerrainFeature> features = new ArrayList<>();
        for (TerrainFeature terrainFeature : terrain.getType().getPossibleFeatures()) {
            boolean state = random.nextBoolean();
            if (state) {
                if (!terrainFeature.equals(TerrainFeature.FLOODPLAINS) || features.contains(TerrainFeature.RIVER))
                    features.add(terrainFeature);
            }
        }
        return features;
    }
    private ArrayList<Resource> randomResources(Random random, Tile terrain) {
        ArrayList<Resource> resources = new ArrayList<>();
        for (Resource resource : terrain.getType().getPossibleResources()) {
            boolean state = random.nextBoolean();
            if (state) {
                resources.add(resource);
            }
        }
        for (TerrainFeature terrainFeature : terrain.getTerrainFeatures()) {
            for (Resource resource : terrainFeature.getPossibleResources()) {
                boolean state = random.nextBoolean();
                if (state) {
                    resources.add(resource);
                }
            }
        }
        return resources;
    }

    private ArrayList<Resource> randomResources(Random random, Tile terrain) {
        ArrayList<Resource> resources = new ArrayList<>();
        for (Resource resource : terrain.getType().getPossibleResources()) {
            boolean state = random.nextBoolean();
            if (state) {
                resources.add(resource);
            }
        }
        for (TerrainFeature terrainFeature : terrain.getTerrainFeatures()) {
            for (Resource resource : terrainFeature.getPossibleResources()) {
                boolean state = random.nextBoolean();
                if (state) {
                    resources.add(resource);
                }
            }
        }
        return resources;
    }
    public ArrayList<Coordinates> getDrought() {
        return drought;
    }

    public void setTerrain(int x, int y, Tile terrain) {
        allTiles[x][y] = terrain;
    }

    public Tile getTerrain(int x, int y) {
        if (x > super.getRows() - 1 || x < 0 || y > super.getColumns() - 1 || y < 0) {
            return null;
        }
        return allTiles[x][y];
    }
    public int getYpositionTerrain(Tile terrain) {
        for (int i = 0; i < allTiles.length; i++)
            for (int j = 0; j < allTiles[0].length; j++) {
                if (allTiles[i][j] == terrain)
                    return i;
            }
        return -1;
    }

    public int getXpositionTerrain(Tile terrain) {
        for (int i = 0; i < allTiles.length; i++) {
            for (int j = 0; j < allTiles[i].length; j++) {
                if (terrain == allTiles[i][j])
                    return j;
            }
        }
        return -1;
    }

    public boolean isValidTerrian(Tile terrain) {
        for (int i = 0; i < allTiles.length; i++) {
            for (int j = 0; j < allTiles[i].length; j++) {
                if(terrain == allTiles[i][j])
                    return true;
            }
        }
        return false;
    }

}