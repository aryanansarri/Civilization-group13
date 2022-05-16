package Models.Units;

import Models.Block.TerrainFeature;
import Models.Block.Tile;
import Models.Improvment.Improvement;
import Models.Civilization.Civilization;

public class Worker extends Unit {
    private Improvement improvement;
    private int neededturns;

    public Worker(Tile tile, Civilization civilization) {
        super(UnitType.WORKER, tile, civilization);
        this.improvement = null;
    }

    public void makeImprovement(Improvement improvement) {
        if (!(getCivilization().getCivilizationTechnology().getPassedTechnology().contains(improvement.getNeededTech()) || improvement.getNeededTech() == null)) {
            System.out.println("The necessary technology for making this improvement doesn't exist");
        }
        if (improvement != null) {
            System.out.println("you could make only one improvement in a time, have patience friend!");
        }
        this.improvement = improvement;
        setWorkDone(true);
    }

    private void removeRoad() {
        getTile().getTerraintype().setHasroad(false);
        setWorkDone(true);
    }


    private void removeMarsh() {
        getTile().getTerrainFeatures().remove(TerrainFeature.Marsh);
        setWorkDone(true);
    }


    private void removeJungle() {
        getTile().getTerraintype().getPossibleFeatures().remove(TerrainFeature.Jungle);
        setWorkDone(true);
    }

    private void removeForest() {
        getTile().getTerraintype().getPossibleFeatures().remove(TerrainFeature.Forest);
        setWorkDone(true);
    }

    public Improvement getImprovement() {
        return improvement;
    }

    private void repair() {
        /////////////////toDo;
        setWorkDone(true);
    }

    public void setImprovement(Improvement improvement) {
        this.improvement = improvement;
    }

    public int getNeededturns() {
        return neededturns;
    }

    public void setNeededturns(int neededturns) {
        this.neededturns = neededturns;
    }

    public String getWorkingDetail() {
        if (improvement == null) return "no improvement is being made";
        else return improvement + " remaining turns :" + neededturns;
    }
    private void addImprovement() {
        if (improvement == Improvement.Road)
            getTile().getTerraintype().setHasroad(true);
        else if (improvement == Improvement.REMOVE_FOREST)
            removeForest();
        else if (improvement == Improvement.REPAIR)
            repair();
        else if (improvement == Improvement.REMOVE_ROUTE)
            removeRoad();
        else if (improvement == Improvement.REMOVE_MARSH)
            removeMarsh();
        else if (improvement == Improvement.REMOVE_JUNGLE)
            removeJungle();
        else
            getTile().setImprovement(this.improvement);
    }

    public void relieveFromJob() {
        getPath().clear();
        setSleep(false);
        setWorkDone(true);
        improvement = null;
    }

    public void nextTurn() {
        if (improvement != null) {
            neededturns--;
            if (neededturns <= 0) {
                addImprovement();
                improvement = null;
                neededturns = 0;
            }
        }
    }
}