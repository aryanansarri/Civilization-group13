package com.example.hexagons;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
public class Map extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(1280, 720);
        Pane content = new Pane();
        content.setPrefSize(1500,1500);
        scrollPane.setContent(content);
        Scene scene = new Scene(scrollPane);
        stage.setTitle("Map");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}