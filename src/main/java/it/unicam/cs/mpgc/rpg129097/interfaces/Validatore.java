package it.unicam.cs.mpgc.rpg129097.interfaces;

/**
 * Rappresenta un elemento in grado di validare un'intuizione
 * fornita dal giocatore.
 * <p>Un validatore possiede un nome e una descrizione e permette
 * di verificare un'intuizione restituendo una {@link Proposizione}
 * che ne rappresenta l'esito.</p>
 *
 * @author Elia Magini
 */
public interface Validatore {

    /**
     * Restituisce il nome del validatore.
     * @return nome del validatore
     */
    String getNome();

    /**
     * Restituisce la descrizione del validatore.
     * @return descrizione del validatore
     */
    String getDescrizione();

    /**
     * Valida un'intuizione fornita dal giocatore.
     * @param intuzione intuizione da validare
     * @return proposizione contenente la descrizione e l'esito della validazione
     */
    Proposizione valida(String intuzione);
}