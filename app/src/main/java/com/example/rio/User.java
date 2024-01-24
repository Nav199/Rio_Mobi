
package com.example.rio;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class User implements Serializable {
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

    // Método estático para obter o usuário logado
    public static User getUsuarioLogado() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null) {
            // Usuário está logado, crie um objeto User com base nas informações do FirebaseUser
            String uid = firebaseUser.getUid();
            String nome = firebaseUser.getDisplayName();
            String email = firebaseUser.getEmail();

            // Você pode adicionar outras informações aqui, dependendo do que está disponível no FirebaseUser

            return new User(uid, email, nome, null, null, null);
        } else {
            // Nenhum usuário logado
            return null;
        }
    }
}
