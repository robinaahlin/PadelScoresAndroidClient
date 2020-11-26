package com.example.padelscoresandroidclient.Models;

public class User {

    private int userID;
    private String username;
    private String password;
    private int points;
    private int games;

    public User(int userID, String username, String password, int points, int games) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.games = 0;
        this.points = 0;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }
}