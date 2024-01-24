package com.example.rio;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity6 extends AppCompatActivity {

    private EditText destinoEdite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        Button btnActivity7 = findViewById(R.id.btn_activity7);
        btnActivity7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesquisar();
            }
        });
    }

    private void pesquisar() {
        //pegando o nome do usuário
        Intent i = getIntent();
        String nome = i.getStringExtra("nome");
        String Nome = i.getStringExtra("Nome_user_cadastro");
        Log.d("nome",nome);
        //

        destinoEdite = findViewById(R.id.id_destino);

        String destino = destinoEdite.getText().toString();

        if (destino.equals("Tefé") || destino.equals("Parintins") || destino.equals("Itacoatiara") ||
                destino.equals("Coari") || destino.equals("Manacapuru") || destino.equals("Anori")) {
            Toast.makeText(MainActivity6.this, "Cidades Cadastradas", Toast.LENGTH_SHORT).show();

            // Adicionando um log para verificar se o destino está correto
            Log.d("Destino", destino);

            Intent intent = new Intent(MainActivity6.this, MainActivity11.class);
           intent.putExtra("destino", destino);
           intent.putExtra("Nome",nome);
           intent.putExtra("Nome_User",Nome);
            startActivity(intent);
        }
    }
}

