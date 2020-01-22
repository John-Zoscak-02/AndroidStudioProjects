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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

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

                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .create();

                try {
                    Account[] newAccounts = {new Account( username.getText().toString(), CryptWithMD5.cryptWithMD5(password.getText().toString()), data.getText().toString())};

                    String newJson = gson.toJson(newAccounts, Account[].class );
                    //Log.i("Json File", newJson );
                    FileOutputStream fileOutputStream = openFileOutput( MainActivity.FILE_NAME, Context.MODE_PRIVATE );
                    fileOutputStream.write( newJson.getBytes() );
                    fileOutputStream.close();
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
