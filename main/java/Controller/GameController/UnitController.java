package Controller.GameController;

import Models.Block.Tile;
import Models.Civilization.City;
import Models.Civilization.Civilization;
import Models.Units.*;

public class selectSettlerUnitController {
    public boolean createUnit(Civilization civilization, Tile tile, String type) {
        Unit unit = new Unit("type", tile, civilization);
        civilization.getUnits().add(unit);
        return addUnitToMap(unit);
    }
    public boolean deleteUnit(Unit unit,Civilization civilization) {
        if(unit == null)
            return false;
        civilization.getUnits().remove(unit);
        return removeUnitFromMap(unit,civilization);
    }

    private boolean removeUnitFromMap(Unit unit,Civilization civilization) {
        if(unit == null||unit.getTile()==null) return false;
        if(unit instanceof CivilianUnit)
            unit.getTile().setCivilianUnit(null);
        else unit.getTile().setMilitaryUnit(null);
        //todo nameyesh hazf az map
        return true;
    }

    public boolean isUnitHomeTile(Unit unit, Tile tile,Civilization civilization){
        return unit.getTile().getOwner().equals(civilization);
    }

    public boolean moveUnit(Unit unit , Tile destination,Civilization civilization){
        if(civilization.isTileVisible(destination.getX(), destination.getY()) &&
                destination.canPass()) {
            unit.move(destination);
            // todo namayesh harekat
            return true;
        }


        else return false;
    }


    public boolean addUnitToMap (Unit unit ){
        if(unit == null || unit.getTile() == null ) return false;
        if(unit instanceof CivilianUnit)
            unit.getTile().setCivilianUnit((CivilianUnit) unit);
        else unit.getTile().setMilitaryUnit((MilitaryUnit) unit);
        // todo namayesh tasvir harekat kardan
        return true;
    }
    public void alertUnit(Civilization civilization)
    {
        Unit unit = civilization.getSelectedUnit();
        unit.alert();
    }
    public void awakeUnit(Civilization civilization)
    {
        Unit unit = civilization.getSelectedUnit();
        unit.wakeUp();
    }
    public void sleepUnit(Civilization civilization)
    {
        Unit unit =civilization.getSelectedUnit();
        unit.sleep();
    }
    public boolean unitFortify(Civilization civilization) {
        Unit unit =civilization.getSelectedUnit();
        if(unit == null) return false;
        if(unit instanceof CivilianUnit||
                unit  instanceof MountedUnit ||
                unit instanceof ArcherUnit)
            return false;
        unit.fortify();
        return true;
    }


    public boolean unitFortifyUntilHealed(Civilization civilization) {
        Unit unit = civilization.getSelectedUnit();
        if(unit == null|| unit instanceof CivilianUnit)
            return false;
        //unit.setAction("fortify_Until_Healed");
        return true;
    }

    public boolean unitGarrison(Civilization civilization)
    {
        Unit unit = civilization.getSelectedUnit();
        if(unit == null) return false;
        City city = unit.getTile().getCity();
        if (!city.getOwnership().equals(civilization)) return false;
        unit.garrison();
        return true;
    }

