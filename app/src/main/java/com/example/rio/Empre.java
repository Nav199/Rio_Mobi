package com.example.rio;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class Empre {
    private String cnpj;
    private String contaBancaria;
    private String email;
    private String senha;
    private String telefone;
    private String id_empre;

    //Constructor para o firebase

    public Empre(){
        // Construtor vazio necessário para o Firebase
    }

    // Construtor
    public Empre(String cnpj, String contaBancaria, String email, String senha, String telefone) {
        this.cnpj = cnpj;
        this.contaBancaria = contaBancaria;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    // Getters e Setters para CNPJ
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    // Getters e Setters para Conta Bancaria
    public String getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(String contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    // Getters e Setters para Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getters e Setters para Senha
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Getters e Setters para Telefone
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    private void setId_user(String idEmpre) {
        this.id_empre = id_empre;
    }
    private String getId_user() {
        return id_empre;
    }

    public boolean Salvar() {
        try {
            DatabaseReference EmpreRef = FirebaseDatabase.getInstance().getReference().child("Empre_Barco");
            String id_empre = EmpreRef.push().getKey();

            // Configurando o ID do usuário
            setId_user(id_empre);

            // Salvando o usuário no banco de dados usando a chave única
            EmpreRef.child(id_empre).setValue(this);

            // Retornar true se a operação for bem-sucedida
            return true;
        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
            return false;
        }
    }
    public static void LerUsuarios(ValueEventListener listener) {
        try {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Empre_Barco");
            userRef.addListenerForSingleValueEvent(listener);
        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
        }
    }

    // Método para atualizar um usuário
    public void Atualizar() {
        try {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Empre_Barco").child(getId_user());
            userRef.setValue(this);
        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
        }
    }


    // Método para excluir um usuário
    public void Excluir() {
        try {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Empre_Barco").child(getId_user());
            userRef.removeValue();
        } catch (Exception e) {
            // Lidar com exceções, se ocorrerem
            e.printStackTrace();
        }
    }
}