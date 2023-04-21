package com.example.applisant;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NextActivity extends AppCompatActivity {

    private EditText sleepInput;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        sleepInput = findViewById(R.id.sleep_input);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sleepHours = sleepInput.getText().toString();
                if (!sleepHours.isEmpty()) {
                    // Traiter les données ici, par exemple les envoyer à une API ou les enregistrer localement.
                    Toast.makeText(NextActivity.this, "Durée de sommeil soumise: " + sleepHours + " heures", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NextActivity.this, "Veuillez entrer une durée de sommeil", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
