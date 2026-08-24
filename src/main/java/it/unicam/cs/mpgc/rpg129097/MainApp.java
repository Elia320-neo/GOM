package it.unicam.cs.mpgc.rpg129097;

import it.unicam.cs.mpgc.rpg129097.interfaces.Proposizione;
import it.unicam.cs.mpgc.rpg129097.model.ArchivioProposizioni;
import it.unicam.cs.mpgc.rpg129097.utils.NarrazioneData;
import it.unicam.cs.mpgc.rpg129097.utils.ParserNarrazioni;
import it.unicam.cs.mpgc.rpg129097.utils.ParserProposizioni;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        ArchivioProposizioni archivio = new ParserProposizioni().parse("archivio_esiti.json");

        List<NarrazioneData> listaNarrazioni = new ParserNarrazioni().parse("narrazioni.json");

        for (NarrazioneData testo: listaNarrazioni){
            System.out.println(testo.descrizione);
        }
    }
}