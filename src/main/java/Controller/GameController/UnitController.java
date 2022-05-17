package Controller.GameController;

import Models.Block.TerrainFeature;
import Models.Block.TerrainType;
import Models.Block.Tile;
import Models.Block.TileVisitingKind;
import Models.Civilizations.City;
import Models.Civilizations.Civilization;
import Models.Coordinates;
import Models.Improvment.Improvement;
import Models.Map;
import Models.Technology.Technology;
import Models.Units.*;

import java.util.ArrayList;
import java.util.regex.Matcher;

import static Models.Units.Unit.getCivilization;

public class UnitController {

        public boolean createUnit(Civilization civilization, Tile tile, UnitType type) {
            Unit unit = new Unit(type, tile, civilization);
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
            return true;
        }

        public boolean isUnitHomeTile(Unit unit, Tile tile,Civilization civilization){
            return unit.getTile().getOwner().equals(civilization);
        }

        public String moveUnit(Unit unit , Tile destination,Civilization civilization){

            GameDatabase.getCurrentCivilization().getCivilizationMap().updateExploration();
            Tile origin = unit.getTile();
            int Movement = unit.getRemindMove();
            int maxMovement = unit.getType().getMovementPoint();
            UnitType unitType = unit.getType();
            Coordinates coordinates = destination.getCordination();
            TileVisitingKind state = GameDatabase.getCurrentCivilization().getTileVisitingKind(coordinates.getX(), coordinates.getY());
            if (unit.getRemindMove() <= 0)
                return "unfortunately you don't have enough moving point to move your unit";
            if (state == TileVisitingKind.FogOfWar)
                return "your destination is in fog of war";
            if (!isDestinationEmpty(unitType, destination))
                return "destination is not empty for you";
            return backTrack(destination, origin, Movement, maxMovement, unitType, unit);
        }
    public String moveUnit(Matcher matcher) {
        if (GameDatabase.getSelected() instanceof Unit)
            return move(matcher, (Unit) GameDatabase.getSelected());
        return "no unit selected";
    }


        public boolean addUnitToMap (Unit unit ){
            if(unit == null || unit.getTile() == null ) return false;
            if(unit instanceof CivilianUnit)
                unit.getTile().setCivilianUnit((CivilianUnit) unit);
            else unit.getTile().setMilitaryUnit((MilitaryUnit) unit);
            return true;
        }
        public void alertUnit(Civilization civilization)
        {
            Unit unit = (Unit)GameDatabase.getSelected();
            unit.alert();
        }
        public void awakeUnit(Civilization civilization)
        {
            Unit unit = (Unit)GameDatabase.getSelected();
            unit.wakeUp();
        }
        public String sleep(Civilization civilization)
        {
            if (!(GameDatabase.getSelected() instanceof Unit)) {
                return "No unit selected";
            }
            if (((Unit) GameDatabase.getSelected()).getCivilization() != GameDatabase.getCurrentCivilization()) {
                return "selected har nobat new shavad";
            }
            Unit unit = ((Unit) GameDatabase.getSelected());
            unit.sleep();
            GameDatabase.getCurrentCivilization().updateNotification("Unit " + unit.getType().name() + " on "
                    + unit.getTile().getCordination().toString() + " is now sleeping!");
            return "Done. Unit slept";
        }

    public void delete(Unit unit) {
        getCivilization().removeUnit(unit);
        for (Tile[] terrains : GameDatabase.getOriginalMap().getTile()) {
            for (Tile tile : terrains) {
                if (tile.getMilitaryUnit() == unit)
                    tile.setMilitaryUnit(null);
                if (tile.getCivilianUnit() == unit)
                    tile.setCivilianUnit(null);
            }
        }
    }


    public String alert() {
        if (!(GameDatabase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDatabase.getSelected()).getCivilization() != GameDatabase.getCurrentCivilization()) {
            return "selected har nobat new shavad";
        }
        if (!(GameDatabase.getSelected() instanceof MilitaryUnit)) {
            return "its not a military unit!";
        }
        MilitaryUnit unit = ((MilitaryUnit) GameDatabase.getSelected());
        unit.setOnAlert(true);
        GameDatabase.getCurrentCivilization().updateNotification("Military unit " + unit.getType().name() + " on "
                + unit.getTile().getCordination().toString() + " is now in alert!");
        return "Unit is alerted!";
    }


