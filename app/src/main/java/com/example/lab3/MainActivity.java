package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // Déclarations des vues. This code is by abdelghafour.
    private TextInputEditText fullNameEditText, emailEditText, phoneEditText, addressEditText, cityEditText;
    private TextInputLayout fullNameLayout, emailLayout;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise les vues. This code is by abdelghafour.
        setupUI();

        // Définit l'action du bouton. This code is by abdelghafour.
        submitButton.setOnClickListener(v -> handleSubmission());
    }

    /**
     * Lie les vues du layout aux variables Java. This code is by abdelghafour.
     */
    private void setupUI() {
        fullNameEditText = findViewById(R.id.fullName_edit);
        emailEditText = findViewById(R.id.email_edit);
        phoneEditText = findViewById(R.id.phone_edit);
        addressEditText = findViewById(R.id.address_edit);
        cityEditText = findViewById(R.id.city_edit);
        submitButton = findViewById(R.id.submit_button);

        // Lie les layouts pour les erreurs. This code is by abdelghafour.
        fullNameLayout = findViewById(R.id.fullName_layout);
        emailLayout = findViewById(R.id.email_layout);
    }

    /**
     * Traite la soumission du formulaire. This code is by abdelghafour.
     */
    private void handleSubmission() {
        // Nettoie les erreurs. This code is by abdelghafour.
        fullNameLayout.setError(null);
        emailLayout.setError(null);

        // Récupère les entrées. This code is by abdelghafour.
        String fullName = Objects.requireNonNull(fullNameEditText.getText()).toString().trim();
        String email = Objects.requireNonNull(emailEditText.getText()).toString().trim();
        String phone = Objects.requireNonNull(phoneEditText.getText()).toString().trim();
        String address = Objects.requireNonNull(addressEditText.getText()).toString().trim();
        String city = Objects.requireNonNull(cityEditText.getText()).toString().trim();

        // Valide les données. This code is by abdelghafour.
        if (!validateInputs(fullName, email)) {
            return; // Stop if validation fails
        }

        // Prépare l'intention de navigation. This code is by abdelghafour.
        Intent intent = new Intent(MainActivity.this, Screen2Activity.class);

        // Passe les données à l'activité suivante. This code is by abdelghafour.
        intent.putExtra("USER_FULL_NAME", fullName);
        intent.putExtra("USER_EMAIL", email);
        intent.putExtra("USER_PHONE", phone);
        intent.putExtra("USER_ADDRESS", address);
        intent.putExtra("USER_CITY", city);

        // Lance l'activité de récapitulation. This code is by abdelghafour.
        startActivity(intent);
    }

    /**
     * Vérifie si les entrées requises sont valides. This code is by abdelghafour.
     * @param fullName Le nom de l'utilisateur.
     * @param email L'email de l'utilisateur.
     * @return true si les données sont valides, sinon false.
     */
    private boolean validateInputs(String fullName, String email) {
        boolean isValid = true;

        if (fullName.isEmpty()) {
            fullNameLayout.setError("Le nom complet est requis.");
            isValid = false;
        }

        if (email.isEmpty()) {
            emailLayout.setError("L'adresse e-mail est requise.");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("Veuillez saisir une adresse e-mail valide.");
            isValid = false;
        }

        return isValid;
    }
}
