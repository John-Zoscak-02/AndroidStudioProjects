package com.example.logon_project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    private Button submitButton;
    private Button createButton;

    private EditText accountText;
    private EditText passwordText;

    private Account[] accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        accounts = null;

        try {
            FileReader fileReader = new FileReader("./app/info.json");
            accounts = gson.fromJson( fileReader, Account[].class);
            fileReader.close();
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }

        accountText = findViewById( R.id.editText2 );
        passwordText = findViewById( R.id.editText3 );
        createSubmitButton();
        createNewAccountButton();
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
