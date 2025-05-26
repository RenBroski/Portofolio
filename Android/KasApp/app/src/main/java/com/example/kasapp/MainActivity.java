package com.example.kasapp;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnTambah;
    TextView textMasuk, textKeluar, textSaldo;
    ListView listTransaksi;
    String queryKas, queryTotal;
    Koneksi koneksi;
    Cursor cursor;
    ArrayList<HashMap<String, String>> arusKas = new ArrayList<>();
    public static String transaksi_id = "";
    public static String tglDari, tglSampai;
    public static boolean filter;
    SwipeRefreshLayout swipeRefreshLayout;
    public static TextView textFilter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_filter) {
            startActivity(new Intent(this, FilterActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textFilter = findViewById(R.id.textFilter);
        textMasuk = findViewById(R.id.textMasuk);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        textKeluar = findViewById(R.id.textKeluar);
        textSaldo = findViewById(R.id.textSaldo);
        listTransaksi = findViewById(R.id.listTransaksi);
        koneksi = new Koneksi(this);

        queryKas = "select *, strftime('%d-%m-%Y', tanggal) as tgl from transaksi";
        queryTotal = "select sum(jumlah), " +
                "(select sum(jumlah) from transaksi where status ='MASUK') as jlhMasuk," +
                "(select sum(jumlah) from transaksi where status ='KELUAR') as jlhKeluar " +
                "from transaksi";

        btnTambah = findViewById(R.id.btnTambah);
        btnTambah.setOnClickListener(v -> startActivity(new Intent(this, AddActivity.class)));

        swipeRefreshLayout.setOnRefreshListener(() -> {
            tampilKas();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (filter) {
            queryKas = "select *, strftime('%d-%m-%Y', tanggal) as tgl from transaksi " +
                    "where (tanggal >= '" + tglDari + "') AND (tanggal <= '" + tglSampai + "')";
            queryTotal = "select sum(jumlah), " +
                    "(select sum(jumlah) from transaksi where status='MASUK' AND (tanggal >= '" + tglDari + "') " +
                    "AND (tanggal <= '" + tglSampai + "')) as jlhMasuk, " +
                    "(select sum(jumlah) from transaksi where status='KELUAR' AND (tanggal >= '" + tglDari + "') " +
                    "AND (tanggal <= '" + tglSampai + "')) as jlhKeluar " +
                    "from transaksi";

            // Tampilkan textFilter dengan tanggal filter
            textFilter.setVisibility(View.VISIBLE);
            textFilter.setText("FILTER: " + tglDari + " SD " + tglSampai);

            filter = false; // Reset filter setelah tampilkan
        } else {
            textFilter.setVisibility(View.GONE); // Sembunyikan jika tidak ada filter
        }
        tampilKas();
    }


    private void tampilKas() {
        arusKas.clear();
        listTransaksi.setAdapter(null);
        SQLiteDatabase db = koneksi.getReadableDatabase();
        cursor = db.rawQuery(queryKas, null);

        while (cursor.moveToNext()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("transaksi_id", cursor.getString(0));
            map.put("status", cursor.getString(1));
            map.put("jumlah", cursor.getString(2));
            map.put("keterangan", cursor.getString(3));
            map.put("tanggal", cursor.getString(4));
            arusKas.add(map);
        }
        cursor.close();

        SimpleAdapter simple = new SimpleAdapter(this, arusKas, R.layout.list_item,
                new String[]{"transaksi_id", "status", "jumlah", "keterangan", "tanggal"},
                new int[]{R.id.textID, R.id.textStatus, R.id.textJumlah, R.id.textKeterangan, R.id.textTanggal});
        listTransaksi.setAdapter(simple);

        listTransaksi.setOnItemClickListener((parent, view, position, id) -> {
            transaksi_id = ((TextView) view.findViewById(R.id.textID)).getText().toString();
            tampilkanMenu();
        });

        tampilTotal();
    }

    private void tampilkanMenu() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.list_menu);
        dialog.getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
        dialog.show();
        TextView textMenuUbah = dialog.findViewById(R.id.textMenuUbah);
        TextView textMenuHapus = dialog.findViewById(R.id.textMenuHapus);

        textMenuHapus.setOnClickListener(v -> {
            dialog.dismiss();
            hapusData();
        });

        textMenuUbah.setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(this, EditActivity.class));
        });
    }

    private void hapusData() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Hapus Data?");
        alert.setMessage("Yakin mau hapus data ini?");
        alert.setPositiveButton("Ya", (dialog, which) -> {
            SQLiteDatabase db = koneksi.getWritableDatabase();
            db.execSQL("DELETE FROM transaksi WHERE transaksi_id = '" + transaksi_id + "'");
            Toast.makeText(this, "Data transaksi yang dipilih berhasil dihapus!",
                    Toast.LENGTH_SHORT).show();
            tampilKas();
        });
        alert.setNegativeButton("Tidak", null);
        alert.show();
    }

    private void tampilTotal() {
        SQLiteDatabase db = koneksi.getReadableDatabase();
        cursor = db.rawQuery(queryTotal, null);
        if (cursor.moveToFirst()) {
            textMasuk.setText(String.valueOf(cursor.getDouble(1)));
            textKeluar.setText(String.valueOf(cursor.getDouble(2)));
            double saldo = cursor.getDouble(1) - cursor.getDouble(2);
            textSaldo.setText(String.valueOf(saldo));
            swipeRefreshLayout.setRefreshing(false);

            // Menampilkan atau menyembunyikan textFilter sesuai dengan kondisi filter
            if (tglDari != null && tglSampai != null) {
                textFilter.setVisibility(View.VISIBLE);
                textFilter.setText("FILTER: " + tglDari + " SD " + tglSampai);
            } else {
                textFilter.setVisibility(View.GONE);
            }
        }
        cursor.close();
    }

}
