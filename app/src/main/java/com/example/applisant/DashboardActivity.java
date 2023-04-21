package com.example.applisant;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    private EditText sleepInput, heightInput, weightInput, waterInput;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sleepInput = findViewById(R.id.sleep_input);
        heightInput = findViewById(R.id.height_input);
        weightInput = findViewById(R.id.weight_input);
        waterInput = findViewById(R.id.water_input);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    double sleepHours = Double.parseDouble(sleepInput.getText().toString());
                    double height = Double.parseDouble(heightInput.getText().toString());
                    double weight = Double.parseDouble(weightInput.getText().toString());
                    double waterIntake = Double.parseDouble(waterInput.getText().toString());

                    double bmi = calculateBmi(height, weight);
                    String advice = generateAdvice(sleepHours, bmi, waterIntake);

                    Intent intent = new Intent(DashboardActivity.this, AdviceActivity.class);
                    intent.putExtra("advice", advice);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateInputs() {
        boolean valid = true;

        if (TextUtils.isEmpty(sleepInput.getText().toString())) {
            sleepInput.setError("Champ requis");
            valid = false;
        }

        if (TextUtils.isEmpty(heightInput.getText().toString())) {
            heightInput.setError("Champ requis");
            valid = false;
        }

        if (TextUtils.isEmpty(weightInput.getText().toString())) {
            weightInput.setError("Champ requis");
            valid = false;
        }

        if (TextUtils.isEmpty(waterInput.getText().toString())) {
            waterInput.setError("Champ requis");
            valid = false;
        }

        return valid;
    }

    private double calculateBmi(double height, double weight) {
        return weight / Math.pow(height / 100, 2);
    }

    private String generateAdvice(double sleepHours, double bmi, double waterIntake) {
        StringBuilder advice = new StringBuilder();

        if (sleepHours < 7) {
            advice.append("Essayez de dormir au moins 7 heures par nuit pour une meilleure récupération. \n");
        }

        if (bmi < 18.5) {
            advice.append("Votre IMC est faible. Essayez de prendre du poids de manière saine. \n");
        } else if (bmi >= 18.5 && bmi < 25) {
            advice.append("Votre IMC est normal. Continuez à maintenir un mode de vie sain. \n");
        } else {
            advice.append("Votre IMC est élevé. Essayez de perdre du poids en adoptant une alimentation équilibrée et en faisant de l'exercice. \n");
        }

        if (waterIntake < 2) {
            advice.append("Essayez de boire au moins 2 litres d'eau par jour pour rester hydraté. \n");
        }

        return advice.toString();
    }
}
