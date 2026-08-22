package it.unicam.cs.mpgc.rpg129097.model;

import it.unicam.cs.mpgc.rpg129097.interfaces.Proposizione;
import lombok.NonNull;

import java.util.*;

public class ArchivioProposizioni {

    private final Map<String, Proposizione> proposizioni;


    public ArchivioProposizioni(@NonNull Collection<Proposizione> proposizioni) {
        this.proposizioni = new LinkedHashMap<>();
        for (Proposizione proposizione : proposizioni) {
            this.proposizioni.put(proposizione.getDescrizione(), proposizione);
        }
    }

    public Proposizione cerca(String descrizione) {
        Proposizione proposizione = proposizioni.get(descrizione);
        if (proposizione == null) throw new NoSuchElementException("Nessuna proposizione trovata per: " + descrizione);
        return proposizione;
    }


    public Collection<Proposizione> getProposizioni() {
        return Collections.unmodifiableCollection(proposizioni.values());
    }

}