    public String fortify() {
        if (!(GameDatabase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDatabase.getSelected()).getCivilization() != GameDatabase.getCurrentCivilization()) {
            return "selected har nobat new shavad";
        }
        if (!(GameDatabase.getSelected() instanceof MilitaryUnit)) {
            return "its not a military unit!";
        }
        MilitaryUnit unit = ((MilitaryUnit) GameDatabase.getSelected());
        unit.fortify();
        GameDatabase.getCurrentCivilization().updateNotification("Military unit " + unit.getType().name() + " on "
                + unit.getTile().getCordination().toString() + " is now in fortify!");
        return "Unit is fortify!";
    }

    public String fortifyHeal() {
        if (!(GameDatabase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (!(GameDatabase.getSelected() instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        if (((Unit) GameDatabase.getSelected()).getCivilization() != GameDatabase.getCurrentCivilization()) {
            return "selected har nobat new shavad";
        }
        MilitaryUnit unit = ((MilitaryUnit) GameDatabase.getSelected());
        unit.setFortifyHeal(true);
        GameDatabase.getCurrentCivilization().updateNotification("Military unit " + unit.getType().name() + " on "
                + unit.getTile().getCordination().toString() + " is now in fortify until full health!");
        return "Unit is fortify until heal!";
    }



    public String setUp() {
        if (!(GameDatabase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDatabase.getSelected()).getCivilization() != GameDatabase.getCurrentCivilization()) {
            return "selected har nobat new shavad";
        }
        if (!(GameDatabase.getSelected() instanceof MilitaryUnit)) {
            return "This is not a military unit!";
        }
        if (!UnitType.getSiegeUnits().contains(((Unit) GameDatabase.getSelected()).getType()))
            return "this unit can not siege";
        SiegeUnit unit = ((SiegeUnit) GameDatabase.getSelected());
        unit.setUp();
        GameDatabase.getCurrentCivilization().updateNotification("Military unit " + unit.getType().name() + " on "
                + unit.getTile().getCordination().toString() + " is now set up!");
        return "Unit is set up!";
    }



    public String pillage() {
        if (!(GameDatabase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDatabase.getSelected()).getCivilization() != GameDatabase.getCurrentCivilization()) {
            return "selected har nobat new shavad";
        }
        if (!(GameDatabase.getSelected() instanceof MilitaryUnit)) {
            return "its not a military unit";
        }
        if (((MilitaryUnit) GameDatabase.getSelected()).getTile().getImprovement() == null) {
            return "There is not improvement in this area";
        }
        if (!((MilitaryUnit) GameDatabase.getSelected()).getTile().getImprovementConnector().getSecond()) {
            return "improvement kharabe va dobare pillage nemishe";
        }
        MilitaryUnit unit = ((MilitaryUnit) GameDatabase.getSelected());
        unit.pillage();
        GameDatabase.getCurrentCivilization().updateNotification("Military unit " + unit.getType().name() + " on "
                + unit.getTile().getCordination().toString() + " pillaged the city");
        return "City   pillaged";
    }

    public String garrison() {
        if (!(GameDatabase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDatabase.getSelected()).getCivilization() != GameDatabase.getCurrentCivilization()) {
            return "selected har nobat new shavad";
        }
        if (!(GameDatabase.getSelected() instanceof MilitaryUnit)) {
            return "its not a military unit!";
        }
        if (!(((MilitaryUnit) GameDatabase.getSelected()).getTile().getCity() instanceof City)) {
            return "This unit is not in city!";
        }
        MilitaryUnit unit = ((MilitaryUnit) GameDatabase.getSelected());
        unit.garrison();
        GameDatabase.getCurrentCivilization().updateNotification("Military unit " + unit.getType().name() + " on "
                + unit.getTile().getCordination().toString() + " is now in garrison!");
        return "Unit is in garrison!";
    }




    public String doNothing() {
        if (!(GameDatabase.getSelected() instanceof Unit)) {
            return "No unit selected";
        }
        if (((Unit) GameDatabase.getSelected()).getCivilization() != GameDatabase.getCurrentCivilization()) {
            return "selected har nobat new shavad";
        }
        ((Unit) GameDatabase.getSelected()).DoNothing();
        return "done!";
    }
    
    public String wake(){
        if (!(GameDatabase.getSelected() instanceof Unit)) {
            return "No unit selected!";
        }
        if (((Unit) GameDatabase.getSelected()).getCivilization() != GameDatabase.getCurrentCivilization()) {
            return "selectedo bayad har turn new mikardim";
        }
        Unit unit = (Unit) GameDatabase.getSelected();
        unit.wakeUp();
        GameDatabase.getCurrentCivilization().updateNotification("Unit " + unit.getType().name() + " on "
                + unit.getTile().getCordination().toString() + " is now awake!");
        return "Unit waked up  !";
    }



        public boolean unitFortify(Civilization civilization) {
            Unit unit = (Unit)GameDatabase.getSelected();
            if(unit == null) return false;
            if(unit instanceof CivilianUnit||
                    !(unit  instanceof MilitaryUnit))
                return false;
            unit.fortify();
            return true;
        }


        public boolean unitFortifyUntilHealed(Civilization civilization) {
            Unit unit = (Unit)GameDatabase.getSelected();
            if(unit == null|| unit instanceof CivilianUnit)
                return false;
            //unit.setAction("fortify_Until_Healed");
            return true;
        }

        public boolean unitGarrison(Civilization civilization)
        {
            Unit unit = (Unit)GameDatabase.getSelected();
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
            Unit unit = (Unit)GameDatabase.getSelected();
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
            Unit unit = (Unit)GameDatabase.getSelected();
            if(unit == null)
                return false;
            Tile tile = unit.getTile();
            if (tile == null )
                return false;

            if(!civilization.getCivilizationTechnology().equals(improvement.getNeededTech()))
                return false;
            if(tile.getTerrainFeature().equals(TerrainFeature.Forest)|| tile.getTerrainFeature().equals(TerrainFeature.Jungle) || tile.getTerrainFeature().equals(TerrainFeature.Marsh) )
                return false;


            if(improvement.getType().equals("Farm")) {
                if(tile.getTerrainFeature().equals(TerrainFeature.Ice))
                    return false;
                unit.setAction("Build_Farm");
            }
            if(improvement.getType().equals("Mine")){
                if(!tile.getTerraintype().equals(TerrainType.HILLS))
                    return false;
                unit.setAction("BuildMine");
            }
            if(improvement.getType().equals("Trading_Post")) {
                if(!civilization.hasTechnology("Trapping"))
                    return false;
                unit.setAction("Build_Trading_Post");
            }
            if(improvement.getType().equals("Lumber_Mill")) {
                if(!civilization.hasTechnology("Construction"))
                    return false;
                unit.setAction("Build_Lumber_Mill");
            }
            if(improvement.getType().equals("Pasture")) {
                if(!civilization.hasTechnology("Animal_Husbandry"))
                    unit.setAction("Build_Pasture");
            }
            if(improvement.getType().equals("Camp")) {
                if(!civilization.hasTechnology("Trapping"))
                    return false;
                unit.setAction("Build_Camp");
            }
            if(improvement.getType().equals("Plantation")) {
                if(!civilization.hasTechnology("Calendar") )
                    return false;
                unit.setAction("Build_Plantation");
            }
            if(improvement.getType().equals("Quarry")) {
                if(!civilization.hasTechnology("Engineering"))
                    return false;
                unit.setAction("Build_Quarry");
            }
            return true;
        }
    public String buildLumberMill() {
        if (checkWorker() != null)
            return checkWorker();
        if (!Improvement.Lumber_Mill.checkIsPossible(((Worker) GameDatabase.getSelected()).getTile())) {
            return "Cant build ! !";
        }
        if (((Worker) GameDatabase.getSelected()).getTile().getImprovement() == Improvement.Lumber_Mill) {
            return "There is lumber mill here!";
        }
        if (((Worker) GameDatabase.getSelected()).getMakeImprovement() != null) {
            return "They are busy at the moment";
        }
        Worker worker = ((Worker) GameDatabase.getSelected());
        worker.makeImprovement(Improvement.Lumber_Mill);
        GameDatabase.getCurrentCivilization().updateNotification(
                "a worker made a lubmermill on " + worker.getTile().getCordination().toString());
        return "Lumber_mill created !";
    }


        public String buildRoad(){
            if (checkWorker() != null)
                return checkWorker();
            if (((Worker) GameDatabase.getSelected()).getMakeImprovement() != null) {
                return "They are busy at the moment";
            }
            if (((Worker) GameDatabase.getSelected()).getTile().isHasRoad()) {
                return "There is road here!";
            }
            Worker worker = ((Worker) GameDatabase.getSelected());
            worker.makeImprovement(Improvement.ROAD);
            GameDatabase.getCurrentCivilization()
                    .updateNotification("A worker made a road on " + worker.getTile().getCordination().toString());
            return "Road created !";
        }

         public String buildFarm(){
             if (checkWorker() != null)
                 return checkWorker();
             if (((Worker) GameDatabase.getSelected()).getTile().getImprovement() == Improvement.Farm) {
                 return "There is farm here!";
             }
             if (!Improvement.Farm.checkIsPossible(((Worker) GameDatabase.getSelected()).getTile())) {
                 return "Cant build !";
             }
             if (((Worker) GameDatabase.getSelected()).getMakeImprovement() != null) {
                 return "They are busy at the moment";
             }
             Worker worker = ((Worker) GameDatabase.getSelected());
             worker.makeImprovement(Improvement.Farm);
             GameDatabase.getCurrentCivilization()
                     .updateNotification("a worker made a farm on " + worker.getTile().getCordination().toString());
             return "Farm created !";
         }

    public String buildMine() {
        if (checkWorker() != null)
            return checkWorker();
        if (!Improvement.Mine.checkIsPossible(((Worker) GameDatabase.getSelected()).getTile())) {
            return "Cant build !";
        }
        if (((Worker) GameDatabase.getSelected()).getTile().getImprovement() == Improvement.Mine) {
            return "There is mine here!";
        }
        if (((Worker) GameDatabase.getSelected()).getMakeImprovement() != null) {
            return "They are busy at the moment";
        }
        Worker worker = (Worker) GameDatabase.getSelected();
        worker.makeImprovement(Improvement.Mine);
        GameDatabase.getCurrentCivilization()
                .updateNotification("a worker made a mine on " + worker.getTile().getCordination().toString());
        return "Mine created !";
    }

    public String buildTradingPost() {
        if (checkWorker() != null)
            return checkWorker();
        if (!Improvement.Trading_Post.checkIsPossible(((Worker) GameDatabase.getSelected()).getTile())) {
            return "Cant build !";
        }
        if (((Worker) GameDatabase.getSelected()).getTile().getImprovement() == Improvement.Trading_Post) {
            return "There is farm here!";
        }
        if (((Worker) GameDatabase.getSelected()).getMakeImprovement() != null) {
            return "They are busy at the moment";
        }
        Worker worker = ((Worker) GameDatabase.getSelected());
        worker.makeImprovement(Improvement.Trading_Post);
        GameDatabase.getCurrentCivilization().updateNotification(
                "a worker made a trading post on " + worker.getTile().getCordination().toString());
        return "Farm created !";
    }




        public String buildPasture() {
            String command = checkWorker();
            if (command != null)
                return command;
            if (((Worker) GameDatabase.getSelected()).getTile().getImprovement() == Improvement.Pasture) {
                return "There is pasture here!";
            }
            if (!Improvement.Pasture.checkIsPossible(((Worker) GameDatabase.getSelected()).getTile())) {
                return "can build";
            }
            if (((Worker) GameDatabase.getSelected()).getMakeImprovement() != null) {
                return "They are busy at the moment";
            }
            Worker worker = ((Worker) GameDatabase.getSelected());
            worker.makeImprovement(Improvement.Pasture);
            GameDatabase.getCurrentCivilization()
                    .updateNotification("a worker made a pasture on " + worker.getTile().getCordination().toString());
            return "Pasture created !";
        }

        public String buildCamp() {
            String command = checkWorker();
            if (command != null)
                return command;
            if (((Worker) GameDatabase.getSelected()).getTile().getImprovement() == Improvement.Camp) {
                return "There is camp mill here!";
            }
            if (!Improvement.Camp.checkIsPossible(((Worker) GameDatabase.getSelected()).getTile())) {
                return "Cant build !";
            }
            if (((Worker) GameDatabase.getSelected()).getMakeImprovement() != null) {
                return "They are busy at the moment";
            }
            Worker worker = ((Worker) GameDatabase.getSelected());
            worker.makeImprovement(Improvement.Camp);
            GameDatabase.getCurrentCivilization()
                    .updateNotification("a worker made a camp on " + worker.getTile().getCordination().toString());
            return "Camp created !";
        }

        public String buildPlantation() {
            String command = checkWorker();
            if (command != null)
                return command;
            if (((Worker) GameDatabase.getSelected()).getTile().getImprovement() == Improvement.Plantation) {
                return "There is plantation here!";
            }
            if (!Improvement.Plantation.checkIsPossible(((Worker) GameDatabase.getSelected()).getTile())) {
                return "Cant build !";
            }
            if (((Worker) GameDatabase.getSelected()).getMakeImprovement() != null) {
                return "They are busy at the moment";
            }
            Worker worker = ((Worker) GameDatabase.getSelected());
            worker.makeImprovement(Improvement.Plantation);
            GameDatabase.getCurrentCivilization().updateNotification(
                    "a worker made a plantation on " + worker.getTile().getCordination().toString());
            return "Plantation created !";
        }

        public String buildQuarry() {
            String command = checkWorker();
            if (command != null)
                return command;
            if (((Worker) GameDatabase.getSelected()).getTile().getImprovement() == Improvement.Quarry) {
                return "There is quarry here!";
            }
            if (!Improvement.Quarry.checkIsPossible(((Worker) GameDatabase.getSelected()).getTile())) {
                return "Cant build !";
            }
            if (((Worker) GameDatabase.getSelected()).getImprovement() != null) {
                return "They are busy at the moment";
            }
            Worker worker = ((Worker) GameDatabase.getSelected());
            worker.makeImprovement(Improvement.Quarry);
            GameDatabase.getCurrentCivilization()
                    .updateNotification("a worker made a quarry on " + worker.getTile().getCordination().toString());
            return "Quarry created !";
        }

        public String removeJungle() {
            String command = checkWorker();
            if (command != null)
                return command;
            if (!((Worker) GameDatabase.getSelected()).getTile().getTerrainFeatures().contains(TerrainFeature.Jungle)) {
                return "There is no jungle or forrest in this place!";
            }
            if (((Worker) GameDatabase.getSelected()).getImprovement() != null) {
                return "They are busy at the moment";
            }
            Worker worker = ((Worker) GameDatabase.getSelected());
            worker.makeImprovement(Improvement.REMOVE_JUNGLE);
            GameDatabase.getCurrentCivilization()
                    .updateNotification("a worker removed jungle on " + worker.getTile().getCordination().toString());
            return "Jungle removed !";
        }

        public String removeRoute() {
            String command = checkWorker();
            if (command != null)
                return command;
            if (!((Worker) GameDatabase.getSelected()).getTile().isHasRoad()) {
                return "There is no road in this place!";
            }
            if (((Worker) GameDatabase.getSelected()).getImprovement() != null) {
                return "They are busy at the moment";
            }
            Worker worker = ((Worker) GameDatabase.getSelected());
            worker.makeImprovement(Improvement.REMOVE_ROUTE);
            GameDatabase.getCurrentCivilization()
                    .updateNotification("a worker removed route on " + worker.getTile().getCordination().toString());
            return "Road removed !";
        }

        public String removeMarsh() {
            String command = checkWorker();
            if (command != null)
                return command;
            if (!((Worker) GameDatabase.getSelected()).getTile().getTerrainFeatures().contains(TerrainFeature.Marsh)) {
                return "There is no marsh or forrest in this place!";
            }
            if (((Worker) GameDatabase.getSelected()).getMakeImprovement() != null) {
                return "They are busy at the moment";
            }
            Worker worker = ((Worker) GameDatabase.getSelected());
            worker.makeImprovement(Improvement.REMOVE_MARSH);
            GameDatabase.getCurrentCivilization()
                    .updateNotification("a worker removed marsh on " + worker.getTile().getCordination().toString());
            return "marsh removed !";
        }

        public String removeForest() {
            String command = checkWorker();
            if (command != null)
                return command;
            if (!((Worker) GameDatabase.getSelected()).getTile().getTerrainFeatures()
                    .contains(TerrainFeature.Forest)) {
                return "no forest here!";
            }
            if (((Worker) GameDatabase.getSelected()).getImprovement() != null) {
                return "They are busy at the moment";
            }
            Worker worker = ((Worker) GameDatabase.getSelected());
            worker.makeImprovement(Improvement.REMOVE_FOREST);
            GameDatabase.getCurrentCivilization()
                    .updateNotification("a worker removed forest on " + worker.getTile().getCordination().toString());
            return "forest removed !";
        }

        public String repair() {
            String command = checkWorker();
            if (command != null)
                return command;
            if (((Worker) GameDatabase.getSelected()).getTile().getImprovementConnector() == null)
                return "there is no improvements";
            if (((Worker) GameDatabase.getSelected()).getTile().getImprovementConnector().getSecond())
                return " improvement saaleme";
            if (((Worker) GameDatabase.getSelected()).getImprovement() != null) {
                return "They are busy at the moment";
            }
            Worker worker = ((Worker) GameDatabase.getSelected());
            worker.makeImprovement(Improvement.REPAIR);
            GameDatabase.getCurrentCivilization()
                    .updateNotification("a worker repaired on " + worker.getTile().getCordination().toString());
            return "Repaired!";
        }

        public String move(Matcher matcher, Unit unit) {
            int x = Integer.parseInt(matcher.group("x"));
            int y = Integer.parseInt(matcher.group("y"));
            Coordinates coordination = new Coordinates(x, y);
            if (!coordination.isValidCoordination(GameDatabase.getCurrentCivilization().getCivilizationMap()))
                return "position out of bounds";
            if (unit == null)
                return "no unit selected";
            if (getCivilization() != GameDatabase.getCurrentCivilization())
                return "this unit doesn't belong to you good sir";
            Tile destination = GameDatabase.getOriginalMap().getTile(x, y);
            return moveUnit(destination, unit);
        }

        private String moveUnit(Tile destination, Unit unit) {
            GameDatabase.getCurrentCivilization().getCivilizationMap().updateExploration();
            Tile origin = unit.getTile();
            int MP = unit.getRemindMove();
            int maxMp = unit.getType().getMovementPoint();
            UnitType unitType = unit.getType();
            Coordinates coordination = destination.getCordination();
            TileVisitingKind state = GameDatabase.getCurrentCivilization().getTileVisitingKind(coordination.getX(),
                    coordination.getY());
            if (unit.getRemindMove() <= 0)
                return "unfortunately you don't have enough moving point to move your unit";
            if (state == TileVisitingKind.FogOfWar)
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

    public String showWorkerInfo() {
        String command = checkWorker();
        if (command != null)
            return command;
        return ((Worker) GameDatabase.getSelected()).showInfo();
    }

        private String checkWorker() {
            if (!(GameDatabase.getSelected() instanceof Unit)) {
                return "No unit selected";
            }
            if (((Unit) GameDatabase.getSelected()).getCivilization() != GameDatabase.getCurrentCivilization()) {
                return "must new every selected";
            }
            if (((Unit) GameDatabase.getSelected()).getTile().getOwner() != GameDatabase
                    .getCurrentCivilization()) {
                return "tile isnt yours!";
            }
            if (!(GameDatabase.getSelected() instanceof Worker)) {
                return "not a worker unit";
            }
            return null;
        }



        private String backTrack(Tile destination, Tile origin, int MP, int maxMp, UnitType unitType, Unit unit) {
            ArrayList<ArrayList<Tile>> paths = new ArrayList<>();
            ArrayList<Tile> path = new ArrayList<>();
            path.add(origin);

            Coordinates minimum = new Coordinates(Math.min(origin.getX(), destination.getX()),
                    Math.min(origin.getY(), destination.getY()));
            Coordinates maximum = new Coordinates(Math.max(origin.getX(), destination.getX()),
                    Math.max(origin.getY(), destination.getY()));

            findAllPaths(destination, origin, maxMp, paths, path, maximum, minimum);
            if (paths.isEmpty())
                return "unfortunately there is no available path for your unit to move to your desired destination";
            ArrayList<Tile> bestPath = findBestPath(paths, unit.getType().getMovementPoint(), maxMp, unitType);
            if (bestPath == null)
                return "unfortunately there is no available path for your unit to move to your desired destination";
            ArrayList<Coordinates> pathCoordination = new ArrayList<>();
            setPathCoordinates(pathCoordination, bestPath);
            pathCoordination.remove(0);
            unit.setPath(pathCoordination);
            unit.move(destination);
            GameDatabase.getCurrentCivilization().updateNotification("unit " + unitType.name() + " started moving from "
                    + origin.getCordination().toString() + " to " + destination.getCordination().toString());
            return "unit moved  ";
        }

        public ArrayList<Coordinates> getBestPath(Tile destination, Tile origin, Unit unit) {
            int maxMp = unit.getType().getMovementPoint();
            UnitType unitType = unit.getType();
            ArrayList<ArrayList<Tile>> paths = new ArrayList<>();
            ArrayList<Tile> path = new ArrayList<>();
            path.add(origin);

            Coordinates minimum = new Coordinates(Math.min(origin.getX(), destination.getX()),
                    Math.min(origin.getY(), destination.getY()));
            Coordinates maximum = new Coordinates(Math.max(origin.getX(), destination.getX()),
                    Math.max(origin.getY(), destination.getY()));
            findAllPaths(destination, origin, maxMp, paths, path, maximum, minimum);
            if (paths.isEmpty())
                return null;

            ArrayList<Tile> bestPath = findBestPath(paths, unitType.getMovementPoint(), maxMp, unitType);
            if (bestPath == null)
                return null;

            ArrayList<Coordinates> pathCoordination = new ArrayList<>();
            setPathCoordinates(pathCoordination, bestPath);
            pathCoordination.remove(0);
            return pathCoordination;
        }

        public int turnNeedToMove(Tile destination, Tile origin, Unit unit) {
            Tile currentTerrain = unit.getTile();
            int res = 0;
            int remainingMove = unit.getType().getMovementPoint();
            ArrayList<Coordinates> path = getBestPath(destination, origin, unit);
            if (path == null)
                return res;
            while (path.size() != 1) {
                for (int i = 0; i < path.size(); i++) {
                    Tile terrain = path.get(i).getTile();
                    if (currentTerrain.isHasRoad() && terrain.isHasRoad()) {

                    } else if (terrain.getTerrainFeatures().contains(TerrainFeature.RIVER)
                            && currentTerrain.getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                        remainingMove = 0;
                    } else {
                        remainingMove -= terrain.getMovementCost();
                    }
                    currentTerrain = terrain;
                    path.remove(i);
                    i = 0;
                    if (remainingMove < 0) {
                        break;
                    }
                }
                remainingMove = unit.getType().getMovementPoint();
                res++;
            }
            return res;
        }

        private void setPathCoordinates(ArrayList<Coordinates> coordinates, ArrayList<Tile> path) {
            for (int i = 0; i < path.size(); i++) {
                coordinates.add(path.get(i).getCordination());
            }
        }

        private boolean isDestinationEmpty(UnitType unitType, Tile destination) {
            if (unitType.equals(UnitType.WORKER) || unitType.equals(UnitType.SETTLER)) {
                if (destination.getCivilianUnit() != null)
                    return false;
            }
            if (destination.getMilitaryUnit() != null)
                return false;
            return true;
        }

        private ArrayList<Tile> findBestPath(ArrayList<ArrayList<Tile>> paths, int MP, int maxMp, UnitType unitType) {
            sortPathsByMP(paths);
            for (ArrayList<Tile> path : paths) {
                if (isPathAvailable(path, MP, maxMp, unitType)) {
                    return path;
                }
            }
            return null;
        }

        private boolean isPathWorthChecking(ArrayList<Tile> path, Tile terrain, Coordinates maximum,
                                            Coordinates minimum) {
            if (path.size() > 8)
                return false;
            if (terrain.getX() - maximum.getX() > 3 || terrain.getY() - maximum.getY() > 3)
                return false;
            if (minimum.getX() - terrain.getX() > 3 || minimum.getY() - terrain.getY() > 3)
                return false;
            return true;
        }

        private boolean isPathAvailable(ArrayList<Tile> path, int MP, int MaxMp, UnitType unitType) {
            if (path.isEmpty())
                return false;
            MP -= path.get(0).getMovementCost();
            for (int i = 1; i < path.size(); i++) {
                Tile terrain = path.get(i);
                if (terrain.isHasRoad() && path.get(i - 1).isHasRoad()) {

                } else if (terrain.getTerrainFeatures().contains(TerrainFeature.RIVER)
                        && path.get(i - 1).getTerrainFeatures().contains(TerrainFeature.RIVER)) {
                    MP = 0;
                } else {
                    MP -= terrain.getMovementCost();
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
        private int getPathMP(ArrayList<Tile> path) {
            int MP = 0;
            for (Tile terrain : path) {
                MP += terrain.getMovementCost();
            }
            return MP;
        }
        private boolean isMovePossible(int MP, Tile nextTerrain, Tile terrain) {
            if (nextTerrain.getTerraintype() == TerrainType.MOUNTAIN || nextTerrain.getTerraintype() == TerrainType.OCEAN)
                return false;
            if (GameDatabase.getCurrentCivilization().getTileVisitingKind(nextTerrain.getX(), nextTerrain.getY()) == TileVisitingKind.FogOfWar) {
                return false;
            }
            return true;
        }

        private void findAllPaths(Tile destination, Tile origin, int Movement, ArrayList<ArrayList<Tile>> paths,
                                  ArrayList<Tile> path, Coordinates maximum, Coordinates minimum) {
            if (destination == origin) {
                paths.add(path);
                return;
            }
            for (Tile nextTerrain : origin.getSurroundingTerrain()) {
                if (isMovePossible(Movement, nextTerrain, origin) && !path.contains(nextTerrain)) {
                    if (!isPathWorthChecking(path, nextTerrain, maximum, minimum))
                        continue;
                    ArrayList<Tile> nextPath = (ArrayList<Tile>) path.clone();
                    nextPath.add(nextTerrain);
                    findAllPaths(destination, nextTerrain, Movement, paths, nextPath, maximum, minimum);
                }
            }
        }

        private void sortPathsByMP(ArrayList<ArrayList<Tile>> paths) {
            for (int i = 0; i < paths.size(); i++) {
                for (int j = i + 1; j < paths.size(); j++) {
                    if (getPathMP(paths.get(j)) < getPathMP(paths.get(i))) {
                        ArrayList<Tile> pathX = paths.get(i);
                        ArrayList<Tile> pathY = paths.get(j);
                        paths.add(i, pathX);
                        paths.remove(i);
                        paths.add(j, pathY);
                        paths.remove(j);
                    }
                }
            }
        }

    }


