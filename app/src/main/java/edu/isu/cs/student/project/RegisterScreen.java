package edu.isu.cs.student.project;

import javafx.application.Application;
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

import java.io.IOException;

public class RegisterScreen {


    public static Scene getRegisterScreen(Stage primaryStage) throws Exception {
        Label firstNameLabel = new Label("First Name: ");
        Label lastNameLabel = new Label("Last Name: ");
        Label emailLabel = new Label("Email: ");
        Label passwordLabel = new Label("Password: ");
        Label numberLabel = new Label("Phone Number: ");
        Label userLabel = new Label("Username: ");

        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField email = new TextField();
        TextField username = new TextField();
        PasswordField passwordField = new PasswordField();
        TextField number = new TextField();


        Button register = new Button("Register");
        register.setOnAction(event -> {
            boolean check = true;
            if(firstName.getText().isEmpty() || lastName.getText().isEmpty() || email.getText().isBlank() || passwordField.getText().isBlank() || number.getText().isBlank()){
                check = false;
            }
            else{
                try {
                    Manager.addUser(firstName.getText(), lastName.getText(), email.getText(), passwordField.getText(), number.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(check){
                try {
                    primaryStage.setScene(LoginScreen.getLoginScreen(primaryStage));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        GridPane grid = new GridPane();

        grid.add(firstNameLabel,0,0);
        grid.add(lastNameLabel,0,1);
        grid.add(emailLabel, 0, 2);
      //  grid.add(userLabel,0,3);
        grid.add(passwordLabel,0,4);
        grid.add(numberLabel,0,5);
        grid.add(firstName,1,0);
        grid.add(lastName,1,1);
//        grid.add(username,1,2);
        grid.add(email,1,3);
        grid.add(passwordField,1,4);
        grid.add(number,1,5);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);


        HBox buttonContainer = new HBox(register);
        buttonContainer.setAlignment(Pos.BOTTOM_RIGHT);





        BorderPane pane = new BorderPane();
        pane.setCenter(grid);
        pane.setBottom(buttonContainer);


        Scene scene = new Scene(pane, 600, 500);
        return scene;

    }
}
