package Models;

import Controller.GameController.GameDatabase;
import Models.Block.TerrainFeature;
import Models.Block.TerrainType;
import Models.Block.Tile;

public class Cordination {
    int x, y;

    public Cordination(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "x : " + x + " y : " + y;
    }

    public TerrainType[][] getTerrainType(){
        return GameDatabase.getOriginalMap().getTerrainTypes();
    }
    public boolean isValidCoordination(){
        if (x >= 0 && x < 4 && y >= 0 && y < 6){
            return true;
        }
        return false;
    }
}