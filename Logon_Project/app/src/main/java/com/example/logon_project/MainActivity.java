package com.example.logon_project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    public static final String FILE_NAME = "data.json";

    private Button submitButton;
    private Button createButton;

    private EditText accountText;
    private EditText passwordText;

    private Account[] accounts;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        try {
            FileInputStream fileInputStream = openFileInput( FILE_NAME );
            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read( buffer );
            String json = new String(buffer);
            accounts = gson.fromJson( json, Account[].class );
            fileInputStream.close();
        }
        catch ( Exception e ) {
            e.printStackTrace();
            try {
                Account[] a = {new Account("default","default","default")};
                String newJson = gson.toJson( a, Account[].class );
                FileOutputStream fileOutputStream = openFileOutput( MainActivity.FILE_NAME, Context.MODE_PRIVATE);
                fileOutputStream.write( newJson.getBytes() );
                fileOutputStream.close();
            }
            catch ( Exception error ) {
                error.printStackTrace();
            }
        }

        accountText = findViewById( R.id.editText2 );
        passwordText = findViewById( R.id.editText3 );
        createSubmitButton();
        createNewAccountButton();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        try {

            FileInputStream fileInputStream = openFileInput( FILE_NAME );
            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read( buffer );
            String json = new String(buffer);
            //Log.i("Json File", json );
            accounts = gson.fromJson( json, Account[].class );
            fileInputStream.close();

        }
        catch ( Exception e ) {
            e.printStackTrace();
            try {
                Account[] a = {new Account("default", "default", "default")};
                String newJson = gson.toJson(a, Account[].class);
                FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                fileOutputStream.write(newJson.getBytes());
                fileOutputStream.close();
                //Log.i("Made Default", newJson);
            } catch (Exception error) {
                error.printStackTrace();
            }
        }
    }

    private void createSubmitButton() {
        submitButton = findViewById( R.id.button2 );
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountUsername = accountText.getText().toString();
                String encryptedTryPassword = CryptWithMD5.cryptWithMD5( passwordText.getText().toString() );

                if ( accounts != null ) {
                    for ( Account account : accounts ) {
                        if ( account.getUsername().equals( accountUsername ) ) {
                            Account tryAccount= account;
                            if ( account.getPassword().equals( encryptedTryPassword )) {
                                Intent intent = new Intent( getApplicationContext(), UpdateAccountActivity.class );
                                startActivity( intent );
                                return;
                            }
                        }
                    }
                }
                Toast.makeText( getApplicationContext(), "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                passwordText.setText( "" );
            }
        });
    }

    private void createNewAccountButton() {
        createButton = findViewById( R.id.button3 );
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), NewAccountActivity.class );
                startActivity( intent );
            }
        });
    }

}
