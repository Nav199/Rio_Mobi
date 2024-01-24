package com.example.rio;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity5 extends AppCompatActivity {
    private EditText editTextNome;
    private EditText editTextSenha;
    private Button buttonLogin;
    private FirebaseDatabase firebaseDatabase; // Mova a instância para cá

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        firebaseDatabase = FirebaseDatabase.getInstance();

        buttonLogin = findViewById(R.id.Id_entrar_button);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Verificar_Login();
            }
        });

        // link das outras páginas

        TextView tela_cadastro = findViewById(R.id.N_Cadastre); // link para tela de cadastro
        tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity5.this, MainActivity2.class);
                startActivity(i);
            }
        });

        TextView tela_esqueceu_senha = findViewById(R.id.esque_senha);
        tela_esqueceu_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity5.this, MainActivity8.class);
                startActivity(i);
            }
        });

        TextView tela_disk = findViewById(R.id.id_disk_de);
        tela_disk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity5.this, MainActivity14.class);
                startActivity(i);
            }
        });

    }

    private void Verificar_Login() {
        // Obtendo dados dos IDs
        editTextNome = findViewById(R.id.Id_cnpj);
        editTextSenha = findViewById(R.id.ID_Senha_empre);
        String nome = editTextNome.getText().toString();
        String senha = editTextSenha.getText().toString();

        // Criando instância com a classe User
        User userRef = new User();

        // Chamando a classe LerUsuarios
        userRef.LerUsuarios(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // O método onDataChange é chamado quando os dados são lidos com sucesso
                List<User> userList = new ArrayList<>();

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Obter dados do usuário
                    User user = userSnapshot.getValue(User.class);

                    // Adicionar usuário à lista
                    userList.add(user);

                    // Verificar se o usuário está cadastrado
                    if (user.getNome().equals(nome) && user.getSenha().equals(senha)) {
                        // O usuário está cadastrado, mostrar aviso
                        Toast.makeText(MainActivity5.this, "Usuário encontrado!", Toast.LENGTH_SHORT).show();


                        // Atrasar o redirecionamento por 2 segundos (2000 milissegundos)
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Redirecionar para a próxima atividade após o atraso
                                Intent intent = new Intent(MainActivity5.this, MainActivity6.class);
                                intent.putExtra("nome",nome);
                                startActivity(intent);

                                // Opcional: encerrar esta atividade para que o usuário não volte aqui pressionando o botão de voltar
                                finish();
                            }
                        }, 2000); // 2000 milissegundos = 2 segundos de atraso

                        return; // Sair do loop, pois já encontramos o usuário
                    }
                }

                // Se chegou aqui, o usuário não foi encontrado
                Toast.makeText(MainActivity5.this, "Usuário não encontrado!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Lidar com erro de leitura do Firebase
                Log.e("ConsultaUsuarios", "Erro ao ler usuários do Firebase", databaseError.toException());
            }
        });
    }
}