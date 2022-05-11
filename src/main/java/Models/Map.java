package Models;

import Models.Block.*;
import Models.Civilization.Civilization;
import static Models.Block.TileVisitingKind.*;


public class Map{
    private final static int columns =20 , rows = 20;
    private TileVisitingKind[][] tileVisitingKinds = new TileVisitingKind[columns][rows];
    private final Civilization civilization;

    //initialization of map :
    // if it is original map then it is visible
    // if it is not original map then it is fog of war at first to discover it
    public Map(Civilization civilization){
        this.civilization = civilization;
        if(this instanceof OriginalMap)
            for (int i = 0; i < columns; i++) {
                for (int j = 0; j < rows; j++) {
                    tileVisitingKinds[i][j] = Visible;
                }
            }
        else
            for (int i = 0; i < columns; i++) {
                for (int j = 0; j < rows; j++) {
                    tileVisitingKinds[i][j] = FogOfWar;
                }
            }
    }
    public int getColumns() {
        return columns;
    }

    public Civilization getCivilization() {
        return civilization;
    }

    public int getRows() {
        return rows;
    }

    public TileVisitingKind[][] getTileVisitingKinds() {
        return tileVisitingKinds;
    }

    // this updates map as the units move
    // first we set all visible terrains to once visible then :
    // if any unit is there or terrain belongs to civilization , it is visible
    public void updateExploration(){
        Tile[][] tiles = OriginalMap.getTiles;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if(tileVisitingKinds[i][j] == Visible)
                    tileVisitingKinds[i][j] = OnceVisible;
                if(this.civilization == tiles[i][j].getOwner())
                    tileVisitingKinds[i][j]= Visible;
                if(tiles[i][j].getCivilianUnit()!=null&&
                        tiles[i][j].getOwner()==tiles[i][j].getCivilianUnit().getCivilization())
                    tileVisitingKinds[i][j]= Visible;
                if(tiles[i][j].getMilitaryUnit()!=null&&
                        tiles[i][j].getOwner()==tiles[i][j].getMilitaryUnit().getCivilization())
                    tileVisitingKinds[i][j]= Visible;
            }
        }
    }