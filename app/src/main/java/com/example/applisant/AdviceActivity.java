package com.example.applisant;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AdviceActivity extends AppCompatActivity {

    private TextView adviceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);

        adviceTextView = findViewById(R.id.advice_text);

        // Récupérez les conseils passés via l'intent
        String advice = getIntent().getStringExtra("advice");

        // Affichez les conseils dans le TextView
        adviceTextView.setText(advice);
    }

}

