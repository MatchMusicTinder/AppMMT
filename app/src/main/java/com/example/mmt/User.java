package com.example.mmt;

public class User {
    private String name;

    private String genero_mus;

    private String instru;

    public User() {

    }

    public User(String name, String genero_mus, String instru) {
        this.name = name;
        this.genero_mus = genero_mus;
        this.instru = instru;
    }

    public String getName() {
        return name;
    }

    public String getGenero_Mus() {
        return genero_mus;
    }

    public String getInstru() {
        return instru;
    }



}
