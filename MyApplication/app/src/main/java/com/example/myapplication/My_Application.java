package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class My_Application extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById( R.id.editText );
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }


            @Override
            public void afterTextChanged(Editable editable) {
                if ( editText.getText().toString().contains(".") ) {
                    if ( editText.getText().toString().substring(editText.getText().toString().indexOf(".")).length() > 3) {
                        editText.setText( editText.getText().toString().substring( 0, editText.getText().toString().length() - 1 )   );
                        editText.setSelection( editText.getText().toString().length() );
                    }
                }
            }
        });

        Button b = findViewById( R.id.button );
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertCurrency();
            }
        });
    }

    public void convertCurrency( ) {
        Log.i("Info","The currency button was pressed!!");
        double pounds = round(Double.parseDouble( editText.getText().toString() ) * 1.23 , 2);
        Toast.makeText(this, String.valueOf( pounds ) , Toast.LENGTH_LONG).show();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
