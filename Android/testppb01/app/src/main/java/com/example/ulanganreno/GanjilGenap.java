package com.example.ulanganreno;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GanjilGenap extends AppCompatActivity {

    private EditText inputNumber;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganjil_genap);

        // Initialize views
        inputNumber = findViewById(R.id.inputNumber);
        resultText = findViewById(R.id.resultText);
    }

    // Method to check if the number is odd or even
    public void checkOddEven(View view) {
        // Get the number from input field
        String numberStr = inputNumber.getText().toString();

        // Validate input
        if (numberStr.isEmpty()) {
            resultText.setText("Harap masukkan angka.");
            return;
        }

        // Convert input string to integer
        int number = Integer.parseInt(numberStr);

        // Check if the number is even or odd
        if (number % 2 == 0) {
            resultText.setText("Hasil: " + number + " adalah Genap");
        } else {
            resultText.setText("Hasil: " + number + " adalah Ganjil");
        }
    }
}