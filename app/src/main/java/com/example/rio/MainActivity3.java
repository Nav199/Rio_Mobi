package com.example.rio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    private EditText CNPJEditText;
    private EditText TelefoneEditText;
    private EditText EmailEditText;
    private EditText telefoneEditText;
    private EditText SenhalEditText;
    private EditText Nu_ContaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button btnSalvar = findViewById(R.id.id_cadastrar_);
        TextView textViewDirecionar = findViewById(R.id.textView9);
        TextView textView10 = findViewById(R.id.textView10);
        textViewDirecionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                startActivity(intent);
            }
        });
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cadastrar_Empre();
                Intent i = new Intent(MainActivity3.this, MainActivity7.class);
                startActivity(i);
            }
        });
        textView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void Cadastrar_Empre() {
        try {
            CNPJEditText = findViewById(R.id.Id_cnpj);
            TelefoneEditText = findViewById(R.id.Id_telefone);
            EmailEditText = findViewById(R.id.id_Email_);
            SenhalEditText = findViewById(R.id.editTextText7);
            Nu_ContaEditText = findViewById(R.id.editTextText3);

            String cnpj = CNPJEditText.getText().toString();
            String telefone = TelefoneEditText.getText().toString();
            String Email = EmailEditText.getText().toString();
            String senha = SenhalEditText.getText().toString();
            String Numero_Conta = Nu_ContaEditText.getText().toString();

            // Verificar se algum campo obrigatório está vazio
            if (cnpj.isEmpty() || telefone.isEmpty() || Email.isEmpty() || telefone.isEmpty() || Numero_Conta.isEmpty() || senha.isEmpty()) {
                // Exibir mensagem de erro ou lidar com o caso de campos vazios
                Log.e("TAG", "Preencha todos os campos obrigatórios.");
                return;
            }
            Empre empresa = new Empre(cnpj, Numero_Conta, Email, senha, telefone);
            boolean save = empresa.Salvar();
            // Dentro do método cadastrar() após salvar o usuário com sucesso
            Toast.makeText(MainActivity3.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
            Log.e("TAG", "Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
}
