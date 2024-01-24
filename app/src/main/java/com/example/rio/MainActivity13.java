package com.example.rio;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity13 extends AppCompatActivity {

    private Button comprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);

        // Inicialize o botão Comprar
        comprar = findViewById(R.id.id_cadastrar_);

        // Recupere dados do Intent
        Intent intent = getIntent();
        String nome = intent.getStringExtra("Nome_user");
        int preco = intent.getIntExtra("preco", -1);
        String forma = intent.getStringExtra("forma");
        String Nome_Barco = intent.getStringExtra("Nome_barco");
        String nome_user_cadastro = intent.getStringExtra("Nome_user_cadastro");

        // Atualize sua interface do usuário com os dados recebidos
        TextView nomeTextView = findViewById(R.id.id_nome_Passageiro);
        TextView nomeBarcoTextView = findViewById(R.id.id_nome_embarcacao);
        TextView precoTextView = findViewById(R.id.id_preco);
        TextView formaTextView = findViewById(R.id.id_nome_pagamento);

        if (nome_user_cadastro != null && !nome_user_cadastro.isEmpty()) {
            nomeTextView.setText("Nome do Passageiro: " + nome_user_cadastro);
        } else {
            nomeTextView.setText("Nome do Passageiro: " + nome);
        }

        nomeBarcoTextView.setText("Nome da Embarcação: " + Nome_Barco);
        precoTextView.setText("Preço: " + (preco != -1 ? String.valueOf(preco) : "Preço indisponível"));
        formaTextView.setText("Forma de pagamento: " + forma);

        // Crie um objeto 'compra' e salve no banco de dados
        compra compra = new compra(nome_user_cadastro, Nome_Barco, preco, forma);
        compra.Salvar();

        // Defina um OnClickListener para o botão Comprar
        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity13.this, MainActivity9.class);
                startActivity(intent);
            }
        });
    }
}
