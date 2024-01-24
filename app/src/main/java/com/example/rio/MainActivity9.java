package com.example.rio;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class MainActivity9 extends AppCompatActivity {

    private Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        ImageView imageViewGif = findViewById(R.id.imageViewGif);

        // Carrega o GIF usando Glide
        Glide.with(this)
                .asGif()
                .load(R.drawable.rio) // Corrija para o nome real do seu arquivo GIF (no caso, "rio.gif")
                .into(imageViewGif);

        // Home
        home = findViewById(R.id.id_retornar2);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity9.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
