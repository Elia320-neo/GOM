package it.unicam.cs.mpgc.rpg129097.interfaces;

/**
 * Rappresenta una proposizione del gioco.
 * <p>Una proposizione è caratterizzata da una descrizione testuale
 * e da un esito booleano che ne rappresenta la validità.</p>
 *
 * @author Elia Magini
 */
public interface Proposizione {

    /**
     * Restituisce la descrizione della proposizione.
     * @return descrizione testuale della proposizione
     */
    String getDescrizione();

    /**
     * Restituisce l'esito della proposizione.
     * @return {@code true} se la proposizione ha esito positivo, {@code false} altrimenti
     */
    boolean getEsito();
}