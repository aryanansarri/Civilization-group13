package View;

import Controller.GameController.CityController;
import Controller.GameController.GameDatabase;
import Controller.GameController.GameViewController;
import Controller.GameController.InfoController;
import Models.Civilizations.City;
import Models.Civilizations.Civilization;
import Models.Coordinates;
import Models.Technology.Technology;
import View.Regexes.*;

import java.util.regex.Matcher;

public class GameView {
    private GameViewController gameViewController = new GameViewController();

    public void run() {
        while (Menu.getMenu() == Menu.GAME) {
            String cmd = Menu.input();
            if (Menu.checkMatching(GameRegex.Exit, cmd)) {
                System.out.println(gameViewController.exitGameMenu());
            }
            else if (Menu.checkMatching(GameRegex.showCurrentMenu, cmd)) {
                System.out.println(gameViewController.showCurrentMenu());
            }
            else if (Menu.checkMatching(GameRegex.goToMenu, cmd)) {
                System.out.println(gameViewController.goToNextMenu());
            }
            else if (Menu.checkMatching(GameRegex.showMap, cmd)) {
                System.out.println("You are in map menu");
                showMap(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.showInfo, cmd)) {
               showInfo(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.selectCityByCoordinate, cmd)) {
                selectCity(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.selectCityByCoordinateType2, cmd)) {
                selectCity(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.selectMilitaryUnit, cmd)) {
                selectMilitaryUnit(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.selectSettler, cmd)) {
               selectSettler(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.selectWorker, cmd)) {
                selectWorker(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.technologyMenu, cmd)) {
                System.out.println("you are now in technology menu");
                technologyMenu(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.cheatMenu, cmd)) {
                System.out.println("you are now in cheat menu");
                cheatMenu(Menu.getMatcher());
            }
            else if (Menu.checkMatching(GameRegex.nextTurn, cmd)) {
                System.out.println(gameViewController.nextTurn());
            }
            else {
                System.out.println(gameViewController.invalidMessage());
            }
        }
    }

    private void showMap(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        gameViewController.getMapController().showMap(x, y);
        String state = "mapMenu";
        while (state.equals("mapMenu"))
        {
            String cmd = Menu.input();
            if (Menu.checkMatching(MapMenuRegex.back, cmd))
            {
                state = "NULL";
            }
            else if (Menu.checkMatching(MapMenuRegex.move, cmd))
            {
                int amount = Integer.parseInt(Menu.getMatcher().group("amount"));
                String d = Menu.getMatcher().group("d");
                if (d.equals("up")) {
                    y -= amount;
                }
                else if (d.equals("down")) {
                    y += amount;
                }
                else if (d.equals("left")) {
                    x += amount;
                }
                else {
                    x -= amount;
                }
            }
            else if (Menu.checkMatching(MapMenuRegex.show, cmd))
            {
                System.out.println(gameViewController.getMapController().showDetails(Menu.getMatcher()));
                gameViewController.getMapController().showMap(x, y);
            }
        }
    }

    private void showInfo(Matcher matcher)
    {
        Civilization current = GameDatabase.getGameDatabase().getCurrentCivilization();
        current.Refresh();
        String State = "Info";
        System.out.println(current);
        InfoController infoController = getGameViewController().getInfoController();
        while (State.equals("Info")) {
            String cmd = Menu.input();
            if (Menu.checkMatching(InfoGameRegex.research, cmd))
            {
                System.out.println(infoController.getExploreInformation());
            }
            else if (Menu.checkMatching(InfoGameRegex.units, cmd))
            {
                System.out.println(infoController.getUnitsListPanel());
            }
            else if (Menu.checkMatching(InfoGameRegex.cities, cmd))
            {
                System.out.println(infoController.getCitiesListPanel());
            }
            else if (Menu.checkMatching(InfoGameRegex.diplomacy, cmd))
            {
                System.out.println(infoController.getDiplomacyInformationPanel());
            }
            else if (Menu.checkMatching(InfoGameRegex.victory, cmd))
            {
                System.out.println(infoController.getVictoryProcess());
            }
            else if (Menu.checkMatching(InfoGameRegex.demographic, cmd))
            {

                System.out.println(infoController.getDemographics());
            }
            else if (Menu.checkMatching(InfoGameRegex.military, cmd))
            {
                System.out.println(infoController.getMilitary());
            }
            else if (Menu.checkMatching(InfoGameRegex.notification, cmd))
            {
                System.out.println(infoController.getNotification());
            }
            else if (Menu.checkMatching(InfoGameRegex.diplomatic, cmd))
            {
                System.out.println(infoController.getDiplomatics());

            }
            else if (Menu.checkMatching(InfoGameRegex.economic, cmd))
            {
                System.out.println(infoController.getEconomy());
            }
            else if (Menu.checkMatching(InfoGameRegex.deal, cmd))
            {
                System.out.println(infoController.getDeal());
            }
            else if (Menu.checkMatching(InfoGameRegex.back, cmd))
            {
                State = "back";
                System.out.println("you are taken to game menu");
            }
            else {
                System.out.println(gameViewController.invalidMessage());
            }
        }
    }

