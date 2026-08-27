package it.unicam.cs.mpgc.rpg129097.model;

import it.unicam.cs.mpgc.rpg129097.utils.CostantiGioco;
import lombok.Getter;
import lombok.NonNull;

import java.util.Collections;
import java.util.List;

/**
 * Rappresenta una singola giornata all'interno del gioco.
 * <p>Ogni giornata è caratterizzata da un numero identificativo, una descrizione
 * narrativa del contesto ed è vincolata a contenere esattamente NUMERO_NPC_PER_GIORNATA elementi con cui interagire.</p>
 * @author Elia Magini
 */
@Getter
public class Giornata {

    private final int numero;

    private final List<NPC> npc;

    private final String narrazione;

    /**
     * Costruisce una nuova giornata verificando che la lista degli NPC contenga esattamente NUMERO_NPC_PER_GIORNATA elementi.
     * @param numero il numero identificativo della giornata
     * @param npc la lista contenente esattamente 3 NPC; non può essere {@null}
     * @param narrazione il testo narrativo della giornata; non può essere {@null}
     * @throws NullPointerException se {@code npc} o {@code narrazione} sono {@null}
     * @throws IllegalArgumentException se la dimensione della lista {@code npc} è diversa da 3
     */
    public Giornata(int numero, @NonNull List<NPC> npc, @NonNull String narrazione) {
        if (npc.size() != CostantiGioco.NPC_PER_GIORNATA) {
            throw new IllegalArgumentException("Ogni giornata deve avere esattamente" + CostantiGioco.NPC_PER_GIORNATA+" NPC, ricevuti: " + npc.size());
        }
        this.numero = numero;
        this.npc = Collections.unmodifiableList(npc);
        this.narrazione = narrazione;
    }

}