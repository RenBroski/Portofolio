package com.example.intent_reno;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void kirim1(View v){

        String url = "https://komiku.id/";
        Intent link = new Intent(Intent.ACTION_VIEW);
        link.setData(Uri. parse(url));
        startActivity(link);
    }

    public void kirim2(View v){

        String url = "https://www.youtube.com/";
        Intent link = new Intent(Intent.ACTION_VIEW);
        link.setData(Uri. parse(url));
        startActivity(link);
    }

    public void kirim3(View v){

        String url = "https://www.instagram.com/";
        Intent link= new Intent(Intent.ACTION_VIEW);
        link.setData(Uri. parse(url));
        startActivity(link);
    }

    public void kirim4(View v){
        Intent link = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(link);
    }

    public void kirim5(View v){

        String url = "https://www.canva.com/";
        Intent link = new Intent(Intent.ACTION_VIEW);
        link.setData(Uri. parse(url));
        startActivity(link);
    }

    public void kirim6(View v){

        String url = "https://chatgpt.com/";
        Intent link = new Intent(Intent.ACTION_VIEW);
        link.setData(Uri. parse(url));
        startActivity(link);
    }

    public void kirim7(View v){

        String url = "http://192.168.1.1/";
        Intent link = new Intent(Intent.ACTION_VIEW);
        link.setData(Uri. parse(url));
        startActivity(link);
    }


}