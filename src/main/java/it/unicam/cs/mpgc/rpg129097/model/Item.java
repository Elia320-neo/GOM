package it.unicam.cs.mpgc.rpg129097.model;

import it.unicam.cs.mpgc.rpg129097.interfaces.Proposizione;
import it.unicam.cs.mpgc.rpg129097.interfaces.Validatore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Item implements Validatore {
    @Getter
    private String nome;
    @Getter
    private String descrizione;
    @Getter @Setter
    private boolean utilizzato;
    private final ArchivioProposizioni archivio; //dipendenza di utilizzo

    public Item(@NonNull String nome, @NonNull String descrizione, ArchivioProposizioni archivio){
        this.nome = nome;
        this.descrizione = descrizione;
        this.archivio = archivio;
    }


    @Override
    public Proposizione valida(String intuizione) {
        return archivio.cerca(intuizione);
    }

}