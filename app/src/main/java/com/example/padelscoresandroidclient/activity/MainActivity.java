package com.example.padelscoresandroidclient.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.padelscoresandroidclient.R;
import com.example.padelscoresandroidclient.dbconnector.LoginAsyncTask;
import com.example.padelscoresandroidclient.queryinterface.OnTaskCompleted;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted {

    private String username;
    private String password;

    private EditText editTextUsername;
    private EditText editTextPassword;

    private Button login;

    private ProgressDialog dialog;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        editTextUsername = findViewById(R.id.editText_username);
        editTextPassword = findViewById(R.id.editText_password);
        login = findViewById(R.id.button_login);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.button_login){
            username = editTextUsername.getText().toString();
            editTextUsername.getText().clear();
            password = editTextPassword.getText().toString();
            editTextPassword.getText().clear();

            Toast toast = Toast.makeText(context, "Signing in. Please wait...", Toast.LENGTH_LONG);
            toast.show();

            LoginAsyncTask loginAsyncTask = new LoginAsyncTask(this, username, password);
            loginAsyncTask.execute();
        } else if(v.getId() == R.id.button_new_user){
            username = editTextUsername.getText().toString();
            editTextUsername.getText().clear();
            password = editTextPassword.getText().toString();
            editTextPassword.getText().clear();

            LoginAsyncTask loginAsyncTask = new LoginAsyncTask(this, username, password);
            loginAsyncTask.execute();
        }
    }

    @Override
    public void onTaskCompleted(String str) {
        if(str.equals("SUCCESS")) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(context, "Failed to sign in!", Toast.LENGTH_LONG);
            toast.show();
            //dialog.dismiss();
        }
    }
}
