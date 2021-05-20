package edu.isu.cs.student.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainScreen {
    public static Scene getMainScreen(Stage primaryStage){


        TextField search = new TextField();
        search.setAlignment(Pos.TOP_CENTER);
        search.setPrefWidth(500);
        search.setPrefHeight(50);




        ListView<Note> listView = new ListView<>();
        listView.setMaxWidth(400);
        listView.getItems().addAll(Manager.getNotes());
        listView.setVisible(true);

        Button edit = new Button("Edit");
        edit.setAlignment(Pos.BOTTOM_LEFT);
        edit.setOnAction(event -> {
            boolean check = true;
            if(listView.getSelectionModel().getSelectedItem() == null){
                check = false;
            }
            else{
            try {
                primaryStage.setScene(EditNoteScreen.getEditNoteScreen(primaryStage, listView.getSelectionModel().getSelectedItem()));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }});

        Button add = new Button("Add");
        add.setAlignment(Pos.BOTTOM_CENTER);
        add.setOnAction(event -> {
            boolean check = true;
            if(listView.getSelectionModel().getSelectedItem() == null) {
                try {
                    primaryStage.setScene(AddNoteScreen.getAddNoteScreen(primaryStage));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                check = false;

            }
        });

        Button remove = new Button("Delete");
        remove.setAlignment(Pos.BOTTOM_RIGHT);
        remove.setOnAction(event -> {
            boolean check = true;
            if(listView.getSelectionModel().getSelectedItem() == null){
                check = false;

            }
            else {
                try {
                    Manager.removeNote(listView.getSelectionModel().getSelectedItem());
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

        });

        HBox button = new HBox();
        button.getChildren().addAll(edit,remove, add);
        button.setPadding(new Insets(4,4,2,2));
        button.setSpacing(10);
        button.setAlignment(Pos.BOTTOM_CENTER);

        HBox text = new HBox();
        text.getChildren().addAll(search);
        text.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setTop(text);
        pane.setBottom(button);
        pane.setCenter(listView);



        primaryStage.setTitle("Notez");

        Scene scene = new Scene(pane, 600 , 500);

        return scene;
    }


}
