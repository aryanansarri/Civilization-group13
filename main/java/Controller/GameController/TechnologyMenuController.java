package Controller.GameController;

import Models.Building.Technology;
import Models.Info.CivilizationTechnology;

import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;

public class TechnologyMenuController {
    private String temp;

    public String  TechTree(){
        temp = GameDatabase.getCurrentCivilization().getCivilizationTechnology().techTree();
        return temp;
    }
    public Technology findTech(String type){
        temp = type.toLowerCase();
        for(Technology tech : Technology.getAllTechs()){
            if(tech.getType().toLowerCase().equals(temp)) return tech;
        }
        return null;
    }
    public String pickTechToResearch(Matcher matcher){
        CivilizationTechnology civilizationTechnology = GameDatabase.getCurrentCivilization().getCivilizationTechnology();
        Technology tech = findTech(matcher.group("technology"));
        if(tech== null) return "wrong tech name";
        if(civilizationTechnology.getPassedTechnology().contains(tech)) return "tech was researched";
        if(civilizationTechnology.getNotPassedTechnology().contains(tech)) return "error";
        civilizationTechnology.RequestToReachTechnology(tech,civilizationTechnology.getAvailableTech().get(tech));
        return "tech set as researching";
    }
    public String showTechInfo() {
        StringBuilder tech = new StringBuilder();
        tech.append("RESEARCHED TECHS\n");
        for (Technology technologyResearched : GameDatabase.getCurrentCivilization().getCivilizationTechnology().getPassedTechnology()) {
            tech.append("Technology: ").append(technologyResearched.getType()).append("\n");
        }
        tech.append("\n");
        tech.append("AVAILABLE TECHS\n");
        for (Map.Entry<Technology, Integer> availableTech : GameDatabase.getCurrentCivilization().getCivilizationTechnology().getAvailableTech().entrySet()) {
            tech.append("TECHNOLOGY: ").append(availableTech.getKey().getType()).append("    ");
            tech.append("-NEEDED TECHS:");
            for (Technology technology : availableTech.getKey().getPrerequisiteTechs()) {
                tech.append(" ").append(technology.getType());
            }
            tech.append("ENDS UP TO:");
            for (Technology technology : availableTech.getKey().getTechHasUnlocked()) {
                tech.append(" ").append(technology.getType());
            }
            tech.append("    ");
            tech.append("UNLOCKS:");
            for (Object unlocks : availableTech.getKey().unlockingTechsForUnits()) {
                tech.append(" ").append(unlocks.toString().toLowerCase());
            }
            for (Object unlocks : availableTech.getKey().unlockingTechsForResources()) {
                tech.append(" ").append(unlocks.toString().toLowerCase());
            }
            for (Object unlocks : availableTech.getKey().unlockingTechsForImprovements()) {
                tech.append(" ").append(unlocks.toString().toLowerCase());
            }
            tech.append("    COST: ").append(availableTech.getValue()).append("    ");
            tech.append("\n");
        }
        return String.valueOf(tech);
    }

}
