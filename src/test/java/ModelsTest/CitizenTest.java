package ModelsTest;

import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Civilizations.Citizen;
import Models.Civilizations.City;
import org.junit.Test;

public class CitizenTest {
    Citizen citizen = new Citizen(new City(1, 2, new Tile(TerrainType.PLAIN, 4, 6)));

    @Test
    public void Test() {
        try {
            citizen.assignToTile(new Tile(TerrainType.PLAIN, 2, 1));
        }
        catch (Exception e) {

        }
    }

    @Test
    public void Test2() {
        try {
            citizen.diminish();
            citizen.setonTile(new Tile(TerrainType.PLAIN, 4, 5));
            citizen.getonTile();
        }
        catch (Exception e) {

        }
    }
}
