package Models.Units;

import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Civilization.Civilization;

public class Worker extends CivilianUnit {
    public Worker(Civilization civilization, UnitType unitType, Tile tile){
        super(civilization,unitType,tile);
    }
    public void creatRoad(Tile tile) {}
    public void creatFarm(Tile tile) {}
    public void leaveOlderAndCreatNewer(Tile tile) {}
    public void repairRoad(Tile tile) {}
    public void createMine(Tile tile) {}
    public void createTradingPost(Tile tile) {}
    public void createWoodFactory(Tile tile) {}
    public void createCamp(Tile tile) {}
    public void createPasture(Tile tile) {}
    public void removeRoad(Tile tile) {}
    public void removeForest(Tile tile) {}
    public void removeJungle(Tile tile) {}
    public void removeMarsh(Tile tile) {}
    public void createQuarry(Tile tile) {}
}