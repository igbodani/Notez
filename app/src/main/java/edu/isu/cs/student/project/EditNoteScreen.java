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

import java.io.IOException;

public class EditNoteScreen {

    public static Scene getEditNoteScreen(Stage stage, Note workingNote) throws Exception{
        TextField title = new TextField(workingNote.getTitle());
        title.setAlignment(Pos.TOP_CENTER);
        title.setMaxWidth(500);

        TextArea description = new TextArea(workingNote.getBody());
        description.setPrefHeight(300);


        ListView<Note> noteListView = new ListView<>();
        ObservableList<Note> myObservableList = FXCollections.observableList(Manager.getNotes());
        noteListView.setItems(myObservableList);
        noteListView.setVisible(true);



        DatePicker date = new DatePicker();

        Button save = new Button("Save");
        save.setAlignment(Pos.TOP_RIGHT);
        save.setOnAction(event -> {
            boolean check = true;
            if(title.getText().isEmpty() || description.getText().isEmpty()){
                check = false;
            }
            else{
                try {
                    Manager.modifyNote(workingNote, title.getText(), description.getText());

                }catch (IOException e){
                    e.printStackTrace();
                }
                if (check){
                    stage.setScene(MainScreen.getMainScreen(stage));}
            }
        });


        VBox box = new VBox();
        box.getChildren().addAll(title,description);
        box.setAlignment(Pos.TOP_CENTER);

        HBox buttom = new HBox();
        buttom.getChildren().addAll(save);
        buttom.setAlignment(Pos.BOTTOM_RIGHT);

        BorderPane pane = new BorderPane();
        pane.setCenter(box);
        pane.setLeft(noteListView);
        pane.setBottom(buttom);

        Scene view = new Scene(pane, 600, 400);
        return view;
    }
    }

