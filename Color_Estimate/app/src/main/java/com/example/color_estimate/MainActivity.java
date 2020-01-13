package com.example.color_estimate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView header;
    private TextView gameHeader;
    private TextView distance;
    private TextView avgCompare;
    private TextView redDiff;
    private TextView blueDiff;
    private TextView greenDiff;
    private EditText red;
    private EditText green;
    private EditText blue;
    private ImageView imageView;
    private ImageView frowny;
    private ImageView smiley;
    private Button begin;
    private Button results;
    private Button submit;
    private Button mainMenu;
    private Button next;
    public static final String RECORDS = "Results_Screen_Distances";
    public static final String COLORS = "Results_Screen_Colors";
    private List<Double> records;
    private List<Integer> colors;
    private int currentBackgroundColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header = findViewById( R.id.textView2 );
        gameHeader = findViewById( R.id.textView );
        distance = findViewById( R.id.textView3 );
        avgCompare = findViewById( R.id.textView4 );
        redDiff = findViewById( R.id.textView6 );
        greenDiff = findViewById( R.id.textView7 );
        blueDiff = findViewById( R.id.textView8 );
        red = findViewById( R.id.editText2 );
        green = findViewById( R.id.editText );
        blue = findViewById( R.id.editText3 );
        imageView = findViewById( R.id.imageView );
        frowny = findViewById( R.id.imageView5 );
        smiley = findViewById( R.id.imageView4 );
        begin = findViewById( R.id.button );
        results = findViewById( R.id.button2 );
        submit = findViewById( R.id.button3 );
        mainMenu = findViewById( R.id.button4 );
        next = findViewById( R.id.button5 );

        colors = new ArrayList<>();
        records = new ArrayList<>();

        createBeginButton();
        createResultsButton();
        createSubmitButton();
        createMainMenuButton();
        createNextButton();

        showAll( header, begin, results, imageView );
        hideAll( gameHeader, distance, avgCompare, red, green, blue, gameHeader, submit, mainMenu, next, redDiff, greenDiff, blueDiff, frowny, smiley);
    }

    public void showAll( View ... views ) {
        for ( View v : views ) {
            v.setVisibility( View.VISIBLE );
        }
    }
    public void hideAll( View ... views ) {
        for ( View v : views ) {
            v.setVisibility( View.GONE );
        }
    }

    public void createBeginButton() {
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAll( gameHeader, submit, distance, avgCompare, red, green, blue, avgCompare, distance );
                hideAll( header, results, begin );
                setUpQuestion();
            }
        });
    }

    public void createResultsButton() {
        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openResultsActivity();
            }
        });
    }

    public void openResultsActivity() {
        Intent intent = new Intent(this, Results_List.class);
        int[] arr1 = new int[colors.size()];
        for ( int i = 0; i < arr1.length; i++ ) {
            arr1[i] = colors.get( i );
}
    double[] arr2 = new double[records.size()];
        for ( int i = 0; i < arr1.length; i++ ) {
        arr2[i] = records.get( i );
        }
        intent.putExtra( COLORS, arr1 );
        intent.putExtra( RECORDS, arr2 );

        Log.i( "Intent Carry", Arrays.toString( intent.getIntArrayExtra( COLORS ) ) );
        Log.i( "Intent Carry", Arrays.toString( intent.getDoubleArrayExtra( RECORDS ) ) );

        startActivity( intent );
        }

    public void createSubmitButton() {
        submit = findViewById( R.id.button3 );
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( isDataFilled() ) {

                    double redDiffVal = Math.abs( Integer.parseInt( red.getText().toString() ) - Color.red( currentBackgroundColor ) );
                    double greenDiffVal = Math.abs( Integer.parseInt( green.getText().toString() ) - Color.green( currentBackgroundColor ) );
                    double blueDiffVal = Math.abs( Integer.parseInt( blue.getText().toString() ) - Color.blue( currentBackgroundColor ) );
                    double d = Math.sqrt( (redDiffVal * redDiffVal) + (greenDiffVal * greenDiffVal) + (blueDiffVal * blueDiffVal) );

                    colors.add( Color.argb( 255, Color.red( currentBackgroundColor ),
                            Color.green( currentBackgroundColor ), Color.blue ( currentBackgroundColor ) ) );
                    Log.i( "Amount entered", colors.toString() );
                    records.add( d );
                    Log.i( "Distances entered", records.toString() );
                    distance.setText( String.format( "Your guess was a distance of %4f off" , d ) );
                    redDiff.setText( String.valueOf( Color.red( currentBackgroundColor ) ) );
                    greenDiff.setText( String.valueOf( Color.green( currentBackgroundColor ) ) );
                    blueDiff.setText( String.valueOf( Color.blue( currentBackgroundColor ) ) );

                    double avg = 0;
                    for ( double i : records ) {
                        avg += i;
                    }
                    if ( ( avg / records.size() ) > d ) {
                        smiley.setVisibility( View.VISIBLE );
                        avgCompare.setText( "Better than average" );
                    }
                    else if ( colors.size() == 1 ) {
                        avgCompare.setText( "First attempt" );
                    }
                    else if ( ( avg / records.size() ) < d ) {
                        frowny.setVisibility( View.VISIBLE );
                        avgCompare.setText( "Worse than average" );
                    }
                    else {
                        avgCompare.setText( "Wow, you got the value of average" );
                    }

                    showAll( distance, avgCompare, mainMenu, next, redDiff, blueDiff, greenDiff);
                    hideAll( submit, gameHeader );
                }
                else {
                    toaster();
                }
            }
        });
    }

    public void toaster () {
        Toast.makeText( this, "Please enter a number from 0 - 255", Toast.LENGTH_SHORT ).show();
    }

    public boolean isDataFilled () {
        return ( !red.getText().toString().isEmpty() && !blue.getText().toString().isEmpty() && !green.getText().toString().isEmpty() ) &&
                ( ( Double.parseDouble( red.getText().toString() ) >= 0 ) && ( Double.parseDouble( red.getText().toString() ) < 256 ) ) &&
                        ( ( Double.parseDouble( green.getText().toString() ) >= 0 ) && ( Double.parseDouble( green.getText().toString() ) < 256 ) ) &&
                        ( ( Double.parseDouble( blue.getText().toString() ) >= 0 ) && ( Double.parseDouble( blue.getText().toString() ) < 256 ) );
    }

    public void createMainMenuButton() {
        mainMenu = findViewById( R.id.button4 );
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideAll( gameHeader, distance, avgCompare, red, green, blue, gameHeader, submit, mainMenu, next, redDiff, blueDiff, greenDiff , frowny, smiley);
                showAll( header, begin, results, imageView );
                imageView.setBackground( getDrawable( R.drawable.gradient ) );
            }
        });
    }

    public void createNextButton() {
        next = findViewById( R.id.button5 );
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpQuestion();
            }
        });
    }

    public void setUpQuestion() {
        red.setText( "" );
        green.setText( "" );
        blue.setText( "" );
        redDiff.setText( "" );
        greenDiff.setText( "" );
        blueDiff.setText( "" );
        currentBackgroundColor = makeRandomColor();
        imageView.setBackgroundColor( currentBackgroundColor );
        distance.setText( "" );
        avgCompare.setText( "" );
        hideAll( distance, avgCompare, mainMenu, next, redDiff, blueDiff, greenDiff, smiley, frowny);
        showAll( submit, gameHeader );
    }

    public int makeRandomColor () {
        return ( Color.argb( 255, (int)(Math.random() * 256),
                (int)(Math.random() * 256),(int)(Math.random() * 256)) );
    }

    @Override
    public void onBackPressed() {
        hideAll( gameHeader, distance, avgCompare, red, green, blue, gameHeader, submit, mainMenu, next, redDiff, blueDiff, greenDiff, smiley, frowny );
        showAll( header, begin, results, imageView );
        imageView.setBackground( getDrawable( R.drawable.gradient ) );
    }
}