    private void selectCity(Matcher matcher)
    {
        String selectedResult = gameViewController.selectCityInCoordination(matcher);
        System.out.println(selectedResult);

        if (!selectedResult.equals("city is selected successfully"))
            return;
        String state = "select";

        Coordinates coordinates = new Coordinates(Integer.parseInt(matcher.group("x")),
                Integer.parseInt(matcher.group("y")));
        City selectedCity = GameDatabase.getGameDatabase().getCityCoordinates(coordinates);

        CityController cityController = gameViewController.getCityController();
        while (state.equals("select"))
        {
            String cmd = Menu.input();
            if (Menu.checkMatching(SelectCityRegex.back, cmd))
            {
                state = "null";
                System.out.println("you are taken to game menu");
            }
            else if (Menu.checkMatching(SelectCityRegex.showCityInfo, cmd))
            {
                System.out.println(cityController.showCityInfo(selectedCity));
            }
            else if (Menu.checkMatching(SelectCityRegex.deleteCity, cmd))
            {
                System.out.println(cityController.deleteCity());
                state = "end";
            }
            else if (Menu.checkMatching(SelectCityRegex.moveCitizen, cmd))
            {
                System.out.println(cityController.moveCitizen(Menu.getMatcher(),
                        selectedCity));
            }
            else if (Menu.checkMatching(SelectCityRegex.setCitizen, cmd))
            {
                System.out.println(cityController.setCitizen(Menu.getMatcher(), selectedCity));
            }
            else if (Menu.checkMatching(SelectCityRegex.removeCitizen, cmd))
            {
                System.out.println(cityController.removeCitizen(Menu.getMatcher()));
            }
            else if (Menu.checkMatching(SelectCityRegex.buyTile, cmd))
            {
                System.out.println(cityController.buyTile(Menu.getMatcher()));
            }
            else if (Menu.checkMatching(SelectCityRegex.attack, cmd))
            {
                System.out.println(cityController.attack(Menu.getMatcher()));
            }
            else {
                System.out.println(gameViewController.invalidMessage());
            }
        }
    }

    private void selectMilitaryUnit(Matcher matcher)
    {
        System.out.println(gameViewController.selectCivilizationUnits(Menu.getMatcher()));
        if (GameDatabase.getGameDatabase().getSelected() == null) return;
        String state = "menu";
        while (state.equals("menu"))
        {
            String cmd = Menu.input();
            if (Menu.checkMatching(selectMilitaryUnitRegex.back, cmd))
            {
                state = "NULL";
            }
            else {
                System.out.println(gameViewController.invalidMessage());
            }
        }
    }

    private void selectSettler(Matcher matcher) {
        System.out.println(gameViewController.selectCivilizationUnits(Menu.getMatcher()));
        String state = "show";
        while (state.equals("show")) {
            String cmd = Menu.input();
            if (Menu.checkMatching(selectSettlerMenu.back, cmd)) {
                state = "NULL";
            }
            else if (Menu.checkMatching(selectSettlerMenu.Sleep, cmd)) {
                System.out.println();
            }
            else if (Menu.checkMatching(selectSettlerMenu.moveUnit, cmd)) {
                System.out.println();
            }
            else if (Menu.checkMatching(selectSettlerMenu.wakeup, cmd)) {
                System.out.println();
            }
            else if (Menu.checkMatching(selectSettlerMenu.skip, cmd)) {

            }
            else if (Menu.checkMatching(selectSettlerMenu.remove, cmd )) {

            }
            else {
                System.out.println(gameViewController.invalidMessage());
            }
        }
    }

