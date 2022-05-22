package ModelsTest;

import Models.Block.Border;
import Models.Block.TerrainType;
import Models.Block.Tile;
import org.junit.Assert;
import org.junit.Test;

public class ModelsTest {
    Tile a = new Tile(TerrainType.PLAIN, 0, 0);
    Tile b = new Tile(TerrainType.PLAIN, 1, 1);
    Border border = new Border(a, b, false);

    @Test
    public void Test1() {
        Assert.assertEquals(border.getOtherSide(a), b);
    }

    @Test
    public void Test2() {
        Assert.assertFalse(border.toString() == null);
    }

    @Test
    public void Test3() {
        Assert.assertFalse(a.getFood() == -1);
    }

    @Test
    public void Test4() {
        Assert.assertFalse(a.getProduction() == -1);
    }

    @Test
    public void Test5() {
        try {
            System.out.println(a.getGold());
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void Test6() {
        Assert.assertFalse(a.getCombatModifier() == -1);
    }

    @Test
    public void Test7() {
        Assert.assertFalse(a.getMovementCost() == -1);
    }

    @Test
    public void Test8() {
        Assert.assertFalse(a.getCordination() != null);
    }
}
