package com.example.rio;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {
    private EditText nomeEditText;
    private EditText cpfEditText;
    private EditText rgEditText;
    private EditText telefoneEditText;
    private EditText emailEditText;
    private EditText senhaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // IDs diversos
        TextView linkTextView = findViewById(R.id.N_Cadastre);
        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        TextView link_principal = findViewById(R.id.Return_principal_pass);

        // Links para outras telas
        linkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity5.class);
                startActivity(intent);
            }
        });

        link_principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Ação para ir para a tela de pesquisar passagem
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });
    }

    private void cadastrar() {
        try {
            // Obter valores dos campos de entrada
            nomeEditText = findViewById(R.id.Id_Nome);
            cpfEditText = findViewById(R.id.Id_Cpf);
            rgEditText = findViewById(R.id.Id_Rg);
            telefoneEditText = findViewById(R.id.Num_tele);
            emailEditText = findViewById(R.id.Id_cnpj);
            senhaEditText = findViewById(R.id.ID_Senha_empre);

            String nome = nomeEditText.getText().toString();
            String cpf = cpfEditText.getText().toString();
            String rg = rgEditText.getText().toString();
            String telefone = telefoneEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String senha = senhaEditText.getText().toString();

            // Verificar se algum campo obrigatório está vazio
            if (nome.isEmpty() || cpf.isEmpty() || rg.isEmpty() || telefone.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                // Exibir mensagem de erro ou lidar com o caso de campos vazios
                Log.e("TAG", "Preencha todos os campos obrigatórios.");
                return;
            }

            // Restante do código para salvar o usuário
            User user = new User(cpf, email, nome, rg, telefone, senha);
            boolean saveResult = user.Salvar();

            // Dentro do método cadastrar() após salvar o usuário com sucesso
            Toast.makeText(MainActivity2.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

            //Indo para outra Activit
            //pegando ifd do usuário

            Intent intent = new Intent(MainActivity2.this, MainActivity5.class);
            intent.putExtra("Nome_user_cadastro",nome);
            startActivity(intent);


            ValueEventListener userListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        User user = userSnapshot.getValue(User.class);
                        // Faça algo com o usuário lido, por exemplo, exiba no log
                        Log.d("TAG", "Usuário lido: " + user.getNome());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Lidar com erros de leitura
                    Log.e("TAG", "Erro ao ler usuários: " + databaseError.getMessage());
                }
            };

            // Para ler os usuários, chame o método estático da classe User
            User.LerUsuarios(userListener);
        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
            Log.e("TAG", "Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
}