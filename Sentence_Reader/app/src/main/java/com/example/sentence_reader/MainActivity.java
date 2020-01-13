package com.example.sentence_reader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    TextView textView1;
    SeekBar seekBar;
    ImageView background;
    ImageView splashScreen;
    int secondsLeft;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createEditText();
        createSeekBar();
        textView1 = findViewById( R.id.textView );
        editText2 = findViewById( R.id.editText2 );
        splashScreen = findViewById( R.id.imageView4);
        background = findViewById( R.id.imageView2 );

        splashScreen.setVisibility(View.VISIBLE);
        background.setVisibility(View.INVISIBLE);
        seekBar.setVisibility(View.INVISIBLE);
        editText.setVisibility(View.INVISIBLE);
        textView1.setVisibility(View.INVISIBLE);
        editText2.setVisibility(View.INVISIBLE);

        secondsLeft = 5;
        runTimer();

        editText2.setEnabled( false );

    }

    public void createSeekBar() {
        seekBar = findViewById( R.id.seekBar );
        if (! (text == null) ) {
            seekBar.setMax( text.split("/s+").length - 1 );
        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("SeekBarChange", String.valueOf( seekBar.getProgress() ));
                if (! (text == null) ) {
                    printInfo( seekBar.getProgress() );
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
     }

     public void createEditText() {
         editText = findViewById( R.id.editText );
         editText.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
             @Override
             public void afterTextChanged(Editable editable) {
                 seekBar.setMax( editText.getText().toString().split("\\s+").length - 1 );
                 text = editText.getText().toString();
                 editText2.setText( "" );
             }
         });
     }
     public void printInfo( int i ) {
        Scanner scan = new Scanner( text );
        int x = -1;
        String word = "";

        while ( x < i ) {
            word = scan.next();
            x++;
        }

        editText2.setText( new StringBuilder( "Word #" ).append( i + 1 ).append( ": " ).append( word ).toString() );
    }

    public void runTimer() {
        new CountDownTimer(secondsLeft * 1000, 1000) {
            @Override
            public void onTick(long l) {
                secondsLeft--;
            }
            @Override
            public void onFinish() {
                splashScreen.setVisibility(View.INVISIBLE);
                background.setVisibility(View.VISIBLE);
                seekBar.setVisibility(View.VISIBLE);
                editText.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.VISIBLE);
                editText2.setVisibility(View.VISIBLE);

                seekBar.bringToFront();
                editText.bringToFront();
                textView1.bringToFront();
                editText2.bringToFront();
            }
        }.start();
    }
}
