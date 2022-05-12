package Controller.GameController;

import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Block.TileVisitingKind;

import java.util.regex.Matcher;

public class MapController {
    private Tile[][] allTiles;
    private TileVisitingKind[][] tileVisitingKinds;

    public MapController(Tile[][] allTiles,TileVisitingKind[][] tileVisitingKinds){
        this.allTiles = allTiles;
        this.tileVisitingKinds = tileVisitingKinds;
    }

    private String GetBackGroundColor(Tile terrain){
        if(terrain.getTerraintype().getType().equals(TerrainType.DESERT))
            return "\u001B[43m";
        else if(terrain.getTerraintype().getType().equals(TerrainType.OCEAN))
            return "\u001B[44m";
        else if(terrain.getTerraintype().getType().equals(TerrainType.PLAIN))
            return "\u001B[42m";
        else if(terrain.getTerraintype().getType().equals(TerrainType.TUNDRA))
            return "\u001B[46m";
        else if(terrain.getTerraintype().getType().equals(TerrainType.SNOW))
            return "\u001B[47m";
        else if(terrain.getTerraintype().getType().equals(TerrainType.MOUNTAIN))
            return "\u001B[48;5;130m";
        else if(terrain.getTerraintype().getType().equals(TerrainType.GRASSLAND))
            return "\u001B[48;5;156m";
        else if(terrain.getTerraintype().getType().equals(TerrainType.HILL))
            return "\u001B[48;5;214m";
        return "\u001B[0m"; //resets console color to default
    }

    public void drawHex(String[][] mapString, int istart, int jstart) {
        for (int k = 2; k >= 0; k--) {
            mapString[istart + 2 - k][jstart + k] = "/";
            mapString[istart + 2 - k][jstart + 10 - k] = "\\";
            mapString[istart + 5 - k][jstart + 2 - k] = "\\";
            mapString[istart + 5 - k][jstart + 8 + k] = "/";
        }
    }

//    private void drawMainDetails(String[][] mapString, String backgroundColor) {
//
//    }

    private void drawMap(String[][] mapString, int x, int y) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                mapString[2][11 + j] = "_";
                mapString[2][27 + j] = "_";
                mapString[2][43 + j] = "_";
            }
        }

        int remainder = 0;
        if (y % 2 == 1)
            remainder = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                String backgroundColor;
                if (j % 2 == 1)
                    backgroundColor = GetBackGroundColor(allTiles[x + i + remainder][y + j]);
                else
                    backgroundColor = GetBackGroundColor(allTiles[x + i][y + j]);
                int istart = i * 6;
                int jstart = j * 8;
                if (j % 2 == 1) {
                    istart += 3;
                }

                drawHex(mapString, istart, jstart);

                if (j % 2 == 1) {
                    if (tileVisitingKinds[x + i + remainder][y + j] == TileVisitingKind.FogOfWar) {
                        backgroundColor = "\u001B[48;5;254m";
                    }
                } else if (tileVisitingKinds[x + i][y + j] == TileVisitingKind.FogOfWar) {
                    backgroundColor = "\u001B[48;5;254m";
                }

                if (j % 2 == 1)
                    drawMainDetails(mapString, istart, jstart, x + i + remainder, y + j, backgroundColor);
                else
                    drawMainDetails(mapString, istart, jstart, x + i, y + j, backgroundColor);

                if (j % 2 == 0 & terrainStates[x + i][y + j] == TerrainState.VISIBLE) {
                    if (terrains[x + i][y + j].getCivilization() == null) {
                        mapString[istart + 1][jstart + 5] = backgroundColor + " " + "\u001B[0m";
                    } else {
                        mapString[istart + 1][jstart + 5] = backgroundColor
                                + terrains[x + i][y + j].getCivilization().getName().charAt(0) + "\u001B[0m";
                    }
                } else {
                    if (terrains[x + i + remainder][y + j].getCivilization() == null) {
                        mapString[istart + 1][jstart + 5] = backgroundColor + " " + "\u001B[0m"
                        mapString[istart + 1][jstart + 5] = backgroundColor
                                + terrains[x + i + remainder][y + j].getCivilization().getName().charAt(0)
                                + "\u001B[0m";
                    }
                }
            }
        }

    }

    public String showMap(int x, int y) {
        x = handelXBoundaries(x, y);
        y = handelYBoundaries(y);

        if (!isValidTerran(x, y)) {
            return "ERROR x: " + x + " , y: " + y + " in show map is invalid";
        }
        String[][] mapString = new String[21][51];
        for (int i = 0; i < mapString.length; i++) {
            for (int j = 0; j < mapString[i].length; j++) {
                mapString[i][j] = " ";
            }
        }

        drawMap(mapString, x, y);

        StringBuilder mapStringBuilder = new StringBuilder();
        for (int i = 0; i < mapString.length; i++) {
            for (int j = 0; j < mapString[i].length; j++) {
                mapStringBuilder.append(mapString[i][j]);
            }
            mapStringBuilder.append("\n");
        }

        return mapStringBuilder.toString();
    }

    public String showMap(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        return showMap(x, y);
    }

    public String showDetails(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        isValidTerrian(x, y);
        Terrain terrain = MainMap().getTerrain(x, y);
        TileVisitingKind terrainState = GameDatabase.getGameDatabase().getCurrentCivilization().getTileVisitingKind(x, y);

        if (terrainState == TerrainState.FOG_OF_WAR)
            return "this terrain is in fog for you";

        StringBuilder stringBuilder = new StringBuilder();
        if (terrain.getCivilization() == null)
            stringBuilder.append("this terrain belongs to: no one");
        else
            stringBuilder.append("this terrain belongs to: ").append(terrain.getCivilization().getName());
        stringBuilder.append("\n" + "Terrain type is: ")
                .append(terrain.getType()).append("\n").append("Terrain features are: ")
                .append(terrain.getTerrainFeatures())
                .append("\n");
        if (terrainState == TerrainState.VISIBLE) {
            showVisibleDetails(stringBuilder, terrain);
        }
        return stringBuilder.toString();
    }


}