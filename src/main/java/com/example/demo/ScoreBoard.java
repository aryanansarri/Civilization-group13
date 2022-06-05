package com.example.demo;

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

public class ScoreBoard extends Application {
    private ArrayList<User> users;
    private User currentUser;

    @Override
    public void start(Stage stage) throws IOException {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(1280, 720);
        Pane content = new Pane();
        scrollPane.setContent(content);
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            Rectangle rectangle = new Rectangle(0, i * 100 + 10, 1280, 100);
            rectangle.setFill(Color.PURPLE);
            content.getChildren().add(rectangle);
            Rectangle rectangle2 = new Rectangle(0, i * 100, 1280, 10);
            rectangle2.setFill(Color.WHITE);
            content.getChildren().add(rectangle2);
        }
        Scene scene = new Scene(scrollPane);
        stage.setTitle("ScoreBoard");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void sortUsers() {
            for (int i = 0; i < this.users.size() - 1; i++)
                for (int j = i + 1; j < this.users.size(); j++)
                    if (this.users.get(j).getScore()<
                           this.users.get(i).getScore()) {
                        Collections.swap(this.users, i, j);
                    }
            for (int i = 0; i < this.users.size() - 1; i++)
                for (int j = i + 1; j < this.users.size(); j++)
                    if (this.users.get(j).getScore()==this.users.get(i).getScore())
                        if (this.users.get(j).getTimeOfLastWin() < this.users.get(i).getTimeOfLastWin()) {
                            Collections.swap(this.users, i, j);
                        }
            for (int i = 0; i < this.users.size() - 1; i++)
                for (int j = i + 1; j < this.users.size(); j++)
                    if (this.users.get(j).getScore()==this.users.get(i).getScore())
                        if (this.users.get(j).getTimeOfLastWin() == this.users.get(i).getTimeOfLastWin())
                            if (this.users.get(j).getNickname()
                                    .compareTo(this.users.get(i).getNickname()) < 0) {
                                Collections.swap(this.users, i, j);
                            }
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public static void main(String[] args) {
        launch();
    }
}