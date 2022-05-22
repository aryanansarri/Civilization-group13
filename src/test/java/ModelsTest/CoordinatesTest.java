package ModelsTest;

import Models.Civilizations.Civilization;
import Models.Coordinates;
import Models.Map;
import org.junit.Assert;
import org.junit.Test;

public class CoordinatesTest {
    Coordinates coordinates = new Coordinates(1, 1);

    @Test
    public void Test() {
        try {
            coordinates.setX(0);
            coordinates.setY(0);
            coordinates.getY();
            coordinates.getX();
            coordinates.isValidCoordination(new Map(new Civilization("")));
            coordinates.getTile();
            Assert.assertTrue(coordinates.equals(new Coordinates(5, 4)) == false);
            Assert.assertTrue(coordinates.getTerrainType() == null);
        }
        catch (Exception ex) {

        }
    }
}
