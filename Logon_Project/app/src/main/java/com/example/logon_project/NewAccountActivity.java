package com.example.logon_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewAccountActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText data;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        username  = findViewById( R.id.editText4 );
        password  = findViewById( R.id.editText5 );
        data  = findViewById( R.id.editText );
        createSaveButton();
    }

    private void createSaveButton() {
        saveButton = findViewById( R.id.button );
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$";
                Pattern pattern = Pattern.compile( regex );
                Matcher matcher = pattern.matcher( password.getText().toString() );
                if ( matcher.matches() ) {
                    Gson gson = new GsonBuilder()
                            .setPrettyPrinting()
                            .create();

                    try {
                        Account[] newAccounts = {new Account( username.getText().toString(), CryptWithMD5.cryptWithMD5(password.getText().toString()), data.getText().toString())};

                        FileInputStream fileInputStream = openFileInput( MainActivity.FILE_NAME );
                        int size = fileInputStream.available();
                        byte[] buffer = new byte[size];
                        fileInputStream.read( buffer );
                        String json = new String(buffer);
                        Account[] accounts = gson.fromJson( json, Account[].class );
                        fileInputStream.close();

                        boolean acceptableUsername = true;
                        for ( Account a : accounts ) {
                            if ( a.getUsername().equals(username.getText().toString()) ) {
                                acceptableUsername = false;
                            }
                        }

                        if ( acceptableUsername ) {
                            String newJson = gson.toJson(newAccounts, Account[].class );
                            FileOutputStream fileOutputStream = openFileOutput( MainActivity.FILE_NAME, Context.MODE_PRIVATE );
                            fileOutputStream.write( newJson.getBytes() );
                            fileOutputStream.close();
                        }
                        else {
                            Toast.makeText( getApplicationContext(), "That username is already taken", Toast.LENGTH_LONG).show();
                        }

                    }
                    catch ( Exception e ) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                    startActivity( intent );
                }
                else {
                    Toast.makeText( getApplicationContext(), "Must have password of 8-16 characters, one uppercae, one lowercase, one number, and one special symbol", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
