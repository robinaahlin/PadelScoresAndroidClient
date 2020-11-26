package com.example.padelscoresandroidclient.dbconnector;

import android.os.AsyncTask;

import com.example.padelscoresandroidclient.queryinterface.OnTaskCompleted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class LoginAsyncTask extends AsyncTask<Void, Void, Void> {

    private String action, username, password, response = null;

    private OnTaskCompleted listener;
    private boolean socketConnectionError = false;

    public LoginAsyncTask(OnTaskCompleted listener, String action, String username, String password){
        this.listener=listener;
        this.action = action;
        this.username = username;
        this.password = password;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            String serverName = "192.168.1.78";
            int port = 3308;
            InetSocketAddress inetSocketAddress = new InetSocketAddress(serverName,port);
            Socket socket = new Socket();

            // If no response from server within 1000 ms an SocketTimeoutException occurs.
            socket.connect(inetSocketAddress,1000);
            SocketHandler.setSocket(socket);

            PrintWriter write = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println(read.readLine());
            write.println(action);
            write.println(username);
            write.println(password);
            response = read.readLine();

        } catch (SocketTimeoutException e) {
            socketConnectionError = true;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Void param) {
        if(!socketConnectionError) {
            if (response.contains("SUCCESS")) {
                listener.onTaskCompleted("SUCCESS");
            } else {
                listener.onTaskCompleted("FAILED");
            }
        } else {
            listener.serverConnectionError();
        }
    }
}
