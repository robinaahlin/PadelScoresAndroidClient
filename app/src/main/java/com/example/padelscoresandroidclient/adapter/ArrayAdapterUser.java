package com.example.padelscoresandroidclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.padelscoresandroidclient.Models.User;
import com.example.padelscoresandroidclient.R;

import java.util.List;
import java.util.Locale;

public class ArrayAdapterUser extends ArrayAdapter<User> {

    private Context context;
    private List<User> itemList;

    public ArrayAdapterUser(@NonNull Context context, int resource, List<User> itemList) {
        super(context, resource, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;
        if(view == null)
            view = inflater.inflate(R.layout.item_user, parent, false);

        User item = itemList.get(position);

        TextView tv_username, tv_userID;
        RelativeLayout rl = view.findViewById(R.id.list_view_user_item);

        tv_username = view.findViewById(R.id.item_username);
        tv_userID = view.findViewById(R.id.item_user_id);

        String username = item.getUsername();
        int userID = item.getUserID();

        tv_username.setText(username);
        //tv_userID.setText(Integer.toString(userID));
        tv_userID.setText(String.format(Locale.getDefault(), "%d", userID));

        return view;
    }
}
