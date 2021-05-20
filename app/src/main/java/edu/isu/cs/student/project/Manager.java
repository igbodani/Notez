package edu.isu.cs.student.project;



import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.Date;

@Log
public class Manager {

    protected static ArrayList<Note> notes = new ArrayList<>();


    protected static ArrayList<Note> getNotes() {
        return notes;
    }


    public static void addNote(String title, String body) {
        Note note = new Note(title, body);
        notes.add(note);
    }

    public static void removeNote(Note note) {
        notes.remove(note);
    }

    public static void modifyNote(Note workingNote, String title, String body) {
        workingNote.setTitle(title);
        workingNote.setBody(body);
       // workingNote.setDate(date);
    }

    public static int count() {
        return notes.size();
    }

    public static ArrayList<Note> search(String title) {
        ArrayList<Note> searchList = new ArrayList<>();
        for (Note element : notes) {
            if (element.getTitle().equalsIgnoreCase(title)) {
                searchList.add(element);
            }
        }

        return searchList;
    }

    public static void main(String[] args) {


        System.out.println(Manager.search("death"));
    }


}