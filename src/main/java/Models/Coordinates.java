package Models;

import Controller.GameController.GameDatabase;
import Models.Block.TerrainType;
import Models.Block.Tile;

public class Coordinates{
    private int x,y;

    public Coordinates(int x,int y){
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isValidCoordination(Map map){
        if(this.x >=0 && this.x < map.getColumns()
        && this.y>=0 && this.y < map.getRows())
            return true;
        return false;
    }
    public boolean equals(Coordinates coordinates){
        if( this.x == coordinates.getX()
        && this.y == coordinates.getY())
            return true;
        return false;
    }

    public TerrainType[][] getTerrainType(){
        return GameDatabase.getGameDatabase().getOriginalMap().getTerrainTypes();
    }
    public Tile getTile(){
        return GameDatabase.getGameDatabase().getOriginalMap().getTile(this.x,this.y);
    }

}