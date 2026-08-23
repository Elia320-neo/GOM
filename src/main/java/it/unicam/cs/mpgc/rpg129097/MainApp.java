package it.unicam.cs.mpgc.rpg129097;

import it.unicam.cs.mpgc.rpg129097.interfaces.Proposizione;
import it.unicam.cs.mpgc.rpg129097.model.ArchivioProposizioni;
import it.unicam.cs.mpgc.rpg129097.utils.ParserProposizioni;

public class MainApp {

    public static void main(String[] args) {
        ArchivioProposizioni archivio = new ParserProposizioni().parse("archivio_esiti.json");

        System.out.println("Proposizioni caricate: " + archivio.getProposizioni().size());

        for (Proposizione p : archivio.getProposizioni()) {
            System.out.printf("- \"%s\" -> esito: %b%n", p.getDescrizione(), p.getEsito());
        }

        System.out.println("----------------------------------------");

        String descrizioneTest = "La verità è che tu sei il male";
        Proposizione trovata = archivio.cerca(descrizioneTest);
        System.out.println("Ricerca \"" + descrizioneTest + "\" -> esito: " + trovata.getEsito());
    }
}