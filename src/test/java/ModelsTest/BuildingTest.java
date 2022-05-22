package ModelsTest;

import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Building.Building;
import Models.Building.BuildingType;
import Models.Civilizations.City;
import org.junit.Assert;
import org.junit.Test;

public class BuildingTest {
    Building building = new Building(BuildingType.BARRACKS);

    @Test
    public void Test1() {
        try {
            System.out.println(Building.isCityCompatibleWithBuildingType(new City(0, 0, new Tile(TerrainType.DESERT, 0, 0)), BuildingType.OPERA_HOUSE));
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void Test2() {

    }
}
