package com.example.ulanganreno;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Lpersegi extends AppCompatActivity {
    private EditText inputSide;
    private TextView resultText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lpersegi);

        // Initialize the views
        inputSide = findViewById(R.id.inputSide);
        resultText = findViewById(R.id.resultText);
    }

    // This method is called when the "Hitung Luas" button is clicked
    public void calculateArea(View view) {
        String sideInput = inputSide.getText().toString();

        // Check if the input field is empty
        if (TextUtils.isEmpty(sideInput)) {
            Toast.makeText(this, "Masukkan panjang sisi", Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse the input to a number
        try {
            double side = Double.parseDouble(sideInput);

            if (side <= 0) {
                Toast.makeText(this, "Panjang sisi harus lebih besar dari 0", Toast.LENGTH_SHORT).show();
                return;
            }

            // Calculate the area of the square
            double area = side * side;

            // Display the result
            resultText.setText(String.format("Luas: %.2f", area));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Input tidak valid", Toast.LENGTH_SHORT).show();
        }
    }
}