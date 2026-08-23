package it.unicam.cs.mpgc.rpg129097.model;

import it.unicam.cs.mpgc.rpg129097.interfaces.Proposizione;
import lombok.NonNull;

/**
 * Rappresenta un'informazione utilizzata come proposizione all'interno
 * del gioco.
 * <p>Un'informazione è caratterizzata da una descrizione e da un esito
 * booleano che indica se la proposizione è vera o falsa.</p>
 * <p>La classe implementa l'interfaccia {@link Proposizione}.</p>
 */
public class Informazione implements Proposizione {

    private final String descrizione;

    private final boolean esito;


    /**
     * Costruisce una nuova informazione.
     * @param descrizione descrizione dell'informazione
     * @param esito esito associato all'informazione
     * @throws NullPointerException se {@code descrizione} è {@code null}
     */
    public Informazione(@NonNull String descrizione, boolean esito) {
        this.descrizione = descrizione;
        this.esito = esito;
    }

    /**
     * Restituisce la descrizione dell'informazione.
     * @return la descrizione dell'informazione
     */
    @Override
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Restituisce l'esito dell'informazione.
     * @return {@code true} se l'esito è positivo, {@code false} altrimenti
     */
    @Override
    public boolean getEsito() {
        return esito;
    }
}