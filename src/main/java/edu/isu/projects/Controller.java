package edu.isu.projects;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import lombok.SneakyThrows;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextArea textArea;

    @FXML
    TextField searchBar;

    @FXML
    MenuBar menuBar;

    @FXML
    ListView<String> listView;

    @FXML
    ColorPicker colorPicker;

    @FXML
    Spinner<Integer> spinner;

    @FXML
    ComboBox<String> comboBox;

    @FXML
    MenuItem save, open, delete;

    FileChooser fileChooser;

    File openFile, noteFiles;

    File[] fileList;

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {



        noteFiles = new File("C:/Users/igbod/Desktop/Projects/Notez/NoteFiles");
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        updateListView();


        //Select Font size using spinner
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 96);
        valueFactory.setValue(24);
        textArea.setFont(Font.font(comboBox.getValue(), valueFactory.getValue()));
        spinner.setValueFactory(valueFactory);

        //Adding a listener to auto change text-font size
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> textArea.setFont(Font.font(textArea.getFont().getFamily(), newValue)));

        //Select Font Family using ComboBox
        List<String> fontFamilies = Font.getFamilies();
        comboBox.getItems().addAll(fontFamilies);
        comboBox.getSelectionModel().selectFirst();
        textArea.setFont(Font.font(comboBox.getValue(), spinner.getValue()));

        //Adding a listener to auto change text-font family
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> textArea.setFont(Font.font(newValue, textArea.getFont().getSize())));


       /*
        //Change color using ColorPicker
        colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            //  String style = "-fx-text-fill:" + newValue+";";
            // textArea.setStyle(style);
        });
        */

                /*
                  Search function - Searching using textField text
                 */
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            //Check if textField is empty
            if (newValue == null || newValue.length() == 0) {
                //if empty updateListview
                updateListView();
            } else {
                listView.getItems().clear();
                for (File search : fileList) {
                    if (search.getName().contains(newValue)) {
                        listView.getItems().addAll(search.getName());
                    } else {
                        listView.getItems().removeAll(search.getName());
                    }
                }
            }
        });


        /*
         Delete, save and open Menu items events
         */

        //Open files
        open.setOnAction(event -> {

            // If item on listview is selected open selected item
            if (listView.getSelectionModel().getSelectedItem() != null) {
                System.out.println(searchBar.getText());
                String fileName = listView.getSelectionModel().getSelectedItem();
                try {
                    //Reading selected item file to textArea
                    readFromListView(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Clearing selected item
                listView.getSelectionModel().clearSelection();

                //Open File Explorer and select file
            } else {
                fileChooser.setInitialDirectory(noteFiles);
                File file = fileChooser.showOpenDialog(null);
                try {
                    if (file != null) {
                        /*
                        Saving file location when opened
                        tempFile is used for re-saving already existing files
                         */
                        openFile = file;
                        //Reading file into textArea
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String text = reader.readLine();
                        textArea.setText(text);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            updateListView();
        });

        //Delete files
        delete.setOnAction(event -> {
            if (!listView.getSelectionModel().getSelectedItem().isEmpty()) {
                String filename = listView.getSelectionModel().getSelectedItem();
                File removeFile = new File(noteFiles.getAbsolutePath() + "//" + filename);
                boolean deleted = removeFile.delete();
                System.out.println(deleted);
                if (deleted) {
                    listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
                    textArea.setText("");
                }
            }
            listView.getSelectionModel().clearSelection();
            updateListView();
        });

        //Save files
        save.setOnAction(event -> {
            if (!noteFiles.exists()) {
                noteFiles.mkdirs();
            }

            if (openFile != null) {
                fileChooser.setInitialDirectory(openFile.getParentFile());
                fileChooser.setInitialFileName(openFile.getName());
                File file = fileChooser.showSaveDialog(null);
                if (file != null) {
                    saveTextToFile(textArea.getText(), file);
                    textArea.setText("");
                }
            } else {
                fileChooser.setInitialDirectory(noteFiles);
                File file = fileChooser.showSaveDialog(null);
                if (file != null) {
                    saveTextToFile(textArea.getText(), file);
                    textArea.setText("");
                }
            }
            updateListView();
        });
    }

    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateListView() {
        fileList = noteFiles.listFiles();
        if (fileList != null) {
            ArrayList<String> arrayList = new ArrayList<>();
            for (File files : fileList) {
                arrayList.add(files.getName());
            }
            for (String name : arrayList) {
                if (!listView.getItems().contains(name)) {
                    listView.getItems().addAll(name);
                }
            }
        }
    }

    private void readFromListView(String fileName) throws IOException {
        File selectedFile = new File(noteFiles.getAbsolutePath() + "//" + fileName);
        BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
        String text = reader.readLine();
        textArea.setText(text);
        reader.close();
    }
}
