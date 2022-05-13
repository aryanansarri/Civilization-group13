package Controller.GameController;

import Models.Civilization.City;
import Models.Civilization.Citizen;
import Models.Coordinates;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class CityController {


    public String showCityInfo(City city) {
        return city.getName() + city.getHappiness() + city.getOwnership() + city.getHealth() ;
    }

    public String moveCitizen(Matcher matcher,City city) {
        int firstX = Integer.parseInt(matcher.group("x"));
        int firstY = Integer.parseInt(matcher.group("y"));
        int secondX = Integer.parseInt(matcher.group("xx"));
        int secondY = Integer.parseInt(matcher.group("yy"));
        Coordinates first = new Coordinates(firstX, firstY);
        Coordinates second = new Coordinates(secondX, secondY);
        if (!first.isValidCoordination(GameDatabase.getGameDatabase().getOriginalMap()))
            return "coordinates are invalid";
        if (!second.isValidCoordination(GameDatabase.getGameDatabase().getOriginalMap()))
            return "coordinates are invalid";
        if (!city.getTiles().contains(GameDatabase.getGameDatabase().getOriginalMap().getTerrain(firstX, firstY)))
            return "This Tile does not belong to you!";
        if (!city.getTiles().contains(GameDatabase.getGameDatabase().getOriginalMap().getTerrain(secondX, secondY)))
            return "This Tile does not belong to you!";

        for (int i = 0; i < city.getCitizens().size(); i++) {
            if (city.getCitizens().get(i).getonTile() == GameDatabase.getGameDatabase().getOriginalMap().getTerrain(firstX, firstY)) {
                city.getCitizens().set(i, new Citizen(city));
                return "Citizen Moved Successfully.";
            }
        }
        return "Citizen is not Working in origin!";
    }

    public String setCitizen(Matcher matcher,City city) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Coordinates coordination = new Coordinates(x, y);
        if (!coordination.isValidCoordination(GameDatabase.getGameDatabase().getOriginalMap()))
            return "Coordinates are invalid!";
        if (city == null)
            return "City Not Selected!";
        if (!city.getTiles().contains(GameDatabase.getGameDatabase().getOriginalMap().getTerrain(coordination.getX(), coordination.getY())))
            return "This Tile does not belong to you!";

        for (int i = 0; i < city.getCitizens().size(); i++) {
            if (city.getCitizens().get(i) == null) {
                city.getCitizens().set(i, new Citizen(city));
                return "Citizen set.";
            }
        }
        return "None citizen is free to set.";
    }

    public String deleteCity() {
        return "";
    }

    public String removeCitizen(Matcher matcher) {
        return "";
    }

    public String buyTile(Matcher matcher) {
        return "";
    }

    public String attack(Matcher matcher) {
        return "";
    }
}