package com.example.rio;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class compra {

    private String nome_passagueiro;

   private int preco_pass;

   private String nome_embarcacao;

   private String forma_pagamento;
    private String idUser;

    public compra(){} // para o firebase

    public compra(String nome_passagueiro, String nome_embarcacao, int preco_pass, String forma_pagamento){
        this.nome_passagueiro = nome_passagueiro;
        this.nome_embarcacao = nome_embarcacao;
        this.forma_pagamento = forma_pagamento;
        this.preco_pass = preco_pass;
    }


    public boolean Salvar() {
        try {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Compra");
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

    private void setId_user(String idUser) {
        this.idUser = idUser;
    }
    private String getId_user() {
        return idUser;
    }
    // Método estático para ler usuários
    public static void LerUsuarios(ValueEventListener listener) {
        try {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Compra");
            userRef.addListenerForSingleValueEvent(listener);
        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
        }
    }

    // Método para atualizar um usuário
    public void Atualizar() {
        try {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Compra").child(getId_user());
            userRef.setValue(this);
        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
        }
    }


    // Método para excluir um usuário
    public void Excluir() {
        try {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Compra").child(getId_user());
            userRef.removeValue();
        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
        }
    }

    public String getNome_passagueiro() {
        return nome_passagueiro;
    }

    public void setNome_passagueiro(String nome_passagueiro) {
        this.nome_passagueiro = nome_passagueiro;
    }

    public int getPreco_pass() {
        return preco_pass;
    }

    public void setPreco_pass(int preco_pass) {
        this.preco_pass = preco_pass;
    }

    public String getNome_embarcacao() {
        return nome_embarcacao;
    }

    public void setNome_embarcacao(String nome_embarcacao) {
        this.nome_embarcacao = nome_embarcacao;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }
}
