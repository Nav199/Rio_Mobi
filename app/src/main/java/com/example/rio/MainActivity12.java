package com.example.rio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity12 extends AppCompatActivity {

    private LinearLayout layoutPix;
    private LinearLayout layoutCredito;
    private LinearLayout layoutDebito;

    private Button button_home;

    private Button button_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);

        layoutPix = findViewById(R.id.id_linear_pix);
        layoutCredito = findViewById(R.id.id_linear_credito);
        layoutDebito = findViewById(R.id.id_linear_debito);

        RadioButton radioButtonPix = findViewById(R.id.radioButton3);
        RadioButton radioButtonCredito = findViewById(R.id.id_credito);
        RadioButton radioButtonDebito = findViewById(R.id.id_debito);

        radioButtonPix.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showLayout(layoutPix);
            }
        });

        radioButtonCredito.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showLayout(layoutCredito);
            }
        });

        radioButtonDebito.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showLayout(layoutDebito);
            }
        });

        // Botão para voltar para home
        button_home = findViewById(R.id.id_return);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity12.this, MainActivity.class);
                startActivity(intent);
            }
        });
        // colocando o valor do preço
        Intent intent = getIntent();
        int preco = intent.getIntExtra("preco", -1);

        TextView preco_pago = findViewById(R.id.textView19);

        preco_pago.setText("Valor a ser pago: " + (preco != -1 ? String.valueOf(preco) : "Preço indisponível"));

        //colocando o nome da embarcação

        Intent nome_emba = getIntent();
        String nome_barco = nome_emba.getStringExtra("nome");

        //pegando o nome do usuário

        Intent nome_user = getIntent();
        String user = nome_user.getStringExtra("Nome_user");

        Intent nome_user_cadastro = getIntent();
        String nome_cadastro = nome_user_cadastro.getStringExtra("Nome_user_cadastro");


        // Botão para adicionar forma
        button_add = findViewById(R.id.id_ad_forma);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = getCheckedRadioButtonId();
                String formaPagamento = "";

                if (selectedId == R.id.radioButton3) {
                    formaPagamento = "Pix";
                } else if (selectedId == R.id.id_credito) {
                    formaPagamento = "Crédito";
                } else if (selectedId == R.id.id_debito) {
                    formaPagamento = "Débito";
                } else {
                    // Nenhuma opção selecionada
                    Toast.makeText(MainActivity12.this, "Selecione uma opção", Toast.LENGTH_SHORT).show();
                    return;
                }
                Pagamento pagamento = new Pagamento(formaPagamento);
                // Salve a forma de pagamento no Firebase
                if (pagamento.Salvar()) {
                    Toast.makeText(MainActivity12.this, "Forma de pagamento salva com sucesso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity12.this, MainActivity13.class);
                    intent.putExtra("preco",preco);
                    intent.putExtra("forma", formaPagamento);
                    intent.putExtra("Nome_barco", nome_barco); // Adicione isso
                    intent.putExtra("Nome_user",user);
                    intent.putExtra("Nome_user_cadastro",nome_cadastro);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity12.this, "Erro ao salvar forma de pagamento", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void showLayout(LinearLayout layoutToShow) {
        layoutPix.setVisibility(View.GONE);
        layoutCredito.setVisibility(View.GONE);
        layoutDebito.setVisibility(View.GONE);

        layoutToShow.setVisibility(View.VISIBLE);
    }

    // Método para lidar com o clique no botão (pode ser chamado no XML do botão)
    public void onConfirmClick(View view) {
        int selectedId = getCheckedRadioButtonId();

        if (selectedId == R.id.radioButton3) {
            handlePix();
        } else if (selectedId == R.id.id_credito) {
            handleCredito();
        } else if (selectedId == R.id.id_debito) {
            handleDebito();
        } else {
            // Nenhuma opção selecionada
            Toast.makeText(this, "Selecione uma opção", Toast.LENGTH_SHORT).show();
        }
    }

    private int getCheckedRadioButtonId() {
        RadioButton radioButtonPix = findViewById(R.id.radioButton3);
        RadioButton radioButtonCredito = findViewById(R.id.id_credito);
        RadioButton radioButtonDebito = findViewById(R.id.id_debito);

        if (radioButtonPix.isChecked()) {
            return R.id.radioButton3;
        } else if (radioButtonCredito.isChecked()) {
            return R.id.id_credito;
        } else if (radioButtonDebito.isChecked()) {
            return R.id.id_debito;
        } else {
            return View.NO_ID;
        }
    }

    private void handlePix() {
        String codigoPix = generateRandomPixCode();
        Toast.makeText(this, "Código Pix: " + codigoPix, Toast.LENGTH_SHORT).show();
    }

    private void handleCredito() {
        EditText editTextNumero = findViewById(R.id.editTextNumeroCredito);
        EditText editTextData = findViewById(R.id.editTextDataCredito);
        EditText ediCvv = findViewById(R.id.CVV_credito);

        String numeroCartao = editTextNumero.getText().toString();
        String dataExpiracao = editTextData.getText().toString();
        String cvv = ediCvv.getText().toString();
        Toast.makeText(this, "Número: " + numeroCartao + ", Data de Expiração: " + dataExpiracao + ", CVV: "+cvv, Toast.LENGTH_SHORT).show();
    }

    private void handleDebito() {
        EditText editTextNumero = findViewById(R.id.editTextNumeroDebito);
        EditText editTextData = findViewById(R.id.editTextDataDebito);
        EditText edicvv = findViewById(R.id.CVV);
        String numeroCartao = editTextNumero.getText().toString();
        String dataExpiracao = editTextData.getText().toString();
        String cvv = edicvv.getText().toString();
        Toast.makeText(this, "Número: " + numeroCartao + ", Data de Expiração: " + dataExpiracao + ", CVV: "+cvv, Toast.LENGTH_SHORT).show();
    }

    private String generateRandomPixCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // Gera um código de 6 dígitos
        return String.valueOf(code);
    }
}
