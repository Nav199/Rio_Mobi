package com.example.rio;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pagamento {


    String forma;

    String id_user;


    public Pagamento(String forma){
        this.forma = forma;
    }
    public Pagamento(){} //Constructor para o Firebase



    public boolean Salvar() {
        try {
            DatabaseReference pagamentoRef = FirebaseDatabase.getInstance().getReference().child("pagamento");
            String idPagamento = pagamentoRef.push().getKey();

            // Configurando o ID do pagamento
            setId_pagamento(idPagamento);
            // Salvando o pagamento no banco de dados usando a chave única
            pagamentoRef.child(idPagamento).setValue(this);

            // Retornar true se a operação for bem-sucedida
            return true;
        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
            return false;
        }
    }



    // Método estático para ler usuários
    public static void LerPagamento(ValueEventListener listener) {
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
    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }


    private void setId_pagamento(String idPagamento) {
    }

}
