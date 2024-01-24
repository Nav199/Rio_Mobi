// MainActivity11.java
package com.example.rio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity11 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BarcoAdapter barcoAdapter;
    private List<Embarcacao> barcos;

    private String destino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        Intent intent = getIntent();
        destino = intent.getStringExtra("destino");

        barcos = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        barcoAdapter = new BarcoAdapter(barcos);
        recyclerView.setAdapter(barcoAdapter);


        //Pegando o nome do usuário
        Intent i = getIntent();
        String nome_user = i.getStringExtra("Nome");
        String nome_user_cadastro = i.getStringExtra("Nome_User");
        barcoAdapter.setOnItemClickListener(new BarcoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Embarcacao barco) {
                Intent intent12 = new Intent(MainActivity11.this, MainActivity12.class);
                intent12.putExtra("nome", barco.getNome());
                intent12.putExtra("tipo", barco.getTipo());
                intent12.putExtra("capacidade", barco.getCapacidade());
                intent12.putExtra("cor", barco.getCor());
                intent12.putExtra("preco", barco.getPreco());
                intent12.putExtra("Nome_user",nome_user);
                intent12.putExtra("Nome_user_cadastro",nome_user_cadastro);
                startActivity(intent12);
            }
        });


        lerEmbarcacoes();
    }

    private void lerEmbarcacoes() {
        Query query = FirebaseDatabase.getInstance().getReference().child("Embarcacao").orderByChild("destino").equalTo(destino);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                barcos.clear();
                for (DataSnapshot dados : snapshot.getChildren()) {
                    Embarcacao ema = dados.getValue(Embarcacao.class);
                    if (ema != null) {
                        barcos.add(ema);
                    }
                }
                if (barcos.isEmpty()) {
                    barcos.add(new Embarcacao("Não há viagens", "", 0, "", 0,null));
                }
                barcoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Erro ao listar" + error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
