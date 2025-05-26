package com.example.onlinereno;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class CheckoutActivity extends AppCompatActivity {
    private TextView totalPriceValueTextView;
    private TextView orderDetailsLabelTextView;
    private LinearLayout orderDetailsContainerLinearLayout;
    private Button confirmOrderButton;
    private DatabaseHelper databaseHelper;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "StockPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Inisialisasi DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Inisialisasi views
        ImageView backIcon = findViewById(R.id.backIcon);
        totalPriceValueTextView = findViewById(R.id.totalPriceValue);
        orderDetailsLabelTextView = findViewById(R.id.orderDetailsLabel);
        orderDetailsContainerLinearLayout = findViewById(R.id.orderDetailsContainer);
        confirmOrderButton = findViewById(R.id.confirmOrderButton);

        // Mendapatkan data dari Intent
        Intent intent = getIntent();
        String fruitName = intent.getStringExtra("FRUIT_NAME");
        double pricePerKg = intent.getDoubleExtra("PRICE_PER_KG", 0.0);
        double quantity = intent.getDoubleExtra("QUANTITY", 0.0);
        double totalPrice = intent.getDoubleExtra("TOTAL_PRICE", 0.0);

        // Menampilkan total harga
        totalPriceValueTextView.setText(String.format("$%.2f", totalPrice));

        // Menampilkan detail pesanan
        orderDetailsLabelTextView.setText("Order Details:");
        addOrderItem(fruitName, pricePerKg, quantity, totalPrice);

        // Mengatur listener untuk tombol konfirmasi
        confirmOrderButton.setOnClickListener(v -> {
            // Simpan data pesanan ke database
            boolean isInserted = databaseHelper.addHistory(fruitName, pricePerKg, quantity, totalPrice);
            if (isInserted) {
                // Simpan informasi ke SharedPreferences
                String stockKey = getStockKey(fruitName);
                int currentStock = sharedPreferences.getInt(stockKey, 99); // Ambil stok saat ini
                int newStock = currentStock - (int) quantity;
                sharedPreferences.edit().putInt(stockKey, newStock).apply();

                Toast.makeText(this, "PEMBELIAN BERHASIL! Stok tersisa: " + newStock + " kg", Toast.LENGTH_SHORT).show();
                finish(); // Menutup CheckoutActivity
            } else {
                Toast.makeText(this, "Gagal menyimpan data pesanan!", Toast.LENGTH_SHORT).show();
            }
        });

        // Mengatur listener untuk ikon kembali
        backIcon.setOnClickListener(v -> finish());
    }

    private String getStockKey(String fruitName) {
        switch (fruitName) {
            case "Apple":
                return "stock_apple";
            case "Berry":
                return "stock_berry";
            case "Watermelon":
                return "stock_watermelon";
            case "Orange":
                return "stock_orange";
            default:
                return "stock_default"; // Kunci default jika nama buah tidak dikenali
        }
    }

    private void addOrderItem(String fruitName, double pricePerKg, double quantity, double totalPrice) {
        // Membuat CardView untuk item pesanan
        CardView orderItemCardView = new CardView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(15, 15, 15, 15); // Set margins
        orderItemCardView.setLayoutParams(params);
        orderItemCardView.setCardBackgroundColor(getResources().getColor(R.color.black2));
        orderItemCardView.setCardElevation(5);
        orderItemCardView.setRadius(10);

        // Membuat layout untuk detail pesanan
        LinearLayout orderItemLayout = new LinearLayout(this);
        orderItemLayout.setOrientation(LinearLayout.VERTICAL);

        // Menambahkan detail pesanan
        TextView fruitNameTextView = new TextView(this);
        fruitNameTextView.setText("Fruit: " + fruitName);
        fruitNameTextView.setTextColor(getResources().getColor(R.color.white));
        orderItemLayout.addView(fruitNameTextView);

        TextView priceTextView = new TextView(this);
        priceTextView.setText("Price per kg: $" + pricePerKg);
        priceTextView.setTextColor(getResources().getColor(R.color.white));
        orderItemLayout.addView(priceTextView);

        TextView quantityTextView = new TextView(this);
        quantityTextView.setText("Quantity: " + quantity + " kg");
        quantityTextView.setTextColor(getResources().getColor(R.color.white));
        orderItemLayout.addView(quantityTextView);

        TextView totalTextView = new TextView(this);
        totalTextView.setText("Total Price: $" + totalPrice);
        totalTextView.setTextColor(getResources().getColor(R.color.white));
        orderItemLayout.addView(totalTextView);

        // Menambahkan layout detail pesanan ke CardView
        orderItemCardView.addView(orderItemLayout);
        orderDetailsContainerLinearLayout.addView(orderItemCardView);
    }
}