package com.example.rio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ver_dados_empresa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_dados_empresa);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Empre_Barco");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String cnpj = snapshot.child("cnpj").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);
                    String telefone = snapshot.child("telefone").getValue(String.class);
                    String conta = snapshot.child("contaBancaria").getValue(String.class);

                    // Verifique se os valores não são nulos antes de definir nos TextViews
                    if (cnpj != null) {
                        TextView cnpjTextView = findViewById(R.id.textView13);
                        cnpjTextView.setText(cnpj);
                    }

                    if (email != null) {
                        TextView emailTextView = findViewById(R.id.textView14);
                        emailTextView.setText(email);
                    }

                    if (telefone != null) {
                        TextView telefoneTextView = findViewById(R.id.textView15);
                        telefoneTextView.setText(telefone);
                    }

                    if (conta != null) {
                        TextView contaTextView = findViewById(R.id.textView16);
                        contaTextView.setText(conta);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Trate erros aqui
                Log.e("Firebase", "Erro ao ler dados do Firebase", databaseError.toException());
            }
        });

        Button delete = findViewById(R.id.delete_empre);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Empre empresa = new Empre();
                empresa.Excluir();
                // Dentro do método cadastrar() após salvar o usuário com sucesso
                Toast.makeText(ver_dados_empresa.this, "Excluido sucesso!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ver_dados_empresa.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

}