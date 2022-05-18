package ControllerTest;

import Controller.GameController.MapController;
import Models.Civilizations.Civilization;
import Models.OriginalMap;
import org.junit.Test;

public class MapControllerTest {
    Civilization civilization = new Civilization("hossein");
    private OriginalMap originalMap = new OriginalMap(civilization);
    private MapController mapController = new MapController(OriginalMap.getTiles(), originalMap.getAllTileVisitingKinds());

    @Test
    public void runTest() {
        mapController.showMap(0, 0);
    }
}
