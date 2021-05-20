package edu.isu.cs.student.project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RegisterOrLoginScreen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button login = new Button("Login");
        login.setAlignment(Pos.CENTER_RIGHT);
        Button register = new Button("Register");
        register.setAlignment(Pos.CENTER_LEFT);

        HBox box = new HBox();
        box.getChildren().addAll(register, login);
        box.setSpacing(20);
        box.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setCenter(box);

        Scene scene = new Scene(pane, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
