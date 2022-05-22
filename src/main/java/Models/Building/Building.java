package Models.Building;

import Models.Block.TerrainType;
import Models.Civilizations.City;
import Models.Resources.Resource;

public class Building {
    private final BuildingType type;

    public Building(BuildingType type) {
        this.type = type;
    }

    public BuildingType getType() {
        return type;
    }

    public static boolean isCityCompatibleWithBuildingType(City city, BuildingType type) {
        if (!((type.getPrerequisiteTechnology() == null) ||
                city.getOwnership().getCivilizationTechnology().getPassedTechnology().contains(type.getPrerequisiteTechnology())))
            return false;
        else if (type == BuildingType.ARMORY) {
            for (int i = 0; i < city.getTiles().size(); i++)
                if (city.getBuildings().contains(BuildingType.BARRACKS)) return true;
        } else if (type == BuildingType.CIRCUS) {
            for (int i = 0; i < city.getTiles().size(); i++)
                if (city.getTiles().get(i).getResources().contains(Resource.Ivory)) return true;
        } else if (type == BuildingType.STABLE) {
            for (int i = 0; i < city.getTiles().size(); i++)
                if (city.getTiles().get(i).getResources().contains(Resource.Horse)) return true;
        } else if (type == BuildingType.TEMPLE) {
            if (city.getBuildings().contains(BuildingType.MONUMENT)) return true;
        } else if (type == BuildingType.CASTLE) {
            if (city.getBuildings().contains(BuildingType.WALLS)) return true;
        } else if (type == BuildingType.FORGE) {
            for (int i = 0; i < city.getTiles().size(); i++)
                if (city.getTiles().get(i).getResources().contains(Resource.Horse)) return true;
        } else if (type == BuildingType.UNIVERSITY) {
            if (city.getBuildings().contains(BuildingType.LIBRARY)) return true;
        } else if (type == BuildingType.BANK) {
            if (city.getBuildings().contains(BuildingType.MARKET)) return true;
        } else if (type == BuildingType.MILITARY_ACADEMY) {
            if (city.getBuildings().contains(BuildingType.BARRACKS)) return true;
        } else if (type == BuildingType.MUSEUM) {
            if (city.getBuildings().contains(BuildingType.OPERA_HOUSE)) return true;
        } else if (type == BuildingType.OPERA_HOUSE) {
            if (city.getBuildings().contains(BuildingType.TEMPLE) && city.getBuildings().contains(BuildingType.BURIAL_TOMB))
                return true;
        } else if (type == BuildingType.PUBLIC_SCHOOL) {
            if (city.getBuildings().contains(BuildingType.UNIVERSITY)) return true;
        } else if (type == BuildingType.SATRAPS_COURT) {
            if (city.getBuildings().contains(BuildingType.MARKET)) return true;
        } else if (type == BuildingType.THEATER) {
            if (city.getBuildings().contains(BuildingType.COLOSSEUM)) return true;
        } else if (type == BuildingType.ARSENAL) {
            if (city.getBuildings().contains(BuildingType.MILITARY_ACADEMY)) return true;
        } else if (type == BuildingType.BROADCAST_TOWER) {
            if (city.getBuildings().contains(BuildingType.MUSEUM)) return true;
        } else if (type == BuildingType.FACTORY) {
            for (int i = 0; i < city.getTiles().size(); i++)
                if (city.getTiles().get(i).getResources().contains(Resource.Iron)) return true;
        } else if (type == BuildingType.MILITARY_BASE) {
            if (city.getBuildings().contains(BuildingType.CASTLE)) return true;
        } else if (type == BuildingType.STOCK_EXCHANGE) {
            if (city.getBuildings().contains(BuildingType.BANK) || city.getBuildings().contains(BuildingType.SATRAPS_COURT))
                return true;
        } else {
            return true;
        }
        return false;
    }
}