    public boolean unitFoundNewCity(Civilization civilization)
    {
        int var = 10;
        City city;
        if (civilization == null)
            return false;
        Unit unit = civilization.getSelectedUnit();
        if (!(unit instanceof SettlerUnit))
            return false;

        Tile target = unit.getTile();
        if (target == null || target.getCity()!=null)
            return false;
        do {
            // city = new City(City.getCityName(RANDOM.nextInt()), civilization, target);

        } while (/*!CityController.addCity(city) &&*/ var --> 0);
        if (var < 0)
            return false;
        ((SettlerUnit) unit).searchForCity(target);
        return true;
    }
    public boolean unitBuildImprovement(Civilization civilization, Improvement improvement)
    {
        Unit unit = civilization.getSelectedUnit();
        if(unit == null)
            return false;
        Tile tile = unit.getTile();
        if (tile == null )
            return false;

        if(!civilization.hasTechnology(improvement.neededTech))
            return false;
        if(tile.getTerrainFeature().equals("Forest")|| tile.getTerrainFeature().equals("Jungle") || tile.getTerrainFeature().equals("Marsh") )
            return false;


        if(improvement.type.equals("Farm")) {
            if(tile.getTerrainFeature().equals("Ice"))
                return false;
            unit.setAction("Build_Farm");
        }
        if(improvement.type.equals("Mine")){
            if(!tile.getTerrainType().equals("Hill"))
                return false;
            unit.setAction("BuildMine");
        }
        if(improvement.type.equals("Trading_Post")) {
            if(!civilization.hasTechnology("Trapping"))
                return false;
            unit.setAction("Build_Trading_Post");
        }
        if(improvement.type.equals("Lumber_Mill")) {
            if(!civilization.hasTechnology("Construction"))
                return false;
            unit.setAction("Build_Lumber_Mill");
        }
        if(improvement.type.equals("Pasture")) {
            if(!civilization.hasTechnology("Animal_Husbandry"))
                unit.setAction("Build_Pasture");
        }
        if(improvement.type.equals("Camp")) {
            if(!civilization.hasTechnology("Trapping"))
                return false;
            unit.setAction(UnitType.UnitAction.BUILD_CAMP);
        }
        if(improvement.type.equals("Plantation")) {
            if(!civilization.hasTechnology("Calendar") )
                return false;
            unit.setAction("Build_Plantation");
        }
        if(improvement.type.equals("Quarry")) {
            if(!civilization.hasTechnology("Engineerig"))
                return false;
            unit.setAction("Build_Quarry");
        }
        return true;
    }
    public String buildPasture() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) GameDataBase.getSelected()).getTerrain().getImprovement() == Improvement.PASTURE) {
            return "There is pasture in this position!";
        }
        if (!Improvement.PASTURE.checkIsPossible(((Worker) GameDataBase.getSelected()).getTerrain())) {
            return "Build is not possible!";
        }
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.PASTURE);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker made a pasture on " + worker.getTerrain().getCoordination().toString());
        return "Pasture created successfully!";
    }

    public String buildCamp() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) GameDataBase.getSelected()).getTerrain().getImprovement() == Improvement.CAMP) {
            return "There is camp mill in this position!";
        }
        if (!Improvement.CAMP.checkIsPossible(((Worker) GameDataBase.getSelected()).getTerrain())) {
            return "Build is not possible!";
        }
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.CAMP);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker made a camp on " + worker.getTerrain().getCoordination().toString());
        return "Camp created successfully!";
    }

    public String buildPlantation() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) GameDataBase.getSelected()).getTerrain().getImprovement() == Improvement.PLANTATION) {
            return "There is plantation in this position!";
        }
        if (!Improvement.PLANTATION.checkIsPossible(((Worker) GameDataBase.getSelected()).getTerrain())) {
            return "Build is not possible!";
        }
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.PLANTATION);
        GameDataBase.getCurrentCivilization().updateNotification(
                "a worker made a plantation on " + worker.getTerrain().getCoordination().toString());
        return "Plantation created successfully!";
    }

    public String buildQuarry() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) GameDataBase.getSelected()).getTerrain().getImprovement() == Improvement.QUARRY) {
            return "There is quarry in this position!";
        }
        if (!Improvement.QUARRY.checkIsPossible(((Worker) GameDataBase.getSelected()).getTerrain())) {
            return "Build is not possible!";
        }
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.QUARRY);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker made a quarry on " + worker.getTerrain().getCoordination().toString());
        return "Quarry created successfully!";
    }

    public String removeJungle() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (!((Worker) GameDataBase.getSelected()).getTerrain().getTerrainFeatures().contains(TerrainFeature.JUNGLE)) {
            return "There is no jungle or forrest in this place!";
        }
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.REMOVE_JUNGLE);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker removed jungle on " + worker.getTerrain().getCoordination().toString());
        return "Jungle removed successfully!";
    }

    public String removeRoute() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (!((Worker) GameDataBase.getSelected()).getTerrain().isHasRoad()) {
            return "There is no road in this place!";
        }
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.REMOVE_ROUTE);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker removed route on " + worker.getTerrain().getCoordination().toString());
        return "Road removed successfully!";
    }

    public String removeMarsh() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (!((Worker) GameDataBase.getSelected()).getTerrain().getTerrainFeatures().contains(TerrainFeature.MARSH)) {
            return "There is no marsh or forrest in this place!";
        }
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.REMOVE_MARSH);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker removed marsh on " + worker.getTerrain().getCoordination().toString());
        return "marsh removed successfully!";
    }

    public String removeForest() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (!((Worker) GameDataBase.getSelected()).getTerrain().getTerrainFeatures()
                .contains(TerrainFeature.FOREST)) {
            return "There is no forest or forrest in this place!";
        }
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.REMOVE_FOREST);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker removed forest on " + worker.getTerrain().getCoordination().toString());
        return "forest removed successfully!";
    }

    public String repair() {
        String command = checkWorker();
        if (command != null)
            return command;
        if (((Worker) GameDataBase.getSelected()).getTerrain().getImprovementPair() == null)
            return "improvementi nadarim";
        if (((Worker) GameDataBase.getSelected()).getTerrain().getImprovementPair().getValue())
            return "salem hast in improvement";
        if (((Worker) GameDataBase.getSelected()).getMakingImprovement() != null) {
            return "kargaran mashghool karand";
        }
        Worker worker = ((Worker) GameDataBase.getSelected());
        worker.makeImprovement(Improvement.REPAIR);
        GameDataBase.getCurrentCivilization()
                .updateNotification("a worker repaired on " + worker.getTerrain().getCoordination().toString());
        return "Repair successfully!";
    }

    public String move(Matcher matcher, Unit unit) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Coordination coordination = new Coordination(x, y);
        if (!coordination.isValidCoordination())
            return "position out of bounds";
        if (unit == null)
            return "no unit selected";
        if (unit.getCivilization() != GameDataBase.getCurrentCivilization())
            return "this unit doesn't belong to you good sir";
        Terrain destination = GameDataBase.getMainMap().getTerrain(x, y);
        return moveUnit(destination, unit);
    }

    private String moveUnit(Terrain destination, Unit unit) {
        GameDataBase.getCurrentCivilization().getMap().updateExploration();
        Terrain origin = unit.getTerrain();
        int MP = unit.getRemainingMove();
        int maxMp = unit.getMyType().getMovement();
        UnitType unitType = unit.getMyType();
        Coordination coordination = destination.getCoordination();
        TerrainState state = GameDataBase.getCurrentCivilization().getTerrainState(coordination.getX(),
                coordination.getY());
        if (unit.getRemainingMove() <= 0)
            return "unfortunately you don't have enough moving point to move your unit";
        if (state == TerrainState.FOG_OF_WAR)
            return "your destination is in fog of war";
        if (!isDestinationEmpty(unitType, destination))
            return "destination is not empty for you";
        return backTrack(destination, origin, MP, maxMp, unitType, unit);
        // return DjikstraPathFind(destination, origin, MP, maxMp, unitType, unit);
    }

    // private String DjikstraPathFind(Terrain destination, Terrain origin, int MP,
    // int maxMp, UnitType unitType,
    // Unit unit) {
    // return "";
    // }

    private String backTrack(Terrain destination, Terrain origin, int MP, int maxMp, UnitType unitType, Unit unit) {
        ArrayList<ArrayList<Terrain>> paths = new ArrayList<>();
        ArrayList<Terrain> path = new ArrayList<>();
        path.add(origin);

        Coordination minimum = new Coordination(Math.min(origin.getXPosition(), destination.getXPosition()),
                Math.min(origin.getYPosition(), destination.getYPosition()));
        Coordination maximum = new Coordination(Math.max(origin.getXPosition(), destination.getXPosition()),
                Math.max(origin.getYPosition(), destination.getYPosition()));

        findAllPaths(destination, origin, maxMp, paths, path, maximum, minimum);
        if (paths.isEmpty())
            return "unfortunately there is no available path for your unit to move to your desired destination";
        ArrayList<Terrain> bestPath = findBestPath(paths, unit.getMyType().getMovement(), maxMp, unitType);
        if (bestPath == null)
            return "unfortunately there is no available path for your unit to move to your desired destination";
        ArrayList<Coordination> pathCoordination = new ArrayList<>();
        setPathCoordinates(pathCoordination, bestPath);
        pathCoordination.remove(0);
        unit.setPath(pathCoordination);
        unit.move();
        GameDataBase.getCurrentCivilization().updateNotification("unit " + unitType.name() + " started moving from "
                + origin.getCoordination().toString() + " to " + destination.getCoordination().toString());
        return "unit moved successfully";
    }

    public ArrayList<Coordination> getBestPath(Terrain destination, Terrain origin, Unit unit) {
        int maxMp = unit.getMyType().getMovement();
        UnitType unitType = unit.getMyType();
        ArrayList<ArrayList<Terrain>> paths = new ArrayList<>();
        ArrayList<Terrain> path = new ArrayList<>();
        path.add(origin);

        Coordination minimum = new Coordination(Math.min(origin.getXPosition(), destination.getXPosition()),
                Math.min(origin.getYPosition(), destination.getYPosition()));
        Coordination maximum = new Coordination(Math.max(origin.getXPosition(), destination.getXPosition()),
                Math.max(origin.getYPosition(), destination.getYPosition()));
        findAllPaths(destination, origin, maxMp, paths, path, maximum, minimum);
        if (paths.isEmpty())
            return null;

        ArrayList<Terrain> bestPath = findBestPath(paths, unitType.getMovement(), maxMp, unitType);
        if (bestPath == null)
            return null;

        ArrayList<Coordination> pathCoordination = new ArrayList<>();
        setPathCoordinates(pathCoordination, bestPath);
        pathCoordination.remove(0);
        return pathCoordination;
    }

    public int turnNeedToMove(Terrain destination, Terrain origin, Unit unit) {
        Terrain currentTerrain = unit.getTerrain();
        int res = 0;
        int remainingMove = unit.getMyType().getMovement();
        ArrayList<Coordination> path = getBestPath(destination, origin, unit);
        if (path == null)
            return res;
        while (path.size() != 1) {
            for (int i = 0; i < path.size(); i++) {
                Terrain terrain = path.get(i).getTerrain();
                if (currentTerrain.isHasRoad() && terrain.isHasRoad()) {

                } else if (terrain.getTerrainFeatures().contains(TerrainFeature.RIVER)
                        && currentTerrain.getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                    remainingMove = 0;
                } else {
                    remainingMove -= terrain.getMp();
                }
                currentTerrain = terrain;
                path.remove(i);
                i = 0;
                if (remainingMove < 0) {
                    break;
                }
            }
            remainingMove = unit.getMyType().getMovement();
            res++;
        }
        return res;
    }

    private void setPathCoordinates(ArrayList<Coordination> coordinates, ArrayList<Terrain> path) {
        for (int i = 0; i < path.size(); i++) {
            coordinates.add(path.get(i).getCoordination());
        }
    }

    private boolean isDestinationEmpty(UnitType unitType, Terrain destination) {
        if (unitType.equals(UnitType.WORKER) || unitType.equals(UnitType.SETTLER)) {
            if (destination.getCivilianUnit() != null)
                return false;
        }
        if (destination.getMilitaryUnit() != null)
            return false;
        return true;
    }

    private ArrayList<Terrain> findBestPath(ArrayList<ArrayList<Terrain>> paths, int MP, int maxMp, UnitType unitType) {
        sortPathsByMP(paths);
        for (ArrayList<Terrain> path : paths) {
            if (isPathAvailable(path, MP, maxMp, unitType)) {
                return path;
            }
        }
        return null;
    }

    private boolean isPathAvailable(ArrayList<Terrain> path, int MP, int MaxMp, UnitType unitType) {
        if (path.isEmpty())
            return false;
        MP -= path.get(0).getMp();
        for (int i = 1; i < path.size(); i++) {
            Terrain terrain = path.get(i);
            if (terrain.isHasRoad() && path.get(i - 1).isHasRoad()) {

            } else if (terrain.getTerrainFeatures().contains(TerrainFeature.RIVER)
                    && path.get(i - 1).getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                MP = 0;
            } else {
                MP -= terrain.getMp();
            }
            if (MP <= 0) {
                MP = MaxMp;
                if (unitType.equals(UnitType.SETTLER) || unitType.equals(UnitType.WORKER)) {
                    if (terrain.getCivilianUnit() != null)
                        return false;
                } else if (terrain.getMilitaryUnit() != null)
                    return false;
            }
        }
        return true;
    }

    private void sortPathsByMP(ArrayList<ArrayList<Terrain>> paths) {
        for (int i = 0; i < paths.size(); i++) {
            for (int j = i + 1; j < paths.size(); j++) {
                if (getPathMP(paths.get(j)) < getPathMP(paths.get(i))) {
                    ArrayList<Terrain> pathi = paths.get(i);
                    ArrayList<Terrain> pathj = paths.get(j);
                    paths.add(i, pathj);
                    paths.remove(i);
                    paths.add(j, pathi);
                    paths.remove(j);
                }
            }
        }
    }
}
