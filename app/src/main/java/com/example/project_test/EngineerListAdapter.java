package com.example.project_test;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.List;

public class EngineerListAdapter extends CursorAdapter {

    public EngineerListAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView text1 = view.findViewById(android.R.id.text1);
        TextView text2 = view.findViewById(android.R.id.text2);

        String engineerName = cursor.getString(cursor.getColumnIndexOrThrow("nom"));
        String engineerFormation = cursor.getString(cursor.getColumnIndexOrThrow("formations_diplomes"));

        text1.setText(engineerName);
        text2.setText(engineerFormation);
    }
}
