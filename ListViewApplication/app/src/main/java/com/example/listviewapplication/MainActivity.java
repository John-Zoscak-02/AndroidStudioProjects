package com.example.listviewapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public LinearLayout linearLayout;
    public Button button;
    public EditText editText;
    public ArrayList<Player> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<Player>();
        linearLayout = findViewById( R.id.linearLayout );
        editText = findViewById( R.id.editText );

        createButton();
    }

    public void createButton() {
        button = findViewById( R.id.button );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( arrayList.size() < 8 ) {
                    arrayList.add( new Player(editText.getText().toString()) );
                    editText.setText( "" );
                    ListView listView = findViewById( R.id.listView );
                    UserArrayAdaptor userArrayAdaptor = new UserArrayAdaptor(getApplicationContext(), arrayList);
                    listView.setAdapter( userArrayAdaptor );
                }
                else {
                    button.setEnabled( false );
                }
            }
        });

    }
}
