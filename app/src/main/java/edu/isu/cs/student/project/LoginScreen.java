package edu.isu.cs.student.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginScreen extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Label userName = new Label("Username");
        Label passWord = new Label("Password");
        TextField name = new TextField();
        PasswordField pass = new PasswordField();
        GridPane grid = new GridPane();
        grid.add(userName,0,1 );
        grid.add(passWord, 0 , 2);
        grid.add(name, 1, 1);
        grid.add(pass, 1, 2);


        BorderPane pane = new BorderPane();
        pane.setCenter(grid);

        Scene scene = new Scene(pane, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
