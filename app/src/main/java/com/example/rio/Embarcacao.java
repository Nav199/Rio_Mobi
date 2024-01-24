package com.example.rio;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Embarcacao {

    private String cor;
    private String id_empresa; // Id da empresa que está cadastrando a embarcação
    private String nome;
    private String tipo;
    private int capacidade; // Alterado para int
    private List<String> cidadesAtendidas;
    private String id_embar;
    private int preco;
    private String destino;

    // Construtor padrão vazio
    public Embarcacao(String nome, String tipo, int capacidade, String cor, int preco,String destino) {
        this.nome = nome;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.cor = cor;
        this.preco = preco;
        this.destino = destino;
    }

    public Embarcacao() {
        // Construtor vazio necessário para o Firebase
    }

    public void setId_embar(String id_user) {
        this.id_embar = id_user;
    }

    private String getId_user() {
        return id_embar;
    }

    public void setId_empresa(String id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getId_empresa() {
        return id_empresa;
    }

    public String getNome() {
        return this.nome;
    }

    public String getTipo() {
        return this.tipo;
    }

    public int getCapacidade() {
        return this.capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getCor() {
        return this.cor;
    }

    public int getPreco() {
        return this.preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }
    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public boolean Salvar() {
        try {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Embarcacao");
            String id_embarca = userRef.push().getKey();

            // Configurando o ID do usuário
            setId_embar(id_embarca);

            // Adicionando logs para depuração
            Log.d("TAG", "Dados antes de salvar: Nome=" + nome + ", Tipo=" + tipo + ", Capacidade=" + capacidade + ", Cor=" + cor + ", Cidades=" + cidadesAtendidas);

            // Salvando o usuário no banco de dados usando a chave única
            userRef.child(id_embarca).setValue(this);

            // Retornar true se a operação for bem-sucedida
            return true;
        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
            return false;
        }
    }

    public static void Ler_Embarcacoes(ValueEventListener listener) {
        try {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Embarcacao");
            userRef.addListenerForSingleValueEvent(listener);
        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
        }
    }

    public void Atualizar() {
        try {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Embarcacao").child(getId_user());
            userRef.setValue(this);
        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
        }
    }

    public void Excluir() {
        try {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Embarcacao").child(getId_user());
            userRef.removeValue();
        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
        }
    }
}
