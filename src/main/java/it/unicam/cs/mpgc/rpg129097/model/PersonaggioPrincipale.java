package it.unicam.cs.mpgc.rpg129097.model;

import it.unicam.cs.mpgc.rpg129097.interfaces.Proposizione;
import it.unicam.cs.mpgc.rpg129097.utils.CostantiGioco;
import lombok.Getter;
import lombok.NonNull;

/**
 * Rappresenta il personaggio principale controllato dal giocatore.
 * <p>Gestisce i punti esperienza, il livello attuale, il conteggio delle validazioni
 * effettuate e la logica per fidarsi o meno degli NPC incontrati.</p>
 */
@Getter
public class PersonaggioPrincipale extends Personaggio {


    private int esperienza;

    private int livello;

    private int validazioniEffettuate;


    /**
     * Costruisce un nuovo personaggio principale con nome e professione specificati.
     * Inizializza l'esperienza, il livello e le validazioni a 0.
     * @param nome il nome del personaggio; non può essere {@null}
     * @param professione la professione del personaggio; non può essere {@null}
     * @throws NullPointerException se {@code nome} o {@code professione} sono {@null}
     */
    public PersonaggioPrincipale(@NonNull String nome, @NonNull String professione){
        super(nome,professione);
        this.esperienza = 0;
        this.livello = 0;
        this.validazioniEffettuate = 0;
    }

    //TODO: aggiungere stringa di presentazione
    @Override
    public void presentati(){}


    /**
     * Aggiunge punti esperienza al totale del personaggio.
     * @param punti la quantità di punti da aggiungere
     */
    public void addEsperienza(int punti){this.esperienza += punti;}

    /**
     * Rimuove punti esperienza dal totale del personaggio.
     * @param punti la quantità di punti da sottrarre
     */
    public void removeEsperienza(int punti){this.esperienza -= punti;}

    /**
     * Incrementa il livello del personaggio di 1.
     */
    public void updateLivello(){this.livello++;}


    /**
     * Incrementa di 1 il contatore delle validazioni effettuate.
     */
    public void addValidazione(){this.validazioniEffettuate++;}


    /**
     * Decide di fidarsi dell'NPC specificato.
     * <p> Se l'intuizione dell'NPC si rivela vera, si guadagnano punti esperienza,
     * altrimenti si perdono.</p>
     * @param npc l'NPC su cui si esprime la fiducia
     * @return {@code true} se l'intuizione dell'NPC era corretta, {@code false} altrimenti
     */
    public boolean fidati(NPC npc) {
        boolean esito = verifica(npc);
        if (esito) {
            addEsperienza(CostantiGioco.PUNTI_SCOMMESSA);
        } else {
            removeEsperienza(CostantiGioco.PUNTI_SCOMMESSA);
        }
        return esito;
    }

    /**
     * Decide di non fidarsi dell'NPC specificato.
     * <p>Se l'intuizione dell'NPC si rivela falsa, si guadagnano punti esperienza,
     * altrimenti si perdono.</p>
     * @param npc l'NPC su cui esprimi sfiducia
     * @return {@code true} se l'intuizione dell'NPC era effettivamente vera, {@code false} altrimenti
     */
    public boolean nonFidarti(NPC npc) {
        boolean esito = verifica(npc);
        if (!esito) {
            addEsperienza(CostantiGioco.PUNTI_SCOMMESSA);
        } else {
            removeEsperienza(CostantiGioco.PUNTI_SCOMMESSA);
        }
        return esito;
    }

    /**
     * Esegue la verifica dell'intuizione dell'NPC mediante il suo oggetto
     * e incrementa il contatore delle validazioni.
     * @param npc l'NPC di cui verificare l'intuizione
     * @return l'esito della validazione (vero o falso)
     */
    private boolean verifica(NPC npc) {
        addValidazione();
        return npc.getItem().valida(npc.getIntuizione()).getEsito();
    }

    /**
     * Valuta se l'esperienza attuale è sufficiente per incrementare il livello
     * in base alla giornata completata.
     * @param giornataCompletata l'indice (1-based) della giornata appena completata
     * @throws IllegalArgumentException se l'indice della giornata non è valido
     */
    public void valutaLivello(int giornataCompletata) {
        if (giornataCompletata < 1 || giornataCompletata > CostantiGioco.SOGLIE_ESPERIENZA_PER_LIVELLO.length) {
            throw new IllegalArgumentException("Giornata non valida: " + giornataCompletata);
        }
        int sogliaRichiesta = CostantiGioco.SOGLIE_ESPERIENZA_PER_LIVELLO[giornataCompletata - 1];
        if (esperienza >= sogliaRichiesta) {
            livello = Math.max(livello, giornataCompletata);
        }
    }

}