package edu.isu.cs.student.project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LoginScreen {

    public static Scene getLoginScreen(Stage primaryStage) throws Exception {

        Label userName = new Label("Email");
        Label passWord = new Label("Password");

        TextField name = new TextField();
        PasswordField pass = new PasswordField();

        Button login = new Button("Login");
        login.setAlignment(Pos.BOTTOM_RIGHT);
        login.setOnAction(event -> {
            boolean check = true;
            if(name.getText().isEmpty() || pass.getText().isEmpty()){
                check = false;

            }
            else {
                Manager.login(name.getText(), pass.getText());
            }

            if(check){

                primaryStage.setScene(MainScreen.getMainScreen(primaryStage));
            }
        });
        Button back = new Button("Back");
        back.setAlignment(Pos.BOTTOM_LEFT);


        GridPane grid = new GridPane();
        grid.add(userName,0,0);
        grid.add(passWord, 0 , 1);
        grid.add(name, 1, 0);
        grid.add(pass, 1, 1);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);

        HBox loginBox = new HBox();
        loginBox.getChildren().addAll(login);
        loginBox.setAlignment(Pos.CENTER_RIGHT);

        HBox backBox = new HBox();
        backBox.getChildren().addAll(back);
        backBox.setAlignment(Pos.CENTER_LEFT);


        HBox box = new HBox( backBox, loginBox);
        box.setSpacing(30);
        box.setAlignment(Pos.BOTTOM_CENTER);


        BorderPane pane = new BorderPane();
        pane.setCenter(grid);
        pane.setBottom(box);

        Scene scene = new Scene(pane, 500, 400);
        return scene;

    }
}
