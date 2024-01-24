package com.example.rio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity14 extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imageViewCapturada;
    private EditText editTextNomeEmbarcacao;
    private EditText editTextTipoEmbarcacao;
    private TextView textViewAvisoCamera;

    private EditText editTextTipoEmbarcacao2;
    private Bitmap imagemCapturada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);

        // Inicializar as Views
        imageViewCapturada = findViewById(R.id.imageViewCapturada);
        editTextNomeEmbarcacao = findViewById(R.id.editTextNomeEmbarcacao);
        editTextTipoEmbarcacao = findViewById(R.id.editTextTipoEmbarcacao);
        editTextTipoEmbarcacao2 = findViewById(R.id.editTextTipoEmbarcacao2);
        textViewAvisoCamera = findViewById(R.id.textView_aviso_camera);

        // Configurar o OnClickListener para o botão de voltar
        Button buttonVoltar = findViewById(R.id.button_voltar);
        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity14.this, MainActivity5.class);
                startActivity(i);
            }
        });

        // Configurar o OnClickListener para o botão de salvar
        Button buttonSalvar = findViewById(R.id.button_salvar);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarDenuncia();
            }
        });
    }

    private void salvarDenuncia() {
        String nomeEmbarcacao = editTextNomeEmbarcacao.getText().toString().trim();
        String tipoEmbarcacao = editTextTipoEmbarcacao.getText().toString().trim();
        String motivo = editTextTipoEmbarcacao2.getText().toString().trim();

        if (nomeEmbarcacao.isEmpty() || tipoEmbarcacao.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            DatabaseReference denunciaRef = FirebaseDatabase.getInstance().getReference().child("Denuncia");
            String idDenuncia = denunciaRef.push().getKey();

            Denuncia denuncia = new Denuncia(idDenuncia, nomeEmbarcacao, tipoEmbarcacao,motivo);
            denunciaRef.child(idDenuncia).setValue(denuncia);



            Toast.makeText(this, "Denúncia feita com sucesso!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Erro ao salvar a denúncia.", Toast.LENGTH_SHORT).show();
        }
    }

}
