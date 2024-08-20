package com.example.h2conversion;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etTm, etIm, etAsh, egiit tFourthInputValue;
    private Spinner spinnerFourthInput;
    private Button btnCalculate, btnClear;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTm = findViewById(R.id.et_tm);
        etIm = findViewById(R.id.et_im);
        etAsh = findViewById(R.id.et_ash);
        etFourthInputValue = findViewById(R.id.et_fourth_input_value);
        spinnerFourthInput = findViewById(R.id.spinner_fourth_input);
        btnCalculate = findViewById(R.id.btn_calculate);
        btnClear = findViewById(R.id.btn_clear);
        tvResult = findViewById(R.id.tv_result);

        String[] fourthInputOptions = {"Hydrogen(adb)", "Hydrogen(db)", "Hydrogen(daf)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fourthInputOptions);
        spinnerFourthInput.setAdapter(adapter);

        spinnerFourthInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Do nothing
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateHarbValue();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllInputs();
            }
        });
    }

    private void calculateHarbValue() {
        if (etTm.getText().toString().trim().isEmpty() ||
                etIm.getText().toString().trim().isEmpty() ||
                etAsh.getText().toString().trim().isEmpty() ||
                etFourthInputValue.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please Enter The Inputs", Toast.LENGTH_SHORT).show();
            return;
        }

        double tm = Double.parseDouble(etTm.getText().toString());
        double im = Double.parseDouble(etIm.getText().toString());
        double ash = Double.parseDouble(etAsh.getText().toString());
        double fourthInputValue = Double.parseDouble(etFourthInputValue.getText().toString());
        String fourthInputType = spinnerFourthInput.getSelectedItem().toString();

        double harbValue = 0;

        if (fourthInputType.equals("Hydrogen(adb)")) {
            harbValue = (fourthInputValue - (0.1119 * im)) * ((100 - tm) / (100 - im)) + (0.1119 * tm);
        } else if (fourthInputType.equals("Hydrogen(db)")) {
            harbValue = fourthInputValue * ((100 -tm) / 100) + (0.1119 * tm);
        } else if (fourthInputType.equals("Hydrogen(daf)")) {
            //harbValue = (fourthInputValue * (100 - ash) / 100) * ((100 - tm) / 100) + (0.1119 * tm);
            //harbValue = ((fourthInputValue * (1010 - ash) / 100) * ((100 - tm) / 100)) + (0.1119 * tm);
            double temp1 = (100 - ash) / 100;
            double temp2 = fourthInputValue * temp1;
            double temp3 = (100 - tm) / 100;
            harbValue = temp2 * temp3 + (0.1119 * tm);
        }

        //tvResult.setText("Harb Value:" + String.format("%.4f", harbValue));
        harbValue = Math.round(harbValue * 100.0) / 100.0;

        tvResult.setText("Hydrogen(arb) Value : " + String.format("%.2f", harbValue));
    }

    private void clearAllInputs() {
        etTm.setText("");
        etIm.setText("");
        etAsh.setText("");
        etFourthInputValue.setText("");
        tvResult.setText("");
    }


}