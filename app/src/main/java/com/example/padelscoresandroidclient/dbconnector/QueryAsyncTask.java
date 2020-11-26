package com.example.padelscoresandroidclient.dbconnector;

import android.os.AsyncTask;

import com.example.padelscoresandroidclient.Models.User;
import com.example.padelscoresandroidclient.dbconnector.SocketHandler;
import com.example.padelscoresandroidclient.queryinterface.OnTaskCompleted;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class QueryAsyncTask extends AsyncTask<Void, Void, Void> {

    private PrintWriter write;
    private BufferedReader read;

    private List<User> users;
    private String json = null;

    private String query;

    private OnTaskCompleted listener;

    public QueryAsyncTask(OnTaskCompleted listener, String query){
        this.listener = listener;
        this.query = query;
    }

    public QueryAsyncTask(String query){
        this.query = query;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        Socket socket = SocketHandler.getSocket();

        try {
            write = new PrintWriter(socket.getOutputStream(), true);
            read = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send query to server side
            write.println(query);

            // Get response from server in format JSON-string
            json = read.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Void param) {
        if(json != null){
            if(query.equals("GET ALL USERS")){
                List<User> users = jsonToUserList(json);
                listener.onTaskCompleted(users);
            } else if(query.equals("ADD POINTS")){

            }
        }
    }

    /**
     * Help-function to convert JSON-string (ArrayList) to ArrayList of user objects.
     * @param json
     * @return
     */
    private List<User> jsonToUserList(String json){
        JSONArray jsonArray;
        List<User> users = new ArrayList<>();
        try {
            jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                String s = jsonArray.getString(i);

                JSONObject obj = new JSONObject(s);
                String userID = obj.getString("userID");
                String username = obj.getString("username");
                String password = obj.getString("password");
                String points = obj.getString("points");
                String games = obj.getString("games");

                users.add(new User(Integer.parseInt(userID),username,password,
                        Integer.parseInt(points),Integer.parseInt(games)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }
}
