package it.unicam.cs.mpgc.rpg129097.model;


import lombok.Getter;
import lombok.NonNull;

public abstract class Personaggio {
    @Getter
    protected String nome;
    @Getter
    protected String professione;


    protected Personaggio(@NonNull String nome, @NonNull String professione){
        this.nome = nome;
        this.professione = professione;
    }

    public abstract void presentati();

}