    private void selectWorker(Matcher matcher)
    {
        if (GameDatabase.getGameDatabase().getSelected() == null)
            return;
        String state = "menu";
        while (state.equals("state"))
        {
            String cmd = Menu.input();
            if (Menu.checkMatching(SelectWorkerRegex.back, cmd))
            {
                state = "NULL";
            }
            else if (Menu.checkMatching(SelectWorkerRegex.sleep, cmd))
            {

            }
            else if (Menu.checkMatching(SelectWorkerRegex.buildLumberMil, cmd))
            {

            }
            else if (Menu.checkMatching(SelectWorkerRegex.BuildMine, cmd))
            {

            }
            else if (Menu.checkMatching(SelectWorkerRegex.BuildTraidingPost, cmd))
            {

            }
            else if (Menu.checkMatching(SelectWorkerRegex.buidFarm, cmd))
            {

            }
            else if (Menu.checkMatching(SelectWorkerRegex.remove, cmd))
            {

            }
            else if (Menu.checkMatching(SelectWorkerRegex.showInfo, cmd))
            {

            }
            else if (Menu.checkMatching(SelectWorkerRegex.buildRoad, cmd))
            {

            }
            else if (Menu.checkMatching(SelectWorkerRegex.nothing, cmd))
            {

            }
            else if (Menu.checkMatching(SelectWorkerRegex.wake, cmd))
            {

            }
            else if (Menu.checkMatching(SelectWorkerRegex.moveunit, cmd))
            {

            }
            else {
                System.out.println(gameViewController.invalidMessage());
            }
        }
    }

    private void technologyMenu(Matcher matcher) {
        System.out.println(gameViewController.getTechnologyMenuController().showTechInfo());

        String state = "Technology";
        while (state.equals("Technology"))
        {
            String cmd = Menu.input();
            if (Menu.checkMatching(TechnologyRegex.back, cmd))
            {
                state = "NULL";
            }
            else if (Menu.checkMatching(TechnologyRegex.technologyChoice, cmd))
            {
                System.out.println(gameViewController.getTechnologyMenuController().
                        pickTechToResearch(Menu.getMatcher()));
            }
            else if (Menu.checkMatching(TechnologyRegex.showTechInfo, cmd))
            {
                System.out.println(gameViewController.getTechnologyMenuController().TechTree());
            }
            else
            {
                System.out.println(gameViewController.invalidMessage());
            }
        }
    }

    private void cheatMenu(Matcher matcher)
    {
        String state = "cheat";
        while (state.equals("cheat"))
        {
            String cmd = Menu.input();
            if (Menu.checkMatching(CheatRegex.back, cmd))
            {
                state = "NULL";
            }
            else if (Menu.checkMatching(CheatRegex.turnCheat, cmd))
            {
                System.out.println(gameViewController.getCheatController().turnCheat(Menu.getMatcher()));
            }
            else if (Menu.checkMatching(CheatRegex.goldCheat, cmd))
            {
                int amount = Integer.parseInt(Menu.getMatcher().group("amount"));
                gameViewController.getCheatController().goldCheat(GameDatabase.getGameDatabase().getCurrentPlayer(),
                        amount);

                System.out.println("gold cheated successfully");
            }

            else if (Menu.checkMatching(CheatRegex.addTech, cmd))
            {
                String tech = Menu.getMatcher().group("technology");
                Technology technology = Technology.get(tech);
                gameViewController.getCheatController().addTech(GameDatabase.getGameDatabase().getCurrentCivilization(),
                        technology);
                System.out.println("technology cheated shuccessfully");
            }
            else
            {
                System.out.println(gameViewController.invalidMessage());
            }
        }
    }

    private void buildMenu(Matcher matcher)
    {
        System.out.println("you can select a building or unit");

        System.out.println("list of buildings: ");

       CityController cityController = gameViewController.getCityController();

        System.out.println(cityController.showBuildings());
        System.out.println("list of units: ");
        System.out.println(cityController.showUnits());
        String state = "build";
        while (state.equals("build"))
        {
            String cmd = Menu.input();
            if (Menu.checkMatching(SelectCityRegex.back, cmd))
            {
                state = "NULL";
                System.out.println("you are taken to Game Menu");
            }
            else if (Menu.checkMatching(SelectCityRegex.buildBuilding, cmd))
            {

                System.out.println(cityController.buildBuilding());
            }
            else if (Menu.checkMatching(SelectCityRegex.buildUnit, cmd))

            {
                System.out.println(cityController.buildingUnit());

            }
            else if (Menu.checkMatching(SelectCityRegex.buildBuildingGold, cmd))
            {
                System.out.println(cityController.buildBuildingWithGold());
            }
            else if (Menu.checkMatching(SelectCityRegex.buildUnitGold, cmd))
            {

                System.out.println(cityController.buildingUnitWithGold());
            }
            else
            {

                System.out.println(gameViewController.invalidMessage());
            }
        }
    }
    public GameViewController getGameViewController()
    {
        return gameViewController;
    }
}
