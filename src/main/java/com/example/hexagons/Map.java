package com.example.hexagons;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
public class Map extends Application {
    private final static double r = 80; // the inner radius from hexagon center to outer corner
    private final static double n = Math.sqrt(r * r * 0.75); // the inner radius from hexagon center to middle of the axis
    private final static double TILE_HEIGHT = 2 * r;
    private final static double TILE_WIDTH = 2 * n;
    private class Tile extends Polygon {
        Tile(double x, double y) {
            // creates the polygon using the corner coordinates
            getPoints().addAll(
                    x, y,
                    x, y + r,
                    x + n, y + r * 1.5,
                    x + TILE_WIDTH, y + r,
                    x + TILE_WIDTH, y,
                    x + n, y - r * 0.5
            );
            // set up the visuals and a click listener for the tile
            setFill(Color.ANTIQUEWHITE);
            setStrokeWidth(1);
            setStroke(Color.BLACK);
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(1280, 720);
        Pane content = new Pane();
        content.setPrefSize(1500,1000);
        scrollPane.setContent(content);
        addHexagons(content);
        Scene scene = new Scene(scrollPane);
        stage.setTitle("Map");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    private void addHexagons(Pane pane)
    {

          for(int j=0;j<6;j++)
              for(int i=0;i<6;i++)
              {
                  if(j%2==0)
                  {
                      Tile tile = new Tile(200+i*2*n, 200+r*1.5*j);
                      pane.getChildren().add(tile);
                  }
                  if(j%2==1)
                  {
                      Tile tile = new Tile(200+n+i*2*n, 200+r*1.5*j);
                      pane.getChildren().add(tile);
                  }
              }
//        Tile tile=new Tile(200,200);
//        pane.getChildren().add(tile);
//        Tile tile1=new Tile(200+2*n,200);
//        pane.getChildren().add(tile1);
    }

    public static void main(String[] args) {
        launch();
    }
}