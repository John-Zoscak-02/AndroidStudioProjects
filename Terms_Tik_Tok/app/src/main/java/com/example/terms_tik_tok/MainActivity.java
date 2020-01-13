package com.example.terms_tik_tok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button terms;
    private Button logon;
    private boolean accepted;
    private String enteredCode;

    public static final String ACCEPTED_BOOLEAN = "whatever";
    public static final String ENTERED_CODE = "whatever2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createTerms();
        createLogon();
        logon.setEnabled( false );

        accepted = false;
        enteredCode = "";
    }

    public void createTerms() {
        terms = findViewById( R.id.button );
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), Terms_Activity.class );
                startActivityForResult( intent, 1 );
            }
        });
    }

    public void createLogon() {
        logon = findViewById( R.id.button2 );
        logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accepted && enteredCode.equals( Terms_Activity.CODE ) ) {
                    Toast.makeText( getApplicationContext(), "Success! Your code of " + Terms_Activity.CODE + " was correct!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if ( enteredCode.isEmpty() ) {
                        Toast.makeText( getApplicationContext(), "Fail! Your code of <NO CODE> was incorrect", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText( getApplicationContext(), "Fail! Your code of " + enteredCode + " was incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ( resultCode == RESULT_CANCELED) {
            return;
        }
        if ( requestCode == 1 ) {
            accepted = data.getBooleanExtra( ACCEPTED_BOOLEAN, false );
            enteredCode = data.getStringExtra( ENTERED_CODE );
            if ( accepted ) {
                logon.setEnabled( true );
            }
            else {
                logon.setEnabled( false );
            }
        }
        }
    }
