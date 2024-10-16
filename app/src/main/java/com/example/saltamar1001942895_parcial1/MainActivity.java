package com.example.saltamar1001942895_parcial1;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnGestionMesas = findViewById(R.id.btnGestionMesas);
        btnGestionMesas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Producto_Activity.class);
            startActivity(intent);
        });

        Button btnConsultaVentas = findViewById(R.id.btnConsultaVentas);
        btnConsultaVentas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Consulta_Activity.class);
            startActivity(intent);
        });


        Button btnEnglish = findViewById(R.id.btnEnglish);
        Button btnPortuguese = findViewById(R.id.btnPortuguese);
        Button btnSpanish = findViewById(R.id.btnSpanish);

        btnEnglish.setOnClickListener(v -> changeLanguage("en"));
        btnPortuguese.setOnClickListener(v -> changeLanguage("pt"));
        btnSpanish.setOnClickListener(v -> changeLanguage("es"));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void changeLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}