package com.example.rio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity7 extends AppCompatActivity {
    private EditText nomeEditText;
    private Spinner tipoSpinner;
    private EditText corEditText;
    private EditText lotacaoEditText;
    private EditText precoEditText;

    private EditText destinoEditText;
    private Spinner spinnerTipoVeiculo;

    private Button Salvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        spinnerTipoVeiculo = findViewById(R.id.spinnerTipoVeiculo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.tipos_veiculo_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoVeiculo.setAdapter(adapter);


        //Botão Salvar
        Salvar = findViewById(R.id.id_Salvar_embar);
        Salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });
    }

    private void cadastrar() {
        try {
            nomeEditText = findViewById(R.id.Nome_embarca_);
            corEditText = findViewById(R.id.cor_embarca_);
            lotacaoEditText = findViewById(R.id.capacidade_id);
            precoEditText = findViewById(R.id.id_preco_embar);
            destinoEditText = findViewById(R.id.destino_id);
            String nome = nomeEditText.getText().toString();
            String cor = corEditText.getText().toString();
            String lotacaoString = lotacaoEditText.getText().toString();
            String preco_ = precoEditText.getText().toString();
            String destino = destinoEditText.getText().toString();
            if (nome.isEmpty() || cor.isEmpty() || lotacaoString.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos obrigatórios.", Toast.LENGTH_SHORT).show();
                return;
            }

            int lotacao = Integer.parseInt(lotacaoString);
            int preco = Integer.parseInt(preco_);
            String tipoVeiculo = spinnerTipoVeiculo.getSelectedItem().toString();

            Embarcacao embarcacao = new Embarcacao(nome, tipoVeiculo, lotacao, cor, preco,destino);
            if (embarcacao.Salvar()) {
                Toast.makeText(this, "Embarcação salva com sucesso.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity7.this, MainActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(this, "Falha ao salvar a embarcação.", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Log.e("TAG", "Erro ao converter lotacao para inteiro.");
            Toast.makeText(this, "Erro ao converter lotação para inteiro.", Toast.LENGTH_SHORT).show();
        }
    }

}