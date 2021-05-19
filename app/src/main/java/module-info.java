module Notez.app.main {
    requires javafx.controls;
 //   requires javafx.graphics;
    requires com.google.gson;
    requires com.google.common;
    requires lombok;
    exports edu.isu.cs.student.project;
    opens edu.isu.cs.student.project to com.google.gson;

}