module com.civilization.guicivilization {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.civilization.guicivilization to javafx.fxml;
    exports com.civilization.guicivilization;
    exports com.civilization.guicivilization.Controller;
    opens com.civilization.guicivilization.Controller to javafx.fxml;
}