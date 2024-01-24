package com.example.rio;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Denuncia {

    private String imageName;
    private String nomeEmbarcacao;
    private String tipoEmbarcacao;

    private String Motivo;

    // Construtor vazio necessário para o Firebase
    public Denuncia() {
    }

    public Denuncia(String imageName, String nomeEmbarcacao, String tipoEmbarcacao, String Motivo) {
        this.imageName = imageName;
        this.nomeEmbarcacao = nomeEmbarcacao;
        this.tipoEmbarcacao = tipoEmbarcacao;
        this.Motivo = Motivo;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getNomeEmbarcacao() {
        return nomeEmbarcacao;
    }

    public void setNomeEmbarcacao(String nomeEmbarcacao) {
        this.nomeEmbarcacao = nomeEmbarcacao;
    }

    public String getTipoEmbarcacao() {
        return tipoEmbarcacao;
    }

    public void setTipoEmbarcacao(String tipoEmbarcacao) {
        this.tipoEmbarcacao = tipoEmbarcacao;
    }
    private void setId_user(String idUser) {
    }

    public boolean Salvar() {
        try {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Denuncia");
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

}
