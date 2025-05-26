package com.example.ulanganreno;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VLbalok extends AppCompatActivity {

    private EditText inputPanjang, inputWidth, inputHeight;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlbalok);

        // Initialize views
        inputPanjang = findViewById(R.id.inputPanjang);
        inputWidth = findViewById(R.id.inputWidth);
        inputHeight = findViewById(R.id.inputHeight);
        resultText = findViewById(R.id.resultText);
    }

    // Method to calculate the surface area of the block
    public void calculateSurfaceArea(View view) {
        // Get the panjang, lebar, and tinggi from input fields
        String panjangStr = inputPanjang.getText().toString();
        String widthStr = inputWidth.getText().toString();
        String heightStr = inputHeight.getText().toString();

        // Validate inputs
        if (panjangStr.isEmpty() || widthStr.isEmpty() || heightStr.isEmpty()) {
            resultText.setText("Harap masukkan panjang, lebar, dan tinggi.");
            return;
        }

        // Convert input strings to double
        double panjang = Double.parseDouble(panjangStr);
        double width = Double.parseDouble(widthStr);
        double height = Double.parseDouble(heightStr);

        // Calculate the surface area of the block
        double surfaceArea = 2 * (panjang * width + panjang * height + width * height);

        // Display the result
        resultText.setText("Luas Permukaan: " + surfaceArea + " m²");
    }
}
