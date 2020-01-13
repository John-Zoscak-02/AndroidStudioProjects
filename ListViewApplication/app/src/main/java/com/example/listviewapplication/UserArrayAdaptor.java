package com.example.listviewapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class UserArrayAdaptor extends ArrayAdapter<String> {
    public UserArrayAdaptor(@NonNull Context context, List<Player> players) {
        super(context,0);
    }

    public View getView(int position, View view, ViewGroup parent ) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.player, parent, false);
        }
        TextView textView = view.findViewById(R.id.textView2);
        textView.setText(getItem(position));
        return view;
    }
}
