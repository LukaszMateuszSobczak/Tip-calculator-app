package com.lukaszsobczak.tipcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText costEditText;
    private RadioButton amazingButton;
    private RadioButton goodButton;
    private RadioButton okayButton;
    private SwitchMaterial roundSwitch;
    private Button calculateButton;
    private TextView resultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        findViews();
        buttonListener();

    }

    private void findViews() {
        costEditText = findViewById(R.id.edit_text_service_cost);
        amazingButton = findViewById(R.id.radio_button_amazing);
        goodButton = findViewById(R.id.radio_button_good);
        okayButton = findViewById(R.id.radio_button_okay);
        roundSwitch = findViewById(R.id.switch_round);
        calculateButton = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);
    }

    private void buttonListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat decimalFormatter = new DecimalFormat("0.00");
                resultText.setText("Tip amount: " + decimalFormatter.format(tipAmount()));
            }
        });
    }


    private double tipAmount() {
        double tip = 0;
        if (amazingButton.isChecked()) {
            tip = calculateTip(0.2);
        } else if (goodButton.isChecked()) {
            tip = calculateTip(0.18);
        } else if (okayButton.isChecked()) {
            tip = calculateTip(0.15);
        }

        if (roundSwitch.isChecked()) {
            tip = Math.round(tip);
        }
        return tip;
    }

    private double calculateTip(double tipPercentage) {
        double convertedValue = Double.parseDouble(costEditText.getText().toString());
        return convertedValue * tipPercentage;
    }
}