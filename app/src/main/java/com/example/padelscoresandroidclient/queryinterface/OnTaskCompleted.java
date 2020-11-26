package com.example.padelscoresandroidclient.queryinterface;

import com.example.padelscoresandroidclient.Models.User;

import java.util.List;

public interface OnTaskCompleted {
    void onTaskCompleted(String str);
    void onTaskCompleted(List<User> userList);

    // If socket can't connect to server throw error.
    void serverConnectionError();
}
