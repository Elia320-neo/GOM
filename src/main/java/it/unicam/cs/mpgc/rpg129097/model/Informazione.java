package it.unicam.cs.mpgc.rpg129097.model;

import it.unicam.cs.mpgc.rpg129097.interfaces.Proposizione;
import lombok.NonNull;


public class Informazione implements Proposizione {
    private final String descrizione;
    private final boolean esito;

    public Informazione(@NonNull String descrizione, boolean esito) {
        this.descrizione = descrizione;
        this.esito = esito;
    }

    @Override
    public String getDescrizione() { return descrizione; }

    @Override
    public boolean getEsito() { return esito; }
}