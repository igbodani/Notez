package edu.isu.cs.student.project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateNoteScreen {

    public Scene getCreateNoteScreen(Stage stage){
        TextField title = new TextField();
        title.setAlignment(Pos.TOP_CENTER);
        title.setMaxWidth(500);
        TextField bodyNote = new TextField();
        bodyNote.setAlignment(Pos.BOTTOM_CENTER);
        bodyNote.setPrefHeight(300);
        bodyNote.setMaxWidth(500);


        DatePicker date = new DatePicker();

        Button save = new Button("Save");
        save.setAlignment(Pos.TOP_RIGHT);
        save.setOnAction(event -> {
            boolean check = true;
            if(title.getText().isEmpty() || bodyNote.getText().isEmpty()){
                check = false;
            }
            else{
                Manager.addNote(title.getText(),bodyNote.getText(), date);
                //stage.setScene(App.start(primaryStage));
            }
        });

        ListView<Note> noteListView = new ListView<>();
        noteListView.getItems().addAll(Manager.getNotes());

        VBox box = new VBox();
        box.getChildren().addAll(title,bodyNote);
        box.setAlignment(Pos.TOP_CENTER);

        BorderPane pane = new BorderPane();
        pane.setCenter(box);
        pane.setLeft(noteListView);
        pane.setRight(save);



        Scene view = new Scene(pane, 600, 400);
        return view;
    }
    }

