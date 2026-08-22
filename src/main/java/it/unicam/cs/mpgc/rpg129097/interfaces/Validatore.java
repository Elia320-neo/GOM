package it.unicam.cs.mpgc.rpg129097.interfaces;

public interface Validatore {
    String getNome();
    String getDescrizione();
    Proposizione valida(String intuzione);
}