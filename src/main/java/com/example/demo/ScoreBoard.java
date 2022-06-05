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
        sortUsers();
        addUsers(content);
        scrollPane.setContent(content);
        Scene scene = new Scene(scrollPane);
        stage.setTitle("ScoreBoard");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void sortUsers() {
        for (int i = 0; i < this.users.size() - 1; i++)
            for (int j = i + 1; j < this.users.size(); j++)
                if (this.users.get(j).getScore() < this.users.get(i).getScore()) {
                    Collections.swap(this.users, i, j);
                }
        for (int i = 0; i < this.users.size() - 1; i++)
            for (int j = i + 1; j < this.users.size(); j++)
                if (this.users.get(j).getScore() == this.users.get(i).getScore())
                    if (this.users.get(j).getTimeOfLastWin() < this.users.get(i).getTimeOfLastWin()) {
                        Collections.swap(this.users, i, j);
                    }
        for (int i = 0; i < this.users.size() - 1; i++)
            for (int j = i + 1; j < this.users.size(); j++)
                if (this.users.get(j).getScore() == this.users.get(i).getScore())
                    if (this.users.get(j).getTimeOfLastWin() == this.users.get(i).getTimeOfLastWin())
                        if (this.users.get(j).getNickname().compareTo(this.users.get(i).getNickname()) < 0) {
                            Collections.swap(this.users, i, j);
                        }
    }

    public void addUsers(Pane pane) {
        for (int i = 0; i <= users.size(); i++) {
            Rectangle rectangle = new Rectangle(0, i * 100 + 10, 1280, 100);
            Rectangle rectangle1 = new Rectangle(0, i * 100 + 10, 100, 100);
            rectangle1.setFill(users.get(i).getProfilePic());
            if (users.get(i) != currentUser) {
                rectangle.setFill(Color.PURPLE);
            } else {
                rectangle.setFill(Color.YELLOW);
            }
            pane.getChildren().addAll(rectangle, rectangle1);
            Rectangle rectangle2 = new Rectangle(0, i * 100, 1280, 10);
            rectangle2.setFill(Color.WHITE);
            pane.getChildren().add(rectangle2);
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