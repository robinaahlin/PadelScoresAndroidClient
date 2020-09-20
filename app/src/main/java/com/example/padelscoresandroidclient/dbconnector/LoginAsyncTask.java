package com.example.padelscoresandroidclient.dbconnector;

import android.os.AsyncTask;

import com.example.padelscoresandroidclient.queryinterface.OnTaskCompleted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


/**
 * When creating new socket localhost is used, only work when working with emulator.
 * Change this when testing on phone irl.
 */
public class LoginAsyncTask extends AsyncTask<Void, Void, Void> {

    private String username, password, response = null;

    private OnTaskCompleted listener;

    private Socket newSocket;

    public LoginAsyncTask(OnTaskCompleted listener, String username, String password){
        this.listener=listener;
        this.username = username;
        this.password = password;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            // Change the IP address from localhost when not using emulator
            InetAddress inetAddress = InetAddress.getLocalHost();
            newSocket = new Socket(inetAddress.getHostAddress(), 3308);
            SocketHandler.setSocket(newSocket);

            PrintWriter write = new PrintWriter(newSocket.getOutputStream(), true);
            BufferedReader read = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));

            System.out.println(read.readLine());
            write.println(username);
            write.println(password);
            response = read.readLine();
            System.out.println("THIS IS THE RESPONSE: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Void param) {
        if(response.contains("SUCCESS")) {
            listener.onTaskCompleted("SUCCESS");
        } else {
            listener.onTaskCompleted("FAILED");
        }
    }
}
