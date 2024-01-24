package com.example.rio;

import androidx.annotation.NonNull;
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

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity4 extends AppCompatActivity {

    private EditText editTextCnpj;
    private EditText editTextSenha;

    private FirebaseDatabase firebaseDatabase; // Mova a instância para cá

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        TextView linkTextView = findViewById(R.id.N_Cadastre);
        TextView linkTextView_2 = findViewById(R.id.textViewLogin2);
        TextView link_return = findViewById(R.id.Return_empre);
        Button button_login = findViewById(R.id.Id_entrar_button);
        link_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity4.this, MainActivity.class);
                startActivity(intent);
            }
        });

        linkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        linkTextView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_2 = new Intent(MainActivity4.this, MainActivity8.class);
                startActivity(intent_2);
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificar_Login_empre();
            }
        });

    }

    private void verificar_Login_empre() {
        // Obtendo os dados do ID
        editTextCnpj = findViewById(R.id.Id_cnpj);
        editTextSenha = findViewById(R.id.ID_Senha_empre);

        String cnpj = editTextCnpj.getText().toString();
        String senha = editTextSenha.getText().toString();

        Empre empresaRef = new Empre();
        empresaRef.LerUsuarios(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // O método onDataChange é chamado quando os dados são lidos com sucesso
                List<Empre> userList = new ArrayList<>();

                // Iterar sobre os nós filho (cada nó representa um usuário)
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Obter dados do usuário
                    Empre empre = userSnapshot.getValue(Empre.class);

                    // Adicionar usuário à lista
                    userList.add(empre);

                    // Verificar se o usuário está cadastrado
                    if (empre.getCnpj().equals(cnpj) && empre.getSenha().equals(senha)) {
                        // O usuário está cadastrado, mostrar aviso
                        Toast.makeText(MainActivity4.this, "Usuário encontrado!", Toast.LENGTH_SHORT).show();

                        // Atrasar o redirecionamento por 2 segundos (2000 milissegundos)
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Redirecionar para a próxima atividade após o atraso
                                Intent intent = new Intent(MainActivity4.this, MainActivity7.class);
                                startActivity(intent);

                                // Opcional: encerrar esta atividade para que o usuário não volte aqui pressionando o botão de voltar
                                finish();
                            }
                        }, 2000); // 2000 milissegundos = 2 segundos de atraso

                        return; // Sair do loop, pois já encontramos o usuário
                    }
                }

                // Se chegou aqui, o usuário não foi encontrado
                Log.d("ConsultaUsuarios", "Usuário não encontrado");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}