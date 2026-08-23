package it.unicam.cs.mpgc.rpg129097.model;

import it.unicam.cs.mpgc.rpg129097.interfaces.Proposizione;
import lombok.NonNull;

import java.util.*;

/**
 * Gestisce l'archivio delle proposizioni nel sistema, memorizzandole tramite
 * una mappa indicizzata per la loro descrizione.
 * Mantiene l'ordine di inserimento originale delle proposizioni inserite.
 */
public class ArchivioProposizioni {


    private final Map<String, Proposizione> proposizioni;

    /**
     * Costruisce un nuovo archivio a partire da una collezione di proposizioni.
     * @param proposizioni la collezione di proposizioni da inserire nell'archivio; non può essere {@null}
     * @throws NullPointerException se la collezione passata è {@null}
     */
    public ArchivioProposizioni(@NonNull Collection<Proposizione> proposizioni) {
        this.proposizioni = new LinkedHashMap<>();
        for (Proposizione proposizione : proposizioni) {
            this.proposizioni.put(proposizione.getDescrizione(), proposizione);
        }
    }

    /**
     * Cerca e restituisce una proposizione presente nell'archivio in base alla sua descrizione.
     * @param descrizione la descrizione della proposizione da cercare
     * @return la {@link Proposizione} corrispondente alla descrizione fornita
     * @throws NoSuchElementException se non viene trovata alcuna proposizione con la descrizione specificata
     */
    public Proposizione cerca(String descrizione) {
        Proposizione proposizione = proposizioni.get(descrizione);
        if (proposizione == null) throw new NoSuchElementException("Nessuna proposizione trovata per: " + descrizione);
        return proposizione;
    }

    /**
     * Restituisce una vista non modificabile di tutte le proposizioni contenute nell'archivio.
     * @return una {@link Collection} non modificabile di {@link Proposizione}
     */
    public Collection<Proposizione> getProposizioni() {
        return Collections.unmodifiableCollection(proposizioni.values());
    }

}