package com.example.onlinereno;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class detail_activity extends AppCompatActivity {

    private TextView fruitNameTextView;
    private TextView pricePerKgTextView;
    private TextView quantityTextView;
    private TextView totalTxt;
    private TextView addBtn;
    private double quantity = 1; // Default quantity
    private final String fruitName = "Apple"; // Fixed fruit name
    private final double pricePerKg = 3.0; // Fixed price per kg
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "StockPrefs";
    private static final String STOCK_KEY = "stock_apple";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize Views
        fruitNameTextView = findViewById(R.id.titleTxt);
        pricePerKgTextView = findViewById(R.id.priceKgTxt);
        quantityTextView = findViewById(R.id.weightTxt);
        totalTxt = findViewById(R.id.totalTxt);
        addBtn = findViewById(R.id.AddBtn);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int stock = sharedPreferences.getInt(STOCK_KEY, 99); // Default stock

        // Set Fruit Name and Price
        fruitNameTextView.setText(fruitName);
        pricePerKgTextView.setText("$" + String.format("%.2f", pricePerKg));
        updateTotalPrice();
        updateStockText(stock); // Update stock display

        // Add Button Listener
        addBtn.setOnClickListener(v -> {
            double totalPrice = pricePerKg * quantity;
            Intent checkoutIntent = new Intent(detail_activity.this, CheckoutActivity.class);
            checkoutIntent.putExtra("FRUIT_NAME", fruitName);
            checkoutIntent.putExtra("PRICE_PER_KG", pricePerKg);
            checkoutIntent.putExtra("QUANTITY", quantity);
            checkoutIntent.putExtra("TOTAL_PRICE", totalPrice);
            checkoutIntent.putExtra("STOCK", stock); // Kirim stok ke CheckoutActivity
            startActivity(checkoutIntent);
        });

        // Plus and Minus Button Listeners
        findViewById(R.id.plusBtn).setOnClickListener(v -> {
            quantity++;
            updateQuantityText();
        });

        findViewById(R.id.minusBtn).setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                updateQuantityText();
            }
        });
    }

    private void updateQuantityText() {
        quantityTextView.setText(quantity + " kg");
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double totalPrice = pricePerKg * quantity;
        totalTxt.setText("$" + String.format("%.2f", totalPrice));
    }

    private void updateStockText(int stock) {
        TextView stockTextView = findViewById(R.id.stockFruit);
        stockTextView.setText("Stock Fruit: " + stock + " kg");
    }
}