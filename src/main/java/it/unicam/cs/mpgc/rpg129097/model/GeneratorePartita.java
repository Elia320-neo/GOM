package it.unicam.cs.mpgc.rpg129097.model;

import it.unicam.cs.mpgc.rpg129097.interfaces.Proposizione;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.unicam.cs.mpgc.rpg129097.utils.ParserNPC;
import it.unicam.cs.mpgc.rpg129097.utils.NPCData;
import it.unicam.cs.mpgc.rpg129097.utils.NPCFactory;
import it.unicam.cs.mpgc.rpg129097.utils.ParserNarrazioni;
import it.unicam.cs.mpgc.rpg129097.utils.NarrazioneData;
import it.unicam.cs.mpgc.rpg129097.utils.CostantiGioco;

import java.util.HashMap;
import java.util.Map;


/**
 *  Rappresenta un costruttore di partite
 *  <p> La classe si occupa di caricare i dati degli NPC e le narrazioni
 *  delle giornate dai relativi file JSON e di utilizzarli per generare
 *  una nuova istanza di {@link Partita}.
 *  Ogni partita è composta da un numero prestabilito di {@link Giornata},
 *  ciascuna contenente gli NPC associati e la relativa narrazione.
 *  Le intuizioni degli NPC vengono ottenute dall'{@link ArchivioProposizioni}
 *  fornito durante la generazione della partita. </p>
 */
public class GeneratorePartita {

    private final List<NPCData> npcDatabase;
    private final NPCFactory npcFactory;
    private final Map<Integer, String> narrazioni;


    /**
     * Costruisce un generatore di partite.
     * <p>Durante la costruzione vengono caricati i dati degli NPC e
     * le narrazioni delle giornate. Viene inoltre verificato che
     * il numero di NPC e le narrazioni disponibili siano sufficienti
     * per creare una partita completa.</p>
     * @param archivio archivio contenente le proposizioni disponibili per la generazione delle intuizioni degli NPC
     * @throws NullPointerException se {@code archivio} è {@code null}
     * @throws IllegalStateException se il file degli NPC contiene un umero insufficiente di NPC oppure manca la narrazione di una giornata.</p>
     */
    public GeneratorePartita(@NonNull ArchivioProposizioni archivio) {
        this.npcDatabase = new ParserNPC().parse(CostantiGioco.NPC_JSON);
        this.npcFactory = new NPCFactory(archivio);
        this.narrazioni = caricaNarrazioni();

        if (npcDatabase.size() < CostantiGioco.NPC_TOTALI) {throw new IllegalStateException("Servono almeno " + CostantiGioco.NPC_TOTALI + " NPC nel file npc.json, trovati: " + npcDatabase.size());}
        for (int numeroGiornata = 1; numeroGiornata <= CostantiGioco.NUMERO_GIORNATE; numeroGiornata++) {
            if (!narrazioni.containsKey(numeroGiornata)) {
                throw new IllegalStateException("Manca la narrazione per la giornata " + numeroGiornata + " in narrazioni.json");
            }
        }
    }


    /**
     * Carica le narrazioni delle giornate dal file JSON e le organizza
     * in una {@link HashMap}, indicizzata per numero di giornata.
     * @return una mappa contenente, per ogni giornata, la relativa narrazione.
     */
    private Map<Integer, String> caricaNarrazioni() {
        List<NarrazioneData> dati = new ParserNarrazioni().parse("narrazioni.json");
        Map<Integer, String> mappa = new HashMap<>();
        for (NarrazioneData dato : dati) {
            mappa.put(dato.numeroGiornata, dato.descrizione);
        }
        return mappa;
    }


    /**
     * Genera una nuova partita composta dalle giornate previste dal gioco.
     * <p>Per ogni {@link Giornata} vengono creati gli {@link NPC}
     * e le proposizioni disponibili nell'{@link ArchivioProposizioni}. Al termine
     * viene creato il {@link PersonaggioPrincipale} associato al giocatore.</p>
     * @param nomeGiocatore nome del giocatore
     * @param archivio archivio contenente le proposizioni da utilizzare come intuizioni degli NPC
     * @throws NullPointerException se {@code nomeGiocatore} o {@code archivio} è {@code null}
     * @throws IllegalStateException se l'archivio non contiene un numero sufficiente di proposizioni
     * @return una nuova partita completa
     */
    public Partita genera(@NonNull String nomeGiocatore, @NonNull ArchivioProposizioni archivio) {
        Iterator<Proposizione> fatti = fattiDisponibili(archivio);

        List<Giornata> giornate = new ArrayList<>();
        for (int numeroGiornata = 1; numeroGiornata <= CostantiGioco.NUMERO_GIORNATE; numeroGiornata++) {
            giornate.add(creaGiornata(numeroGiornata, fatti));
        }
        return new Partita(creaPersonaggio(nomeGiocatore), giornate);
    }



    /**
     * Recupera le proposizioni disponibili nell'{@link ArchivioProposizioni} e ne restituisce
     * un {@link Iterator}.
     * <p>Viene creata una copia delle proposizioni presenti nell'{@link ArchivioProposizioni},
     * così da non modificare direttamente la collezione originale.</p>
     * @param archivio archivio dal quale recuperare le proposizioni
     * @throws IllegalStateException se il numero di proposizioni presenti nell'archivio è inferiore al numero di NPC richiesti dal gioco
     * @return {@link Iterator} sulle proposizioni disponibili
     */
    private Iterator<Proposizione> fattiDisponibili(ArchivioProposizioni archivio) {
        List<Proposizione> fatti = new ArrayList<>(archivio.getProposizioni());
        if (fatti.size() < CostantiGioco.NPC_TOTALI) {
            throw new IllegalStateException("Servono almeno " + CostantiGioco.NPC_TOTALI + " fatti nell'archivio, trovati: " + fatti.size());
        }
        return fatti.iterator();
    }


    /**
     * Crea una {@link Giornata} della partita e i relativi {@link NPC}.
     * <p> Gli NPC vengono selezionati in base al numero
     * della giornata e ricevono come intuizione una {@link Proposizione}
     * disponibile nell'iteratore fornito.</p>
     * @param numeroGiornata numero della giornata da creare
     * @param fatti iteratore contenente le proposizioni da assegnare agli NPC come intuizioni
     * @throws java.util.NoSuchElementException se non sono disponibili sufficienti proposizioni
     * @return la {@link Giornata} creata con i relativi NPC e la narrazione
     */
    private Giornata creaGiornata(int numeroGiornata, Iterator<Proposizione> fatti) {
        List<NPC> npcDelGiorno = new ArrayList<>();

        for (int i = 1; i <= CostantiGioco.NPC_PER_GIORNATA; i++) {
            int indexNPC = (numeroGiornata - 1) * CostantiGioco.NPC_PER_GIORNATA + i - 1;
            String intuizione = fatti.next().getDescrizione();

            NPC npc = npcFactory.crea(npcDatabase.get(indexNPC), intuizione);
            npcDelGiorno.add(npc);
        }
        return new Giornata(numeroGiornata, npcDelGiorno, narrazioni.get(numeroGiornata));
    }



    /**
     * Crea il {@link PersonaggioPrincipale} della partita.
     * @param nomeGiocatore nome del giocatore da assegnare al personaggio
     * @return il personaggio principale creato
     */
    private PersonaggioPrincipale creaPersonaggio(String nomeGiocatore) {
        return new PersonaggioPrincipale(nomeGiocatore, "esploratore");
    }
}