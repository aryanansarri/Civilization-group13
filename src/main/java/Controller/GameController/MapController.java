package Controller.GameController;

import Models.Block.TerrainFeature;
import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Block.TileVisitingKind;
import Models.Connect;
import Models.ConsoleColors;
import Models.Improvment.Improvement;
import Models.Map;
import Models.Resources.Resource;
import Models.Technology.Technology;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class MapController {

    private Tile[][] terrains;
    private TileVisitingKind[][] tileVisitingKinds;

    public MapController(Tile[][] terrains, TileVisitingKind[][] tileVisitingKinds) {
        this.terrains = terrains;
        this.tileVisitingKinds = tileVisitingKinds;
    }

    public MapController() {

    }


    private void drawHex(String[][] mapString, int istart, int jstart) {
        for (int k = 2; k > -1; k--) {
            mapString[istart + 2 - k][jstart + k] = "/";
            mapString[istart + 2 - k][jstart + 10 - k] = "\\";
            mapString[istart + 5 - k][jstart + 2 - k] = "\\";
            mapString[istart + 5 - k][jstart + 8 + k] = "/";
        }
    }

    private String getBackgroundColor(Tile terrain) {
        TerrainType type = terrain.getTerraintype();
        if (type == TerrainType.DESERT) {
            return ConsoleColors.BROWN_BACKGROUND;
        } else if (type == TerrainType.GRASSLLAND) {
            return ConsoleColors.LIGHTGREEN_BACKGROUND;
        } else if (type == TerrainType.HILLS) {
            return ConsoleColors.DARKGREEN_BACKGROUND;
        } else if (type == TerrainType.MOUNTAIN) {
            return ConsoleColors.DARKBROWN_BACKGROUND;
        } else if (type == TerrainType.OCEAN) {
            return ConsoleColors.BLUE_BACKGROUND;
        } else if (type == TerrainType.PLAIN) {
            return ConsoleColors.GREEN_BACKGROUND;
        } else if (type == TerrainType.SNOW) {
            return ConsoleColors.WHITE_BACKGROUND;
        } else if (type == TerrainType.TUNDRA) {
            return ConsoleColors.DARKRED_BACKGROUND;
        }
        return ConsoleColors.RESET;
    }

    private void drawMainDetails(String[][] mapString, int istart, int jstart, int xCenter, int yCenter,
                                 String backgroundColor) {
        for (int k = 2; k > -1; k--) {
            for (int z = jstart + k + 1; z < jstart + k + 1 + 5 + 4 - 2 * k; z++) {
                mapString[istart + 2 - k][z] = backgroundColor + " " + ConsoleColors.RESET;
                mapString[istart + 3 + k][z] = backgroundColor + " " + ConsoleColors.RESET;
            }
        }

        if (xCenter / 10 != 0)
            mapString[istart + 2][jstart + 3] = ConsoleColors.BLACK + backgroundColor + xCenter / 10
                    + ConsoleColors.RESET;
        mapString[istart + 2][jstart + 4] = ConsoleColors.BLACK + backgroundColor + xCenter % 10 + ConsoleColors.RESET;
        mapString[istart + 2][jstart + 5] = ConsoleColors.BLACK + backgroundColor + "," + ConsoleColors.RESET;
        if (yCenter / 10 != 0)
            mapString[istart + 2][jstart + 6] = ConsoleColors.BLACK + backgroundColor + yCenter / 10
                    + ConsoleColors.RESET;
        mapString[istart + 2][jstart + 7] = ConsoleColors.BLACK + backgroundColor + yCenter % 10 + ConsoleColors.RESET;
        for (int k = 0; k < 5; k++) {
            mapString[istart + 5][jstart + 3 + k] = backgroundColor + "_" + ConsoleColors.RESET;
        }
    }

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
                    backgroundColor = getBackgroundColor(terrains[x + i + remainder][y + j]);
                else
                    backgroundColor = getBackgroundColor(terrains[x + i][y + j]);
                int istart = i * 6;
                int jstart = j * 8;
                if (j % 2 == 1) {
                    istart += 3;
                }

                drawHex(mapString, istart, jstart);

                if (j % 2 == 1) {
                    if (tileVisitingKinds[x + i + remainder][y + j] == TileVisitingKind.FogOfWar) {
                        backgroundColor = ConsoleColors.GRAY_BACKGROUND;
                    }
                } else if (tileVisitingKinds[x + i][y + j] == TileVisitingKind.FogOfWar) {
                    backgroundColor = ConsoleColors.GRAY_BACKGROUND;
                }

                if (j % 2 == 1)
                    drawMainDetails(mapString, istart, jstart, x + i + remainder, y + j, backgroundColor);
                else
                    drawMainDetails(mapString, istart, jstart, x + i, y + j, backgroundColor);

                if (j % 2 == 0 & tileVisitingKinds[x + i][y + j] == TileVisitingKind.Visible) {
                    // TODO set name in civilization constructor
                    if (terrains[x + i][y + j].getOwner() == null) {
                        mapString[istart + 1][jstart + 5] = backgroundColor + " " + ConsoleColors.RESET;
                    } else {
                        mapString[istart + 1][jstart + 5] = backgroundColor
                                + terrains[x + i][y + j].getOwner().getCivilizationName().charAt(0) + ConsoleColors.RESET;
                    }
                } else {
                    if (terrains[x + i + remainder][y + j].getOwner() == null) {
                        mapString[istart + 1][jstart + 5] = backgroundColor + " " + ConsoleColors.RESET;
                    } else {
                        mapString[istart + 1][jstart + 5] = backgroundColor
                                + terrains[x + i + remainder][y + j].getOwner().getCivilizationName().charAt(0)
                                + ConsoleColors.RESET;
                    }
                }
            }
        }

    }

    private int handelXBoundaries(int x, int y) {
        if (x > Map.getRows() - 3)
            return Map.getRows() - 3;
        if (x < 0)
            return 0;
        if (y % 2 == 1 && x > Map.getRows() - 4)
            return Map.getRows() - 4;
        return x;
    }

    private int handelYBoundaries(int y) {
        if (y > Map.getColumns() - 6)
            return Map.getColumns() - 6;
        if (y < 0)
            return 0;
        return y;
    }

    public boolean TerrainValidate(int x, int y) {
        return x < Map.getRows() && x >= 0 && y < Map.getColumns() && y >= 0;
    }

    public String showMap(int x, int y) {
        x = handelXBoundaries(x, y);
        y = handelYBoundaries(y);

        if (!TerrainValidate(x, y)) {
            return "ERROR x: " + x + " , y: " + y + " in show map is invalid";
        }

        // creating mapString
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

    public void showRivers(StringBuilder stringBuilder, Tile terrain) {
        if (terrain.getTerrainFeatures().contains(TerrainFeature.RIVER)) {
            stringBuilder.append("this terrain has a river with following terrains:\n");
            for (Tile adjTerrain : terrain.getSurroundingTerrain()) {
                if (adjTerrain.getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                    stringBuilder.append(
                            "terrain on x: " + adjTerrain.getX() + " y: " + adjTerrain.getY() + "\n");
                }
            }
        }
    }

    private void showVisibleDetails(StringBuilder stringBuilder, Tile terrain) {
        if (terrain.getCivilianUnit() == null) {
            stringBuilder.append("there is no civilization unit in this terrain\n");
        } else {
            stringBuilder.append("civilzation unit: " + terrain.getCivilianUnit().getType() + " belonging to: "
                    + terrain.getCivilianUnit().getCivilization().getCivilizationName() + "\n");
        }
        if (terrain.getMilitaryUnit() == null) {
            stringBuilder.append("there is no military unit in this terrain\n");
        } else {
            stringBuilder.append("military unit: " + terrain.getMilitaryUnit().getType() + " belonging to: " +
                    terrain.getMilitaryUnit().getCivilization().getCivilizationName() + "\n");
        }
        showResources(stringBuilder, terrain);
        showImprovements(stringBuilder, terrain);
        showRivers(stringBuilder, terrain);
    }

    private void showImprovements(StringBuilder stringBuilder, Tile terrain) {
        Connect<Improvement, Boolean> improvementPair = terrain.getImprovementPair();
        if (improvementPair != null) {
            Improvement improvement = improvementPair.getFirst();
            if (improvement != null && improvementPair.getSecond() == true) {
                if (improvement.getNeededTech() == null || GameDatabase.getGameDatabase().getCurrentCivilization()
                        .getCivilizationTechnology().getPassedTechnology().contains(improvement.getNeededTech())) {
                    stringBuilder.append("this terrain has " + improvement.name() + " improvement\n");
                }
            }
        }
    }

    private void showResources(StringBuilder stringBuilder, Tile terrain) {
        stringBuilder.append("list of resources in this terrain:\n");
        ArrayList<Technology> technologies = GameDatabase.getGameDatabase().getCurrentCivilization().getCivilizationTechnology()
                .getPassedTechnology();
        for (Resource resource : terrain.getResources()) {
            if (technologies.contains(resource.getRequiredTechnology())) {
                stringBuilder.append(resource + "\n");
            }
            if (resource.getRequiredTechnology() == null) {
                stringBuilder.append(resource + "\n");
            }
        }
    }

    public String showDetails(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        TerrainValidate(x, y);
        Tile terrain = GameDatabase.getGameDatabase().getOriginalMap().getTile(x, y);
        TileVisitingKind terrainState = GameDatabase.getGameDatabase().getCurrentCivilization().getTileVisitingKind(x, y);

        if (terrainState == TileVisitingKind.FogOfWar)
            return "this terrain is in fog for you";

        StringBuilder stringBuilder = new StringBuilder();
        if (terrain.getOwner() == null)
            stringBuilder.append("this terrain belongs to: no one");
        else
            stringBuilder.append("this terrain belongs to: ").append(terrain.getOwner().getCivilizationName());
        stringBuilder.append("\n" + "Terrain type is: ")
                .append(terrain.getTerraintype()).append("\n").append("Terrain features are: ")
                .append(terrain.getTerrainFeatures())
                .append("\n");
        if (terrainState == TileVisitingKind.Visible) {
            showVisibleDetails(stringBuilder, terrain);
        }
        return stringBuilder.toString();
    }
}