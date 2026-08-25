package it.unicam.cs.mpgc.rpg129097.model;

import it.unicam.cs.mpgc.rpg129097.interfaces.Proposizione;
import it.unicam.cs.mpgc.rpg129097.interfaces.Validatore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Rappresenta un oggetto presente all'interno del gioco.
 *
 * <p>Un {@code Item} è caratterizzato da un nome, una descrizione e
 * dallo stato che indica se è stato utilizzato. Implementa l'interfaccia
 * {@link Validatore} e utilizza un {@link ArchivioProposizioni} per
 * verificare le intuizioni associate all'oggetto.</p>
 */
public class Item implements Validatore {


    @Getter
    private String nome;


    @Getter
    private String descrizione;

    @Getter
    @Setter
    private boolean utilizzato;

    private final ArchivioProposizioni archivio;


    /**
     * Costruisce un nuovo oggetto.
     * @param nome nome dell'oggetto
     * @param descrizione descrizione dell'oggetto
     * @param archivio archivio delle proposizioni utilizzato per la validazione
     * @throws NullPointerException se {@code nome} o {@code descrizione} sono {@code null}
     */
    public Item(@NonNull String nome, @NonNull String descrizione, ArchivioProposizioni archivio) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.archivio = archivio;
    }


    /**
     * Valida un'intuizione cercando nell'archivio la proposizione
     * corrispondente.
     * @param intuizione intuizione da validare
     * @return la {@link Proposizione} corrispondente all'intuizione, oppure il
     * valore restituito dall'archivio qualora non venga trovata una corrispondenza
     */
    @Override
    public Proposizione valida(String intuizione) {
        return archivio.cerca(intuizione);
    }
}