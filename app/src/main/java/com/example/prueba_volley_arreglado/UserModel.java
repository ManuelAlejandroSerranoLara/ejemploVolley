package com.example.prueba_volley_arreglado;

public class UserModel {
    int userID;
    String username;
    String name;
    String surname;


    public UserModel(int userID, String username, String name, String surname) {
        this.userID = userID;
        this.username = username;
        this.name = name;
        this.surname = surname;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
