package com.example.padelscoresandroidclient.Models;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int[] getPoints() {
        return points;
    }

    public void setPoints(int[] points) {
        this.points = points;
    }

    private List<User> users;
    private int[] points;

    public Game(){
        users = new ArrayList<>();
        points = new int[2];
    }
}
