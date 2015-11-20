//http://www.software-architect.net/articles/using-strong-encryption-in-java/introduction.html
//http://www.software-architect.net/articles/using-strong-encryption-in-java/using-stronger-security-algorithms.html

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thanhtd.model;

/**
 *
 * @author Thanh Tran
 */
public class User {
    private String Username;
    private String Password;
    private String Url;
    private String Note;

    public User() {
    }

    public User(String Username, String Password, String Url) {
        this.Username = Username;
        this.Password = Password;
        this.Url = Url;
        this.Note = "";
    }

    public User(String Username, String Password, String Url, String Note) {
        this.Username = Username;
        this.Password = Password;
        this.Url = Url;
        this.Note = Note;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    @Override
    public String toString() {
        return Username + " - " + Url;
    }
}
