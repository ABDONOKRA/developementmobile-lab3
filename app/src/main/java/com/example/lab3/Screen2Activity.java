package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Screen2Activity extends AppCompatActivity {

    // Vues de l'UI. This code is by abdelghafour.
    private TextView summaryDetailsText;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        // Initialisation des vues. This code is by abdelghafour.
        summaryDetailsText = findViewById(R.id.summary_details_text);
        backButton = findViewById(R.id.back_button);

        // Affichage des données. This code is by abdelghafour.
        populateSummaryData();

        // Action du bouton retour. This code is by abdelghafour.
        backButton.setOnClickListener(v -> finish());
    }

    /**
     * Récupère les données et les affiche. This code is by abdelghafour.
     */
    private void populateSummaryData() {
        Intent intent = getIntent();

        // Extraction des données. This code is by abdelghafour.
        String fullName = intent.getStringExtra("USER_FULL_NAME");
        String email = intent.getStringExtra("USER_EMAIL");
        String phone = intent.getStringExtra("USER_PHONE");
        String address = intent.getStringExtra("USER_ADDRESS");
        String city = intent.getStringExtra("USER_CITY");

        // Construction du texte formaté. This code is by abdelghafour.
        SpannableStringBuilder summaryBuilder = new SpannableStringBuilder();
        addDetailLine(summaryBuilder, "Nom complet", fullName);
        addDetailLine(summaryBuilder, "Adresse e-mail", email);
        addDetailLine(summaryBuilder, "Téléphone", phone);
        addDetailLine(summaryBuilder, "Adresse", address);
        addDetailLine(summaryBuilder, "Ville", city);

        // Affichage du récapitulatif. This code is by abdelghafour.
        summaryDetailsText.setText(summaryBuilder);
    }

    /**
     * Ajoute une ligne au récapitulatif. This code is by abdelghafour.
     * @param builder Le constructeur de chaîne.
     * @param label Le libellé.
     * @param value La valeur.
     */
    private void addDetailLine(SpannableStringBuilder builder, String label, String value) {
        String formattedValue = (value == null || value.trim().isEmpty()) ? "Non fourni" : value.trim();
        int start = builder.length();
        builder.append(label).append(": ").append(formattedValue).append("\n\n");
        builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start, start + label.length(), SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
    }
}
