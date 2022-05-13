package Models.Info;

import Controller.GameController.GameDatabase;
import Models.Block.TechnologyAndImprovement.Technology;

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

    public ArrayList<Technology> getPassedTechnology() {
        return passedTechnology;
    }

    public void setPassedTechnology(ArrayList<Technology> passedTechnology) {
        this.passedTechnology = passedTechnology;
    }

    public ArrayList<Technology> getNotPassedTechnology() {
        return notPassedTechnology;
    }

    public void setNotPassedTechnology(ArrayList<Technology> notPassedTechnology) {
        this.notPassedTechnology = notPassedTechnology;
    }

    public HashMap<Technology, Integer> getTechnologyMap() {
        return technologyMap;
    }

    public void setTechnologyMap(HashMap<Technology, Integer> technologyMap) {
        this.technologyMap = technologyMap;
    }

    public Technology getCurrentTeachTechnology() {
        return currentTeachTechnology;
    }

    public void setCurrentTeachTechnology(Technology currentTeachTechnology) {
        this.currentTeachTechnology = currentTeachTechnology;
    }

    public int getRemind() {
        return remind;
    }

    public void setRemind(int remind) {
        this.remind = remind;
    }

    @Override
    public String toString() {
        CivilizationTechnology civilizationTechnology = GameDatabase.getGameDatabase().getCurrentCivilization().getCivilizationTechnology();
        String str;
        str = "Researched" + '\n' ;
        for (Technology technology : civilizationTechnology.getPassedTechnology()) {
            str += technology.getType() + '\n';
        }
        str += "in progress" + '\n';
        for (Technology technology : civilizationTechnology.getTechnologyMap().keySet()) {
            str += technology.getType() + '\n';
        }
        str += "Unavailable" + '\n';
        for (Technology technology : civilizationTechnology.getNotPassedTechnology()) {
            str += technology.getType() + ' ';
            str += "requirements: ";
            for (Technology req : technology.getPrerequisiteTechs()) {
                if (req == null) {
                    str += "null ";
                }
                else {
                    str += req.getType() + " ";
                }
            }
            str += '\n';
        }
        return str;
    }
}
