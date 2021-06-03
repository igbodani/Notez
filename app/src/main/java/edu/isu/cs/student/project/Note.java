package edu.isu.cs.student.project;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

public class Note {
    @NonNull
    @Getter
    @Setter
    private String title;
    @NonNull
    @Getter
    @Setter
    private String body;
    @NonNull
    @Getter
    @Setter
    private Date date;

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }

    @Override
    public String toString() {
        return title;
    }

}
