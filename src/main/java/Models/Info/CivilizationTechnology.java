package Models.Info;

import Models.Building.Technology;

import java.util.ArrayList;
import java.util.HashMap;

public class CivilizationTechnology {
    private ArrayList<Technology>  passedTechnology;
    private ArrayList<Technology> notPassedTechnology;
    private HashMap<Technology, Integer> technologyMap;

    private Technology currentTeachTechnology;
    private int remind;

    public CivilizationTechnology() {
        passedTechnology = new ArrayList<>();
        notPassedTechnology = Technology.getAllTechs();
        technologyMap = new HashMap<>();
        currentTeachTechnology = null;
        remind = 0;
        checkForReaching();
    }

    private boolean available(Technology technology) {
        ArrayList<Technology> preRequired = technology.getPrerequisiteTechs();
        for (Technology t : preRequired) {
            if (passedTechnology.contains(t)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public void checkForReaching() {
        for (int i = notPassedTechnology.size() - 1; i >= 0; i--) {
            Technology now = notPassedTechnology.get(i);
            if (available(now)) {
                technologyMap.put(now, now.getCost());
                notPassedTechnology.remove(i);
            }
        }
    }

    public void RequestToReachTechnology(Technology need, int remind) {
        if (this.currentTeachTechnology != null) {
            technologyMap.put(this.currentTeachTechnology, remind);
        }
        if (technologyMap.containsKey(need)) {
            technologyMap.remove(need);
        }
        this.currentTeachTechnology = need;
        this.remind = remind;
        currentTeachTechnologyProgress();
    }

    public void currentTeachTechnologyProgress() {
//        to do
    }
}
