package it.unicam.cs.mpgc.rpg129097.model;

import it.unicam.cs.mpgc.rpg129097.interfaces.Proposizione;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class PersonaggioPrincipale extends Personaggio {

    private static final int PUNTI_SCOMMESSA = 10;
    private static final int[] SOGLIE_ESPERIENZA_PER_LIVELLO = {0, 10, 30};

    private int esperienza;
    private int livello;
    private int validazioniEffettuate;

    public PersonaggioPrincipale(@NonNull String nome, @NonNull String professione){
        super(nome,professione);
        this.esperienza = 0;
        this.livello = 0;
        this.validazioniEffettuate = 0;
    }


    @Override
    public void presentati(){}

    public void addEsperienza(int punti){this.esperienza += punti;}

    public void removeEsperienza(int punti){this.esperienza -= punti;}

    public void updateLivello(){this.livello++;}


    public void addValidazione(){this.validazioniEffettuate++;}


    public boolean fidati(NPC npc) {
        boolean esito = verifica(npc);
        if (esito) {
            addEsperienza(PUNTI_SCOMMESSA);
        } else {
            removeEsperienza(PUNTI_SCOMMESSA);
        }
        return esito;
    }

    public boolean nonFidarti(NPC npc) {
        boolean esito = verifica(npc);
        if (!esito) {
            addEsperienza(PUNTI_SCOMMESSA);
        } else {
            removeEsperienza(PUNTI_SCOMMESSA);
        }
        return esito;
    }

    private boolean verifica(NPC npc) {
        addValidazione();
        return npc.getItem().valida(npc.getIntuizione()).getEsito();
    }

    public void valutaLivello(int giornataCompletata) {
        if (giornataCompletata < 1 || giornataCompletata > SOGLIE_ESPERIENZA_PER_LIVELLO.length) {
            throw new IllegalArgumentException("Giornata non valida: " + giornataCompletata);
        }
        int sogliaRichiesta = SOGLIE_ESPERIENZA_PER_LIVELLO[giornataCompletata - 1];
        if (esperienza >= sogliaRichiesta) {
            livello = Math.max(livello, giornataCompletata);
        }
    }

}