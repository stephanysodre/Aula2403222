package com.example.aula24_03_2_22.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {
    // declarar variavel
    private String nome;
    private String nascimento;
    private String altura;
    private int id = 0;
//variaveis publicas q apareceram
    public Personagem(String nome, String nascimento, String altura){
        this.nome = nome;
        this.nascimento = nascimento;
        this.altura = altura;
    }
    public Personagem(){

    }
    //setar nomes, nascimento, altura 

    public void setNome(String nome) {this.nome = nome;}

    public void setNascimento(String nascimento) {this.nascimento = nascimento;}

    public  void setAltura(String altura) {this.altura = altura;}
// pegar e retornar
    public String getNome () {return nome;}

    public String getNascimento() {return nascimento;}

    public String getAltura() {return altura;}

    @NonNull
    @Override //informa o q precisa sobrescrever
    public String toString() {return nome;}

    public void setId(int id) {this.id = id;}

    public  int getId() {return  id;}

    public boolean idValido(){return id > 0;}
}
