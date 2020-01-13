package com.example.tip_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText editText;
    private TextView tip;
    private TextView total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tip = findViewById( R.id.textView2 );
        total = findViewById( R.id.textView4 );

        createSpinner();

        editText = findViewById(R.id.editText );
        editText.setOnEditorActionListener( new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE ||
                                event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (event == null || !event.isShiftPressed()) {
                                if ( Double.parseDouble( spinner.getSelectedItem().toString() ) != 0 ) {
                                    getAnswer();
                                }
                                return true;
                            }
                        }
                        return false;
                    }
            }
        );

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

    }

    public void createSpinner() {
        spinner = findViewById( R.id.spinner2 );
        List<String> percents = new ArrayList<>();
        for ( double d = 0 ; d <= 100.0 ; d += 2.5) {
            percents.add( ((Double)d).toString() );
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>( this, android.R.layout.simple_spinner_dropdown_item, percents  );
        spinner.setAdapter( adapter );
        spinner.setSelection( 0 );

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i( "Spinner", spinner.getItemAtPosition( i ).toString() );
                getAnswer();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {  }
        });
    }

    private void getAnswer() {
            if (!editText.getText().toString().isEmpty()) {
                double amount = Double.parseDouble(editText.getText().toString());
                double val = amount * (Double.parseDouble( (spinner.getSelectedItem()).toString() ) / 100);
                tip.setText( new StringBuilder("Tip: $").append( String.format( "%d.%02d", ((Double)val).intValue(), ((Double)((val % 1.0 ) * 100)).intValue()) ).toString() );
                total.setText( new StringBuilder("Total: $").append( String.format( "%d.%02d", ((Double)val).intValue() + ((Double)amount).intValue(), ((Double)((val % 1.0 ) * 100)).intValue() + ((Double)((amount % 1.0 ) * 100)).intValue() )).toString() );
            }
    }

}
