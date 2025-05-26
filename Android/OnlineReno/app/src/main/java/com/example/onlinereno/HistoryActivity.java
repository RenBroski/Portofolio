package com.example.onlinereno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private OrderHistoryAdapter adapter;
    private DatabaseHelper databaseHelper;
    private TextView totalSummary;
    private Button viewSalesSummaryButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.history_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        totalSummary = findViewById(R.id.total_summary);
        viewSalesSummaryButton = findViewById(R.id.view_sales_summary_button);

        databaseHelper = new DatabaseHelper(this);
        loadOrderHistory();

        viewSalesSummaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, SalesSummaryActivity.class);
                HashMap<String, Double[]> salesData = calculateTotalPurchases();
                intent.putExtra("salesData", salesData);
                startActivity(intent);
            }
        });
    }

    private void loadOrderHistory() {
        List<OrderHistory> historyList = databaseHelper.getOrderHistoryList();
        adapter = new OrderHistoryAdapter(this, historyList);
        recyclerView.setAdapter(adapter);
    }

    private HashMap<String, Double[]> calculateTotalPurchases() {
        HashMap<String, Double[]> totals = new HashMap<>();
        List<OrderHistory> historyList = databaseHelper.getOrderHistoryList();

        for (OrderHistory order : historyList) {
            String fruitName = order.getFruitName();
            double quantity = order.getQuantity();
            double totalPrice = order.getTotalPrice();

            // Update total untuk setiap jenis buah
            if (!totals.containsKey(fruitName)) {
                totals.put(fruitName, new Double[]{0.0, 0.0}); // {total quantity, total price}
            }
            totals.get(fruitName)[0] += quantity; // Total quantity
            totals.get(fruitName)[1] += totalPrice; // Total price
        }

        return totals;
    }
}