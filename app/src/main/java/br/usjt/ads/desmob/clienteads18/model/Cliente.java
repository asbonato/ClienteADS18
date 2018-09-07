package br.usjt.ads.desmob.clienteads18.model;

import java.io.Serializable;

public class Cliente implements Serializable {
    private int id;
    private String nome, fone, email;

    public Cliente(int id, String nome, String fone, String email) {
        this.id = id;
        this.nome = nome;
        this.fone = fone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFigura(){
        String figura = email.replace('@', '_');
        return figura.replace('.','_');
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", fone='" + fone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}












