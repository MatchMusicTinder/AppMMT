package com.example.mmt;

public class User {
    private String name;

    public User() {
        // Constructor vacío requerido para Firestore
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
