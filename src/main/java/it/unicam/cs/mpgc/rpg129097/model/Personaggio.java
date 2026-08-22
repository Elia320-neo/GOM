package it.unicam.cs.mpgc.rpg129097.model;

import lombok.Getter;
import lombok.NonNull;

/**
 * Rappresenta l'astrazione di un personaggio giocante o non giocante
 * all'interno del gioco.
 *
 * <p>Ogni personaggio è caratterizzato da un nome e da una professione,
 * definisce inoltre il metodo astratto presentati()</p>
 *
 * @author Elia Magini
 */
public abstract class Personaggio {

    @Getter
    protected String nome;

    @Getter
    protected String professione;

    /**
     * Costruisce un nuovo Personaggio.
     * @param nome nome del personaggio
     * @param professione professione del personaggio
     * @throws NullPointerException se {@code nome} o {@code professione}
     *         sono {@code null}
     */
    protected Personaggio(@NonNull String nome, @NonNull String professione) {
        this.nome = nome;
        this.professione = professione;
    }

    /**
     * Presenta il personaggio.
     * <p>L'implementazione della presentazione è demandata alle classi
     * concrete che estendono {@code Personaggio}.</p>
     */
    public abstract void presentati();
}