
package it.unicam.cs.mpgc.rpg129097.utils;

import it.unicam.cs.mpgc.rpg129097.model.ArchivioProposizioni;
import it.unicam.cs.mpgc.rpg129097.model.Item;
import it.unicam.cs.mpgc.rpg129097.model.NPC;

/**
 * Factory per creare oggetti {@link NPC}.
 * <p> Converte i dati di un {@link NPCData} in un vero e proprio {@link NPC},
 * creando al suo interno anche il relativo {@link Item}.</p>
 * @author Elia Magini
 */
public class NPCFactory {

    private final ArchivioProposizioni archivio;

    /**
     * Costruisce una nuova factory specificando l'archivio delle proposizioni da utilizzare.
     * @param archivio l'{@link ArchivioProposizioni} necessario per la creazione dei singoli item dei personaggi
     */
    public NPCFactory(ArchivioProposizioni archivio) {
        this.archivio = archivio;
    }


    /**
     * Crea un nuovo personaggio {@link NPC} a partire dal DTO di input e dalla stringa intuizione.
     * @param data il DTO {@link NPCData} contenente i dati del personaggio e del suo oggetto
     * @param intuizione l'intuizione narrativa o indizio associato all'NPC
     * @return una nuova istanza configurata di {@link NPC}
     */
    public NPC crea(NPCData data, String intuizione) {
        Item item = new Item(
                data.itemNome,
                data.itemDescrizione,
                archivio
        );

        return new NPC(
                data.nome,
                data.professione,
                item,
                intuizione
        );
    }
}
