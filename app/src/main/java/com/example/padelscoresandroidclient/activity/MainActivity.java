package com.example.padelscoresandroidclient.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.padelscoresandroidclient.Models.User;
import com.example.padelscoresandroidclient.R;
import com.example.padelscoresandroidclient.dbconnector.LoginAsyncTask;
import com.example.padelscoresandroidclient.queryinterface.OnTaskCompleted;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted {

    private String action;
    private String username;
    private int userID = 1;

    private EditText editTextUsername;
    private EditText editTextPassword;

    private ProgressDialog dialog;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        editTextUsername = findViewById(R.id.editText_username);
        editTextUsername.setHint("Username");
        editTextPassword = findViewById(R.id.editText_password);
        editTextPassword.setHint("Password");
        Button btnLogin = findViewById(R.id.button_login);
        Button btnNewUser = findViewById(R.id.button_new_user);
        btnLogin.setOnClickListener(this);
        btnNewUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.button_login){
            action = "LOGIN_USER";
            Toast toast = Toast.makeText(context, "Signing in. Please wait...", Toast.LENGTH_LONG);
            toast.show();
        } else if(v.getId() == R.id.button_new_user){
            action = "NEW_USER";
        }

        this.username = editTextUsername.getText().toString();
        editTextUsername.getText().clear();
        String password = editTextPassword.getText().toString();
        editTextPassword.getText().clear();

        LoginAsyncTask loginAsyncTask = new LoginAsyncTask(this, action, username, password);
        loginAsyncTask.execute();
    }

    @Override
    public void onTaskCompleted(String str) {
        if(str.equals("SUCCESS")) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.putExtra("USERNAME", username);
            intent.putExtra("USER_ID", userID);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(context, "Failed to sign in!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    public void onTaskCompleted(List<User> userList) {

    }

    /**
     * Activated when LoginAsyncTask encounters an SocketTimeoutException when trying to connect
     * to server side with socket. Show alert dialog to the user with information.
     */
    @Override
    public void serverConnectionError() {

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Server Connection Error");
        alertDialog.setMessage("Could not connect to server!");

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
