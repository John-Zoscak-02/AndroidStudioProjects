package com.example.color_estimate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Results_List extends AppCompatActivity {

    private Button mainMenu;
    private TextView avgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results__list);

        Intent intent = getIntent();
        int[] colors = intent.getIntArrayExtra(MainActivity.COLORS);
        double[] records = intent.getDoubleArrayExtra(MainActivity.RECORDS);

        createButton();
        avgView = findViewById(R.id.textView9);

        displayAverage(records);

        List<Result> results = new ArrayList<>();
        for (int i = 0; i < records.length; i++) {
            results.add(new Result(Color.valueOf(colors[i]), records[i]));
        }

        ListView listView = findViewById(R.id.listView);
        //Log.i("listview height", listView.getLayoutParams().height + "");
        //listView.setLayoutParams( new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,
                                                            //listView.getLayoutParams().height - 1 ) );
        User_Array_Adapter user_array_adapter = new User_Array_Adapter(this, results);
        listView.setAdapter(user_array_adapter);

    }

    public void displayAverage( double[] records ) {
        if ( records.length != 0 ) {
            double avg = 0;
            for ( double d : records ) {
                avg += d;
            }
            avg /= records.length;
            avgView.setText( String.format( "Average: %3f", avg ) );
        }
        else {
            avgView.setText( "There are no records" );
        }
    }

    public void createButton () {
        mainMenu = findViewById( R.id.button6 );
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
