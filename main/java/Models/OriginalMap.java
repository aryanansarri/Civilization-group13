package Models;

import Models.*;
import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Civilization.Civilization;

public class OriginalMap extends Map{
    private final TerrainType[][] terraintype = new TerrainType[getRows()][getColumns()];

    // should be this class completed 0-100

    public OriginalMap(Civilization civilization) {
        super(civilization);
    }

    public static Tile[][] getTiles() {
        return null;
    }
    public TerrainType[][] getTerrainTypes(){
        return terraintype;
    }
}