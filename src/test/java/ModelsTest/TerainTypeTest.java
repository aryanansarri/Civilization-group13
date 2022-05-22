package ModelsTest;

import Models.Block.TerrainType;
import org.junit.Test;

public class TerainTypeTest {
    TerrainType t = TerrainType.DESERT;

    @Test
    public void Test1() {
        t.setHasroad(false);
    }
}
