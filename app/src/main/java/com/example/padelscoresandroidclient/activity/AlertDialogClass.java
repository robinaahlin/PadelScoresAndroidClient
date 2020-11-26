package com.example.padelscoresandroidclient.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.padelscoresandroidclient.R;

public class AlertDialogClass extends Dialog implements android.view.View.OnClickListener {

    public AlertDialogClass(Activity a) {
        super(a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_dialog);
        TextView btnOK = findViewById(R.id.btnTextOk);
        btnOK.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
