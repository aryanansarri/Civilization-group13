package Models.Units;

import Models.Block.Tile;
import Models.Civilization.City;
import Models.Civilization.Civilization;

public class SettlerUnit extends CivilianUnit {
    SettlerUnit(int HP, int mana, Tile tile, int combatStrength, int COST) {
        super(HP, mana, tile, combatStrength, COST);
    }
    public void searchForCity(Tile tile) {

    }
}