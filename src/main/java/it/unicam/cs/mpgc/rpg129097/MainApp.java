package it.unicam.cs.mpgc.rpg129097;

import it.unicam.cs.mpgc.rpg129097.interfaces.Proposizione;
import it.unicam.cs.mpgc.rpg129097.model.ArchivioProposizioni;
import it.unicam.cs.mpgc.rpg129097.model.NPC;
import it.unicam.cs.mpgc.rpg129097.utils.*;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        ArchivioProposizioni archivio = new ParserProposizioni().parse("archivio_esiti.json");

        List<NarrazioneData> listaNarrazioni = new ParserNarrazioni().parse("narrazioni.json");

        for (NarrazioneData testo: listaNarrazioni){
            System.out.println(testo.descrizione);
        }

        System.out.println("================================= TEST NPC ===============================0");

        List<NPCData> listaNPC = new ParserNPC().parse("npc.json");

        for(NPCData npc: listaNPC){
            System.out.println("==============================================================");
            System.out.println(npc.nome);
            System.out.println(npc.itemNome);
            System.out.println(npc.itemDescrizione);
            System.out.println(npc.professione);
        }
    }
}