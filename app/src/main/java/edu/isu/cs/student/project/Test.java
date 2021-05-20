package edu.isu.cs.student.project;

import javafx.application.Application;
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

public class Test extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField search = new TextField();
        search.setPrefWidth(500);
        //search.setMaxWidth(400);
        search.setPrefHeight(50);
        //search.setMaxHeight(50);
        search.setAlignment(Pos.CENTER);



        ListView<Note> listView = new ListView<>();
        listView.getItems().addAll(Manager.getNotes());
        listView.setMaxWidth(400);
        listView.setVisible(true);

        Button edit = new Button("Edit");
        edit.setAlignment(Pos.BOTTOM_LEFT);
        edit.setOnAction(event -> {
            try {
                primaryStage.setScene(EditNoteScreen.getEditNoteScreen(primaryStage, listView.getSelectionModel().getSelectedItem()));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        Button add = new Button("Add");
        add.setAlignment(Pos.BOTTOM_CENTER);
        add.setOnAction(event -> {
            try {
                primaryStage.setScene(AddNoteScreen.getAddNoteScreen(primaryStage));

            }catch (Exception e){
                e.printStackTrace();
            }
        });

        Button remove = new Button("Delete");
        remove.setAlignment(Pos.BOTTOM_RIGHT);
        remove.setOnAction(event -> {
            if(listView.getSelectionModel().getSelectedItem() == null){

            }
            else {
                Manager.removeNote(listView.getSelectionModel().getSelectedItem());
            }

        });

        HBox button = new HBox();
        button.getChildren().addAll(edit,remove, add);
        button.setPadding(new Insets(4,4,2,2));
        button.setSpacing(3);
        button.setAlignment(Pos.BOTTOM_CENTER);

        HBox text = new HBox();
        text.getChildren().addAll(search);
        text.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setTop(text);
        pane.setBottom(button);
        pane.setCenter(listView);




        Scene scene = new Scene(pane, 600 , 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
