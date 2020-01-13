package com.example.spelling_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    private ImageView splashscreen;
    private ImageView background;
    private RadioGroup radioGroup;
    private TextView numberBox;
    private TextView header;
    private TextView wordBox;
    private TextView display;
    private RadioButton correct;
    private RadioButton skip;
    private RadioButton incorrect;
    private Button button;
    private Button resetButton;
    private Switch switch1;
    private int ran;
    private int secondsLeft;
    private int numCorrect;
    private String select;
    private ArrayList<String> words;
    private ArrayList<Boolean> spelling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        words = new ArrayList<>();
        spelling = new ArrayList<>();

        for (Words val : Words.values() )  {
            words.add( val.getName() );
            spelling.add( val.getCorrect() );
        }

        splashscreen = findViewById( R.id.imageView2 );
        background = findViewById( R.id.imageView );
        numberBox = findViewById( R.id.textView3 );
        header = findViewById( R.id.textView );
        wordBox = findViewById( R.id.editText );
        correct = findViewById( R.id.radioButton );
        skip = findViewById( R.id.radioButton2 );
        incorrect = findViewById( R.id.radioButton3 );
        switch1 = findViewById( R.id.switch1 );
        display = findViewById( R.id.textView4 );

        createButton();
        createResetButton();
        createSwitch();
        createRadioGroup();

        showAll(splashscreen, background);
        hideAll(header, numberBox, wordBox, correct, skip, incorrect, button, switch1, display);
        resetButton.setVisibility( View.GONE );

        runTimer( 3000, true );

    }

    enum Words {

        WORD1 ( "Antidisestablishmentarinism", false ),
        WORD2 ("Baloney", false ),
        WORD3 ( "Bigly", true ),
        WORD4 ( "Concensus", false ),
        WORD5 ( "Conscious", true ),
        WORD6 ( "Milleniums", false ),
        WORD7 ( "Plinth", true ),
        WORD8 ( "Plynth", true ),
        WORD9 ( "Pneumonoultramicroscopicsilicovolcanoconiosis", true ),
        WORD10 ( "Pnuemonia", false),
        WORD11 ( "Possession", true ),
        WORD12 ( "Vacuum", true );

        private final String str;
        private final boolean correct;

        Words ( String str, Boolean correct ) {
            this.str = str;
            this.correct = correct;
        }
        private String getName() { return str; }
        private boolean getCorrect() { return correct; }

    }

    public void updatePage () {
        if ( words.size() > 0) {
            ran = (int)(Math.random() * words.size());
            switch1.setChecked( false );
            switch1.setEnabled( false );
            wordBox.setText( words.get( ran ) );
            numberBox.setText( String.format( "Questions Left:%o", words.size() ) );
            radioGroup.clearCheck();
        }
        else {
            finishPage();
        }
    }

    public void countdown() {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;
        String result = String.format("%02d:%02d", minutes, seconds);
        button.setText(result);
    }

    public void finishPage() {
        countDownTimer.cancel();
        showAll( display, resetButton);
        hideAll( numberBox, wordBox, radioGroup, switch1 );
        display.setText( String.format( "%o/13" ,numCorrect ) );
    }

    public void runTimer(int milis, final boolean splash ) {
        countDownTimer = new CountDownTimer(milis, 1000) {
            @Override
            public void onTick(long l) {
                if ( !splash ) {
                    countdown();
                }
                secondsLeft--;
            }
            @Override
            public void onFinish() {
                if ( splash ) {
                    splashscreen.setVisibility(View.INVISIBLE);
                    button.setVisibility( View.VISIBLE );
                    button.bringToFront();
                }
                else {
                    finishPage();
                }
            }
        }.start();
    }

    public void createButton() {
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button.getText().toString().equals("Begin!")) {
                    secondsLeft = 300;
                    runTimer(300000, false);
                    showAll(numberBox, header, switch1, wordBox, correct, skip, incorrect, button, radioGroup );
                    updatePage();
                }
            }
        });
    }
    public void createSwitch() {
        switch1 = findViewById( R.id.switch1 );
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if ( select.equals( "skip" ) ) {
                    updatePage();
                }
                else if ( spelling.remove( ran ) == select.equals( "incorrect" ) ) {
                    finishPage();
                }
                else {
                    words.remove( ran );
                    updatePage();
                    numCorrect++;
                }
                switch1.setChecked( false );
                switch1.setEnabled( false );
            }
        });

    }

    public void showAll(View...views) {
        for ( View v : views ) {
            v.setVisibility( View.VISIBLE );
        }
    }

    public void hideAll(View...views) {
        for ( View v : views ) {
            v.setVisibility( View.INVISIBLE );
        }
    }

    public void createRadioGroup() {
        radioGroup = findViewById( R.id.radioGroup );

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged( RadioGroup radioGroup, int checkedId ) {
                switch (checkedId) {
                    case -1:
                        break;
                    case R.id.radioButton:
                        select = "correct";
                        switch1.setEnabled( true );
                        break;
                    case R.id.radioButton2:
                        select = "skip";
                        switch1.setEnabled( true );
                        break;
                    default:
                        select = "incorrect";
                        switch1.setEnabled( true );
                        break;
                }
            }
        });

    }
    public void createResetButton() {
        resetButton = findViewById( R.id.button2 );
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility( View.VISIBLE );
                button.bringToFront();
                button.setText( getResources().getString( R.string.Button ) );
                hideAll(numberBox, header, switch1, wordBox, correct, skip, incorrect, display, resetButton);
                countDownTimer = null;
                numCorrect = 0;

                words.clear();
                spelling.clear();
                for (Words val : Words.values() ) {
                    words.add( val.getName() );
                    spelling.add( val.getCorrect() );
                }
            }
        });
    }
}
