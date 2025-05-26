package com.example.onlinereno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class SalesSummaryActivity extends AppCompatActivity {
    private TextView salesSummaryTextView;
    private TextView summaryFooter;
    private Button backButton; // Deklarasi tombol kembali

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_summary);

        salesSummaryTextView = findViewById(R.id.sales_summary_text_view);
        summaryFooter = findViewById(R.id.summary_footer);
        backButton = findViewById(R.id.back_button); // Inisialisasi tombol kembali

        // Ambil data dari intent
        HashMap<String, Double[]> salesData = (HashMap<String, Double[]>) getIntent().getSerializableExtra("salesData");
        displaySalesSummary(salesData);

        // Set onClickListener untuk tombol kembali
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Kembali ke activity sebelumnya
            }
        });
    }

    private void displaySalesSummary(HashMap<String, Double[]> salesData) {
        StringBuilder summary = new StringBuilder();
        double grandTotalPrice = 0.0;

        for (String fruit : salesData.keySet()) {
            Double[] values = salesData.get(fruit);
            double totalQuantity = values[0];  // Total berat
            double totalPrice = values[1];     // Total harga
            grandTotalPrice += totalPrice;

            summary.append(fruit).append(": ")
                    .append(String.format("%.1f kg", totalQuantity)).append("\n")
                    .append("Total Harga: $").append(String.format("%.2f", totalPrice)).append("\n\n");
        }

        salesSummaryTextView.setText(summary.toString());
        summaryFooter.setText("Grand Total Harga: $" + String.format("%.2f", grandTotalPrice));
    }
}