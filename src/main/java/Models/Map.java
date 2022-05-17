package Models;

import Controller.GameController.GameDatabase;
import Models.Block.*;
import Models.Civilizations.Civilization;
import Models.Units.Unit;

import static Models.Block.TileVisitingKind.*;


public class Map {
    private final static int columns = 20, rows = 20;
    private  final TileVisitingKind[][] tileVisitingKinds = new TileVisitingKind[columns][rows];
    private final Civilization civilization;

    //initialization of map :
    // if it is original map then it is visible
    // if it is not original map then it is fog of war at first to discover it
    public Map(Civilization civilization) {
        this.civilization = civilization;
        if (this instanceof OriginalMap)
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

    public static int getColumns() {
        return columns;
    }

    public void setCivilization(Civilization civilization) {
        for (Civilization civil : GameDatabase.getGameDatabase().getCivilizations()) {
            if (civil.getCivilizationMap() == this) civil.setCivilizationMap(null);
        }
        civilization.setCivilizationMap(this);
    }

    public Civilization getCivilization() {
        for (Civilization civil : GameDatabase.getGameDatabase().getCivilizations()) {
            if (civil.getCivilizationMap() == this) return civil;
        }
        return null;
    }

    public static int getRows() {
        return rows;
    }

    public TileVisitingKind[][] getTileVisitingKinds() {
        return tileVisitingKinds;
    }

    // this updates map as the units move
    // first we set all visible terrains to once visible then :
    // if any unit is there or terrain belongs to civilization , it is visible
    public void updateExploration() {
        Tile[][] tiles = OriginalMap.getTile();
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (tileVisitingKinds[i][j] == Visible)
                    tileVisitingKinds[i][j] = OnceVisible;
                if (this.civilization == tiles[i][j].getOwner())
                    tileVisitingKinds[i][j] = Visible;
                if (tiles[i][j].getCivilianUnit() != null &&
                        tiles[i][j].getOwner() == Unit.getCivilization())
                    tileVisitingKinds[i][j] = Visible;
                if (tiles[i][j].getMilitaryUnit() != null &&
                        tiles[i][j].getOwner() == Unit.getCivilization())
                    tileVisitingKinds[i][j] = Visible;
            }
        }
        setTileVisible();
    }


    public boolean isTileCorrect(int x, int y) {
        return x < rows && x >= 0 && y < columns && y >= 0;
    }


    public void setTileVisible() {
        setTileVisibleForCivilianUnit();
        setTileVisibleForMilitaryUnit();
        setTileVisibleForCivilization();
    }

    public  void setTileVisibleForCivilization() {
        Tile[][] tile = GameDatabase.getGameDatabase().getOriginalMap().getTile();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (tile[i][j].getOwner() == getCivilization())
                    for (Tile terrainType : tile[i][j].getSurroundingTerrain()) {
                        tileVisitingKinds[terrainType.getCordination().getX()][terrainType.getCordination()
                                .getY()] = Visible;
                    }
            }
        }
    }
    public void setTileVisibleForCivilianUnit() {
        Tile[][] tile = GameDatabase.getGameDatabase().getOriginalMap().getTile();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (tile[i][j].getCivilianUnit() != null)
                    if (tile[i][j].getCivilianUnit().getCivilization() == getCivilization())
                        for (Tile terrainType : tile[i][j].getCivilianUnit().getVisibleTerrain()) {
                            tileVisitingKinds[terrainType.getCordination().getX()][terrainType.getCordination()
                                    .getY()] = Visible;
                        }
            }
        }
    }
    public void setTileVisibleForMilitaryUnit() {
        Tile[][] tile = GameDatabase.getGameDatabase().getOriginalMap().getTile();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (tile[i][j].getMilitaryUnit() != null)
                    if (getCivilization() == tile[i][j].getMilitaryUnit().getCivilization())
                        for (Tile terrainType : tile[i][j].getMilitaryUnit().getVisibleTerrain()) {
                            tileVisitingKinds[terrainType.getCordination().getX()][terrainType.getCordination()
                                    .getY()] = Visible;
                        }
            }
        }
    }





}