module com.example.hexagons {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hexagons to javafx.fxml;
    exports com.example.hexagons;
}