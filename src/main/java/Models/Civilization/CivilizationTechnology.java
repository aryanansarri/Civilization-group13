package Models.Civilization;

import Models.Building.Technology;

import java.util.ArrayList;
import java.util.HashMap;

public class CivilizationTechnology {
    private ArrayList<Technology>  licencedTechnology;
    private ArrayList<Technology> haveTechnology;
    private ArrayList<Technology> notHaveTechnology;
    private Technology currentTeachTechnology;
    private int rem;

    public CivilizationTechnology() {
        licencedTechnology = new ArrayList<>();
        haveTechnology = new ArrayList<>();
        notHaveTechnology = Technology.getAllTechs();
        checkForReaching();
    }

    private boolean available(Technology technology) {
        ArrayList<Technology> preRequired = technology.getPrerequisiteTechs();
        for (Technology t : preRequired) {
            if (licencedTechnology.contains(t)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public void checkForReaching() {
        for (int i = notHaveTechnology.size() - 1; i >= 0; i--) {
            if (available(notHaveTechnology.get(i))) {
                haveTechnology.add(notHaveTechnology.get(i));
                notHaveTechnology.remove(i);
            }
        }
    }

    public void RequestToReachTechnology(Technology need, int cost) {

    }
}
