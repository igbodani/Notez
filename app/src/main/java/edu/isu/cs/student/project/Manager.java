package edu.isu.cs.student.project;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.java.Log;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

@Log
public class Manager {

    @Getter
    @NonNull
    private static ArrayList<Note> notes = new ArrayList<>();
    @Getter
    @NonNull
    private static ArrayList<User> users = new ArrayList<>();
    private static User loggedUser;

    public static boolean login(String email, String passWord) {
        loadUser();
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                if (u.getPassWord().equals(passWord)) {
                    loggedUser = u;

                    File tempFile = new File("./UserFiles");
                    boolean exists = tempFile.exists();
                    if (!exists) {
                        tempFile.mkdir();
                    }

                    //load their user data
                    loadUserData();
                }

            }
            return true;

        }

        return false;
    }


    public static boolean addUser(String firstName, String lastName, String passWord, String email, String numbers) throws IOException {
        for (User u : users) {
            if (u.getEmail().equals(email)) ;
            return false;
        }

        User newUser = new User(firstName, lastName, passWord, email, numbers);
        users.add(newUser);
        saveUser();
        return true;
    }


    public static void saveUser() throws IOException {
        Gson gson = new Gson();

        String json = gson.toJson(users);
        try {
            /*
            FileWriter Filewriter = new FileWriter("./Users.json");
            BufferedWriter writer = new BufferedWriter(Filewriter);
            writer.write(json);

             */
            Files.writeString(Path.of("./Users.json"), json);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadUser() {
        Gson gson = new Gson();
        File userFile = new File("./Users.json");
        boolean exist = userFile.exists();

        if (exist) {
            try {
                Type usersType = new TypeToken<ArrayList<User>>() {
                }.getType();
                //Type user = new Type.getType();
                users = gson.fromJson(Files.readString(Path.of("./Users.json")), usersType);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void saveUserData() {
        Gson gson = new Gson();
        String json = gson.toJson(notes);

        try {
            FileWriter fileWriter = new FileWriter("./UserFiles/" + loggedUser.getEmail() + "json");
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadUserData() {
        Gson gson = new Gson();

        try {
            FileReader fileReader = new FileReader("./UserFiles/" + loggedUser.getEmail() + "json");
            BufferedReader reader = new BufferedReader(fileReader);
            Type noteType = new TypeToken<ArrayList<Note>>() {
            }.getType();
            notes = gson.fromJson(reader.readLine(), noteType);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void addNote(String title, String body) {
        Note note = new Note(title, body);
        notes.add(note);
        saveUserData();
    }

    public static void removeNote(Note note) {
        notes.remove(note);
        saveUserData();
    }

    public static void modifyNote(Note workingNote, String title, String body) throws IOException {
        workingNote.setTitle(title);
        workingNote.setBody(body);
        saveUserData();
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
}