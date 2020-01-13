package com.example.terms_tik_tok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terms_Activity extends AppCompatActivity {

    public static final String CODE = "sialoquent";
    public TextView termsView;
    public EditText editText;
    public Button agree;
    public Button disagree;
    private StringBuilder text = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_);

        editText = findViewById( R.id.editText2 );
        createAgree();
        createDisagree();

        populateTermsView();
    }

    public void createAgree() {
        agree = findViewById( R.id.button3 );
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra( MainActivity.ACCEPTED_BOOLEAN, true );
                intent.putExtra(MainActivity.ENTERED_CODE, editText.getText().toString() );
                setResult( 1, intent );
                finish();
            }
        });
    }

    public void createDisagree() {
        disagree = findViewById( R.id.button4 );
        disagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra( MainActivity.ACCEPTED_BOOLEAN, false );
                intent.putExtra(MainActivity.ENTERED_CODE, editText.getText().toString() );
                setResult( 1, intent );
                finish();
            }
        });
    }

    public void populateTermsView() {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("terms.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                text.append(mLine);
                text.append('\n');
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error reading file!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }

            termsView = findViewById(R.id.textView3);
            termsView.setText((CharSequence) text);
        }
    }
}
