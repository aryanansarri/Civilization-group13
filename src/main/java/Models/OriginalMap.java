package Models;

import Models.Block.TerrainFeature;
import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Civilizations.Civilization;
import Models.Resources.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static Models.Block.TerrainType.*;

public class OriginalMap extends Map{
    private final ArrayList<Coordinates> drought = new ArrayList<>();
    private final TerrainType[][] terraintype = new TerrainType[getRows()][getColumns()];
    private static final Tile[][] tile = new Tile[getRows()][getColumns()];
    private final ArrayList<Tile> hasRiver = new ArrayList<>();
    private final int droughtCount = 200;
    private final int riverCount = 50;

    public static Tile[][] getTile(){
        return tile;
    }
    public OriginalMap(Civilization civilization) {
        super(civilization);
    }
    public int getTileX(Tile tiles) {
        for (int i = 0; i < tile.length; i++)
            for (int j = 0; j < tile[0].length; j++) {
                if (tile[i][j] == tiles) return i;
            }
        System.err.println("err int getTileX");
        throw new RuntimeException();
    }

    public int getTileY(Tile tiles){
        for (Tile[] value : tile)
            for (int j = 0; j < tile[0].length; j++) {
                if (value[j] == tiles) return j;}
        System.err.println("err int getTileY");
        throw new RuntimeException();
    }

    public TerrainType[][] getTerrainTypes(){
        return terraintype;
    }

    public ArrayList<Coordinates> getDrought() {
        return drought;
    }
    public void setTile (int x,int y,Tile tiles) {
        tile[x][y] = tiles;
    }
    public Tile getTile(int x,int y){
        if  (x < 0|| y < 0 || x + 1 > Map.getRows()|| y + 1 > Map.getColumns() ) {
            System.err.println("Wrong x and y for Tile");
            throw new RuntimeException();
        }
        return tile[x][y];
    }

    public boolean isTileValid(Tile tiles){
        for (Tile[] value : tile)
            for (int j = 0; j < tile[0].length; j++) {
                if (value[j] != tiles) return false;}
        return true;
    }




    private boolean isCoordinated(int x, int y) {
        for (Coordinates cordination : drought) {
            if (cordination.getX() == x && cordination.getY() == y) {
                return true;
            }
        }
        return false;
    }

    private Coordinates findDrought(int x, int y) {
        for (Coordinates cordination : drought) {
            if (cordination.getX() == x && cordination.getY() == y) return cordination;
        }
        return null;
    }

    private void randomDrought(int x, int y, Random random){
        drought.add(new Coordinates(x,y));
        if(drought.size() < droughtCount){
            for (int i=0; i<6;i++) {
                if (random.nextBoolean()) {
                    if (y % 2 == 1) {
                        if (i == 0 && !isCoordinated(x, y - 1)) {
                            randomDrought(x, y - 1, random);
                        }
                        if (i == 1 && x - 1 >= 0 && !isCoordinated(x - 1, y)) {
                            randomDrought(x - 1, y, random);
                        }
                        if (i == 2 && y + 1 < getColumns() && !isCoordinated(x, y + 1)) {
                            randomDrought(x, y + 1, random);
                        }
                        if (i == 3 && x + 1 < getRows() && !isCoordinated(x + 1, y - 1)) {
                            randomDrought(x + 1, y - 1, random);
                        }
                        if (i == 4 && x + 1 < getRows() && !isCoordinated(x + 1, y)) {
                            randomDrought(x + 1, y, random);
                        }
                        if (i == 5 && x + 1 < getRows() && y + 1 < getColumns() && !isCoordinated(x + 1, y + 1)) {
                            randomDrought(x + 1, y + 1, random);
                        }
                    }
                    if (y % 2 == 0) {
                        if (i == 0 && x - 1 >= 0 && y - 1 >= 0 && !isCoordinated(x - 1, y - 1)) {
                            randomDrought(x - 1, y - 1, random);
                        }
                        if (i == 1 && x - 1 >= 0 && !isCoordinated(x - 1, y)) {
                            randomDrought(x - 1, y, random);
                        }
                        if (i == 2 && x - 1 >= 0 && y + 1 < getColumns() && !isCoordinated(x - 1, y + 1)) {
                            randomDrought(x - 1, y + 1, random);
                        }
                        if (i == 3 && y - 1 >= 0 && !isCoordinated(x, y - 1)) {
                            randomDrought(x, y - 1, random);
                        }
                        if (i == 4 && x + 1 < getRows() && !isCoordinated(x + 1, y)) {
                            randomDrought(x + 1, y, random);
                        }
                        if (i == 5 && y + 1 < getColumns() && !isCoordinated(x, y + 1)) {
                            randomDrought(x, y + 1, random);
                        }
                    }
                }
            }
        }
    }

