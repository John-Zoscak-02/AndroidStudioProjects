package com.example.logon_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;



public class UpdateAccountActivity extends AppCompatActivity {
    private EditText data;
    private Button saveButton;
    private int accountLoc;
    private Account[] accounts;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);

        data = findViewById( R.id.editText );
        saveButton = findViewById( R.id.button5 );
        createSaveButton();

        try {
            gson = new GsonBuilder().
                    setPrettyPrinting().
                    create();

            FileInputStream fileInputStream = openFileInput( MainActivity.FILE_NAME );
            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read( buffer );
            String json = new String(buffer);
            Log.i("Json File", json );
            accounts = gson.fromJson( json, Account[].class );

            Intent intent = getIntent();
            String username = intent.getStringExtra( "username" );
            for ( int i = 0; i < accounts.length; i++ ) {
                if ( accounts[i].getUsername().equals(username) ) {
                    data.setText( accounts[i].getData() );
                    accountLoc = i;
                }
            }

            data.setText(accounts[accountLoc].getData());
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public  void createSaveButton() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    accounts[accountLoc].setData( data.getText().toString() );
                    String json = gson.toJson( accounts, Account[].class );
                    FileOutputStream fileOutputStream = openFileOutput( MainActivity.FILE_NAME, Context.MODE_PRIVATE );
                    fileOutputStream.write( json.getBytes() );
                    fileOutputStream.close();
                    //Log.println(Log.WARN, "json file: ", json);
                }
                catch ( Exception e ) {
                    e.printStackTrace();
                }

                Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                startActivity( intent );
            }
        });
    }
}





