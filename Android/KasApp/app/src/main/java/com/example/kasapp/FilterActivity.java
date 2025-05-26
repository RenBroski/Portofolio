package com.example.kasapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

public class FilterActivity extends AppCompatActivity {

    EditText edtDari,edtSampai;
    Button btnFilter;
    DatePickerDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_filter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtDari = findViewById(R.id.edtDari);
        edtSampai = findViewById(R.id.edtSampai);
        btnFilter = findViewById(R.id.btnFilter);
        Calendar calendar = Calendar.getInstance();
        int tahun = calendar.get(Calendar.YEAR);
        int bulan = calendar.get(Calendar.MONTH);
        int tanggal = calendar.get(Calendar.DAY_OF_MONTH);

        edtDari.setOnClickListener(v -> {
            dialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                NumberFormat format = new DecimalFormat("00");
                    edtDari.setText(year + "/" + (month + 1) + "/" + format.format(dayOfMonth));
                }, tahun, bulan, tanggal);
            dialog.show();
        });

        edtSampai.setOnClickListener(v -> {
            dialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                NumberFormat format = new DecimalFormat("00");
                edtSampai.setText(year + "/" + (month + 1) + "/" + format.format(dayOfMonth));
            }, tahun, bulan, tanggal);
            dialog.show();
        });

        btnFilter.setOnClickListener(v -> {
            if(edtDari.getText().toString().isEmpty()){
                Toast.makeText(this, "input dari tanggal belum diisi",
                        Toast.LENGTH_SHORT).show();
            }else if(edtSampai.getText().toString().isEmpty()){
                Toast.makeText(this, "input dari tanggal belum diisi",
                        Toast.LENGTH_SHORT).show();
            }else {
                MainActivity.textFilter.setVisibility(View.VISIBLE);
                MainActivity.textFilter.setText("FILTER: " + edtDari.getText().toString() + " S/D " + edtSampai.getText().toString());
                MainActivity.tglDari = edtDari.getText().toString();
                MainActivity.tglSampai = edtSampai.getText().toString();
                MainActivity.filter = true;
                finish();
            }
        });
    }
}