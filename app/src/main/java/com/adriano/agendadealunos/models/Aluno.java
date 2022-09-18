package com.adriano.agendadealunos.models;

import java.io.Serializable;

public class Aluno implements Serializable {

    private final String nome;
    private final String telefone;
    private final String email;
    private long id;

    public Aluno(String nome, String telefone, String email) {
        this.nome  = nome;
        this.telefone=telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id){
        if(id > 0){
            this.id = id;
        }

    }

    public long getId(){
        return id;
    }

    @Override
    public String toString() {
        return nome;
    }
}
