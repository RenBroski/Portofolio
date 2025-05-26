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

public class Lsegitiga extends AppCompatActivity {

    private EditText inputBase, inputHeight;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsegitiga);

        // Initialize views
        inputBase = findViewById(R.id.inputBase);
        inputHeight = findViewById(R.id.inputHeight);
        resultText = findViewById(R.id.resultText);
    }

    // Method to calculate area of triangle
    public void calculateArea(View view) {
        // Get the base and height from input fields
        String baseStr = inputBase.getText().toString();
        String heightStr = inputHeight.getText().toString();

        // Validate inputs
        if (baseStr.isEmpty() || heightStr.isEmpty()) {
            resultText.setText("Harap masukkan alas dan tinggi.");
            return;
        }

        // Convert input strings to double
        double base = Double.parseDouble(baseStr);
        double height = Double.parseDouble(heightStr);

        // Calculate the area of the triangle
        double area = 0.5 * base * height;

        // Display the result
        resultText.setText("Luas: " + area);
    }
}