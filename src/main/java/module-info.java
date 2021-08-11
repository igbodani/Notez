module Notez.main {
    requires static lombok;
    requires java.logging;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires com.google.gson;
    requires com.google.common;
    exports edu.isu.projects to com.google.gson;
    opens edu.isu.projects;

}