package edu.isu.cs.student.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddNoteScreen {
    public static Scene getAddNoteScreen(Stage stage) {

        TextField title = new TextField();
        title.setAlignment(Pos.TOP_CENTER);
        title.setMaxWidth(500);
        TextArea description = new TextArea();
        description.setPrefHeight(300);

        ListView<Note> noteListView = new ListView<>();
        //ObservableList<Note> myObservableList = FXCollections.observableList(Manager.getNotes());
        noteListView.setVisible(false);


        DatePicker date = new DatePicker();

        Button save = new Button("Save");
        save.setAlignment(Pos.TOP_LEFT);
        save.setOnAction(event -> {
            boolean check = true;
            if (title.getText().isEmpty() || description.getText().isEmpty()) {
                check = false;
            } else {
                Manager.addNote(title.getText(), description.getText());
                // noteListView.getItems().addAll(Manager.getNotes());
            }
            if (check){
                stage.setScene(MainScreen.getMainScreen(stage));
            }

        });

        Button back = new Button("Back");
        back.setAlignment(Pos.TOP_RIGHT);
        back.setOnAction(event -> {
            stage.setScene(MainScreen.getMainScreen(stage));
        });


        VBox box = new VBox();
        box.getChildren().addAll(title, description);
        box.setAlignment(Pos.TOP_CENTER);

        HBox button = new HBox();
        button.getChildren().addAll(back, save);
        button.setAlignment(Pos.BOTTOM_CENTER);
        button.setSpacing(100);




        BorderPane pane = new BorderPane();
        pane.setCenter(box);
        pane.setBottom(button);
        stage.setTitle(" New Note ");


        Scene scene = new Scene(pane, 600, 400);

        return scene;

    }
}