    private void randomRiver(int x,int y,Random random){
        hasRiver.add(tile[x][y]);
        tile[x][y].getTerrainFeatures().add(TerrainFeature.RIVER);
        if (hasRiver.size() < riverCount){
            for (int i = 0; i < 6; i++) {
                if(random.nextBoolean()){
                    if (y % 2 == 1) {
                        if (i == 0 && !tile[x][y - 1].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                            randomRiver( x, y - 1,random);
                        }
                        if (i == 1 && x - 1 >= 0 && !tile[x - 1][y].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                            randomRiver( x - 1, y,random);
                        }
                        if (i == 2 && y + 1 < getColumns() && !tile[x][y + 1].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                            randomRiver( x, y + 1,random);
                        }
                        if (i == 3 && x + 1 < getRows() && !tile[x + 1][y - 1].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                            randomRiver(x + 1, y - 1,random);
                        }
                        if (i == 4 && x + 1 < getRows() && !tile[x + 1][y].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                            randomRiver( x + 1, y,random);
                        }
                        if (i == 5 && x + 1 < getRows() && y + 1 < getColumns() && !tile[x + 1][y + 1].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                            randomRiver(x + 1, y + 1,random);
                        }
                    }
                    if(y%2 == 0){
                        if (i == 0 && x - 1 >= 0 && y - 1 >= 0 && !tile[x - 1][y - 1].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                            randomRiver( x - 1, y - 1,random);
                        }
                        if (i == 1 && x - 1 >= 0 && !tile[x - 1][y].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                            randomRiver( x - 1, y,random);
                        }
                        if (i == 2 && x - 1 >= 0 && y + 1 < getColumns() && !tile[x - 1][y + 1].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                            randomRiver( x - 1, y + 1,random);
                        }
                        if (i == 3 && y - 1 >= 0 && !tile[x][y - 1].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                            randomRiver( x, y - 1,random);
                        }
                        if (i == 4 && x + 1 < getRows() && !tile[x + 1][y].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                            randomRiver(x + 1, y,random);
                        }
                        if (i == 5 && y + 1 < getColumns() && !tile[x][y + 1].getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                            randomRiver( x, y + 1,random);
                        }
                    }
                }
            }
        }
    }

    private TerrainType randomTerrainType(int x,int y,Random random){
        if(isCoordinated(x,y))
            return new ArrayList<TerrainType>(Arrays.asList(GRASSLLAND,PLAIN,DESERT,SNOW,TUNDRA,HILLS,MOUNTAIN)).get(random.nextInt(7));
        else return OCEAN;
    }


    private ArrayList<TerrainFeature> randomTerrainFeature( Tile tile, Random random) {
        ArrayList<TerrainFeature> chiz = new ArrayList<>();
        for (TerrainFeature terrainFeature : tile.getTerraintype().getPossibleFeatures()) {
            if (random.nextBoolean()) {
                if ((!terrainFeature.equals(TerrainFeature.FloodPlains) || chiz.contains(TerrainFeature.RIVER)) && !chiz.equals(TerrainFeature.RIVER))
                    chiz.add(terrainFeature);
            }
        }
        return chiz;
    }

    private ArrayList<Resource> randomResource( Tile tile, Random random) {
        ArrayList<Resource> resources = new ArrayList<>();
        for (TerrainFeature terrainFeature : tile.getTerrainFeatures()) {
            for (Resource resource : terrainFeature.getPossibleResources()) {
                if (random.nextBoolean()) {
                    resources.add(resource);
                }
            }
        }
        for (Resource resource : tile.getTerraintype().getPossibleResources()) {
            if (random.nextBoolean()) {
                resources.add(resource);
            }
        }
        return resources;
    }
}