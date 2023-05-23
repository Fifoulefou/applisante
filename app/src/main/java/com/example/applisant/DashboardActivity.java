package com.example.applisant;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ArrayAdapter;


public class DashboardActivity extends AppCompatActivity {

    private EditText sleepInput, heightInput, weightInput, waterInput, stepsInput, ageInput;
    private Button submitButton;
    private TextView stepsAdviceTextView;
    private Spinner genderSpinner;
    private String[] genderOptions = {"Homme", "Femme"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Récupérer les références des vues
        sleepInput = findViewById(R.id.sleep_input);
        heightInput = findViewById(R.id.height_input);
        weightInput = findViewById(R.id.weight_input);
        waterInput = findViewById(R.id.water_input);
        stepsInput = findViewById(R.id.steps_input);
        submitButton = findViewById(R.id.submit_button);
        stepsAdviceTextView = findViewById(R.id.steps_advice_text);
        genderSpinner = findViewById(R.id.gender_spinner);

        // Remplir le Spinner avec les options "Homme" et "Femme"
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genderOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        // Ajouter un écouteur d'événements au bouton "Soumettre"
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    // Récupérer les valeurs des champs
                    double sleepHours = Double.parseDouble(sleepInput.getText().toString());
                    double height = Double.parseDouble(heightInput.getText().toString());
                    double weight = Double.parseDouble(weightInput.getText().toString());
                    double waterIntake = Double.parseDouble(waterInput.getText().toString());
                    int steps = Integer.parseInt(stepsInput.getText().toString());
                    String selectedGender = genderSpinner.getSelectedItem().toString();

                    // Calculer la masse graisseuse (utilisez votre propre logique ici)
                    double bodyFatPercentage = calculateBodyFatPercentage(selectedGender, height, weight);

                    // Générer le conseil en utilisant les nouvelles valeurs
                    String advice = generateAdvice(sleepHours, bodyFatPercentage, waterIntake, steps);

                    // Passer le conseil à l'activité suivante
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

        if (TextUtils.isEmpty(stepsInput.getText().toString())) {
            stepsInput.setError("Champ requis");
            valid = false;
        }
        return valid;
    }

    private double calculateBodyFatPercentage(String gender, double height, double weight) {
        // Logique pour calculer le pourcentage de graisse corporelle
        // en fonction du genre, de la taille et du poids

        // Exemple de calcul simple (à adapter selon vos besoins)
        if (gender.equals("Homme")) {
            // Calcul pour les hommes
            return (1.2 * (weight / (height * height))) - (10.8 * 1) - 5.4;
        } else {
            // Calcul pour les femmes
            return (1.2 * (weight / (height * height))) - (10.8 * 0) - 5.4;
        }
    }


    private String generateAdvice(double sleepHours, double bodyFatPercentage, double waterIntake, int steps) {
        StringBuilder advice = new StringBuilder();

        if (sleepHours < 7) {
            advice.append("Essayez de dormir au moins 7 heures par nuit pour une meilleure récupération. \n");
        }

        if (bodyFatPercentage < 20) {
            advice.append("Votre pourcentage de graisse corporelle est faible. Assurez-vous de maintenir une alimentation équilibrée et un niveau d'activité physique adéquat pour préserver votre santé. \n");
        } else if (bodyFatPercentage >= 20 && bodyFatPercentage < 30) {
            advice.append("Votre pourcentage de graisse corporelle est dans la plage normale. Continuez à maintenir un mode de vie sain. \n");
        } else {
            advice.append("Votre pourcentage de graisse corporelle est élevé. Considérez d'adopter une alimentation équilibrée et de faire de l'exercice régulièrement pour réduire votre pourcentage de graisse corporelle. \n");
        }

        // Ajoutez ici les autres conseils en fonction des autres paramètres

        return advice.toString();
    }
}

