package it.unicam.cs.mpgc.rpg129097.model;

import it.unicam.cs.mpgc.rpg129097.interfaces.Validatore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Rappresenta un personaggio non giocante (NPC) all'interno del gioco.
 * <p>Un NPC è un {@link Personaggio} caratterizzato da un oggetto {@link Validatore},
 * che rappresenta l'oggetto o elemento associato al personaggio, e da un livello
 * di intuizione.</p>
 *
 * @author Elia Magini
 */
public class NPC extends Personaggio {

    @Getter
    @Setter
    private Validatore item;


    @Getter
    private String intuizione;

    /**
     * Costruisce un nuovo NPC.
     * @param nome nome dell'NPC
     * @param professione professione dell'NPC
     * @param item oggetto associato all'NPC
     * @param intuizione informazione o indizio fornito dall'NPC
     * @throws NullPointerException se {@code nome} o {@code professione} sono {@code null}
     */
    public NPC(@NonNull String nome, @NonNull String professione,
               Validatore item, String intuizione) {
        super(nome, professione);
        this.item = item;
        this.intuizione = intuizione;
    }

    /**
     * Stampa la presentazione dell'NPC.
     * <p>La presentazione viene ottenuta tramite il metodo
     * {@link #getPresentazione()}.</p>
     */
    @Override
    public void presentati() {
        System.out.println(getPresentazione());
    }

    /**
     * Restituisce la presentazione dell'NPC.
     * @return la stringa contenente la presentazione dell'NPC
     */
    public String getPresentazione() {
        return  "Ciao, sono " + this.nome + "... è un piacere conoscerti." +
                " Il mio ruolo è: " + this.professione + "\n"+ this.intuizione;
    }
}