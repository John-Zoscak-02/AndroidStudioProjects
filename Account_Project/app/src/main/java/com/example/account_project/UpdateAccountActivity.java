package com.example.account_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;



public class UpdateAccountActivity extends AppCompatActivity {
    private EditText data;
    private Button save;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = findViewById( R.id.editText );
        createSaveButton();

        try {
            Gson gson = new GsonBuilder().
                    setPrettyPrinting().
                    create();

            FileReader fileReader = new FileReader( "./src/main/java/account_info.json" );
            Account[] accounts = gson.fromJson(fileReader, Account[].class);
            fileReader.close();

            Intent intent = getIntent();
            String username = intent.getStringExtra( "username" );
            for ( Account accountl : accounts ) {
                if ( accountl.getUsername().equals(username) ) {
                    data.setText( accountl.getData() );
                    account = accountl;
                }
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public  void createSaveButton() {
        save = findViewById( R.id.button );
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    account.setData( data.getText().toString() );
                    Gson gson = new GsonBuilder().
                            setPrettyPrinting().
                            create();
                    FileWriter fileWriter = new FileWriter( "./src/info.json" );
                    gson.toJson(account, Account.class, fileWriter);
                    fileWriter.close();
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





