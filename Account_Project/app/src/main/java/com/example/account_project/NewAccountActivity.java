package com.example.account_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
                    Account account = new Account( username.getText().toString(), CryptWithMD5.cryptWithMD5(password.getText().toString()),
                            data.getText().toString());
                    FileWriter fileWriter = new FileWriter("./src/info.json");
                    String json = gson.toJson(account);
                    Log.println(Log.WARN, "json file: ", json);
                    fileWriter.append( json );
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
