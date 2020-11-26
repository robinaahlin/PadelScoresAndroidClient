package com.example.padelscoresandroidclient.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.padelscoresandroidclient.Models.User;
import com.example.padelscoresandroidclient.R;
import com.example.padelscoresandroidclient.adapter.ArrayAdapterUser;
import com.example.padelscoresandroidclient.dbconnector.QueryAsyncTask;
import com.example.padelscoresandroidclient.queryinterface.OnTaskCompleted;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted {

    private ListView listView;
    final Context context = this;
    private User activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String usernameActiveUser = getIntent().getStringExtra("USERNAME");

        Button buttonShowPoints = findViewById(R.id.button_show_users);
        Button buttonAddScore = findViewById(R.id.button_add_score);
        Button buttonTemp1 = findViewById(R.id.button_temp1);
        Button buttonTemp2 = findViewById(R.id.button_temp2);

        buttonShowPoints.setOnClickListener(this);
        buttonAddScore.setOnClickListener(this);
        buttonTemp1.setOnClickListener(this);
        buttonTemp2.setOnClickListener(this);

        listView = findViewById(R.id.listView_score);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = HomeActivity.this.getSupportFragmentManager().beginTransaction();
        if (v.getId() == R.id.button_show_users) {
            QueryAsyncTask queryAsyncTask = new QueryAsyncTask(this, "GET ALL USERS");
            queryAsyncTask.execute();

        } else if (v.getId() == R.id.button_add_score) {

            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.add_points_dialog,
                    (ViewGroup) v.getParent(), false);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setMessage("Add points");

            // set prompts.xml to alert dialog builder
            alertDialogBuilder.setView(promptsView);

            final EditText userInput = promptsView
                    .findViewById(R.id.editTextDialogUserInput);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // get user input and set it to result
                                    // edit text
                                    System.out.println("INPUT FROM USER: " + userInput.getText());
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

            // create alert dialog and show it
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        } else if (v.getId() == R.id.button_temp1) {
            // Add function
        } else if (v.getId() == R.id.button_temp2) {
            // Add function
        }
    }

    @Override
    public void onTaskCompleted(String str) {

    }

    @Override
    public void onTaskCompleted(List<User> json) {
        ArrayAdapterUser adapterUser;
        if (json != null) {
            listView = findViewById(R.id.listView_score);
            if(listView != null) {
                adapterUser = new ArrayAdapterUser(this, R.layout.item_user, json);
                listView.setAdapter(adapterUser);
            }
        }
    }

    @Override
    public void serverConnectionError() {

    }
}
