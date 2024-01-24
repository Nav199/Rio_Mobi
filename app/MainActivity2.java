package com.example.rio;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
        TextView linkTextView = findViewById(R.id.textViewLogin);
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
            emailEditText = findViewById(R.id.Id_gmail);
            senhaEditText = findViewById(R.id.ID_SENHA);

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

            // Exemplo de leitura dos usuários após o cadastro
            // Isso é apenas um exemplo. Em um aplicativo real, você faria isso de forma assíncrona.
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

package com.example.rio;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class User {
    // Propriedades do Usuário
    private String nome;
    private String cpf;
    private String rg;
    private String telefone;
    private String email;
    private String senha;
    private String id_user; // Adicione esta propriedade para identificar cada usuário no banco de dados

    public User(String cpf, String email, String nome, String rg, String telefone, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    public User() {
        // Construtor vazio necessário para o Firebase
    }

    // Adicione getters e setters para todas as propriedades
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public boolean Salvar() {
        try {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Cadastro_user");
            String id_user = userRef.push().getKey();

            // Configurando o ID do usuário
            setId_user(id_user);

            // Salvando o usuário no banco de dados usando a chave única
            userRef.child(id_user).setValue(this);

            // Retornar true se a operação for bem-sucedida
            return true;
        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
            return false;
        }
    }

    // Método estático para ler usuários
    public static void LerUsuarios(ValueEventListener listener) {
        try {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Cadastro_user");
            userRef.addListenerForSingleValueEvent(listener);
        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
        }
    }

    // Método para atualizar um usuário
    public void Atualizar() {
        try {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Cadastro_user").child(getId_user());
            userRef.setValue(this);
        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
        }
    }

    // Método para excluir um usuário
    public void Excluir() {
        try {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Cadastro_user").child(getId_user());
            userRef.removeValue();
        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
        }
    }
}
