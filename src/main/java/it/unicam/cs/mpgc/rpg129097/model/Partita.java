package it.unicam.cs.mpgc.rpg129097.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.Collections;
import java.util.List;
import it.unicam.cs.mpgc.rpg129097.utils.CostantiGioco;

/**
 * Orchestra una partita "one-shot": nessun dato viene salvato fra partite
 * diverse. Una partita è composta da 3 giornate fisse, ciascuna con 3 NPC,
 * seguite da narrazione. Questa classe si limita a gestire l'avanzamento
 * (quale giornata è corrente, quando la partita è finita): non contiene
 * logica di punteggio, che resta responsabilità di PersonaggioPrincipale.
 */
public class Partita {

    @Getter
    private final PersonaggioPrincipale personaggio;

    private final List<Giornata> giornate;

    private int indiceGiornataCorrente;


    /**
     * Costruisce una nuova partita verificando che la lista delle giornate contenga esattamente 3 elementi.
     * @param personaggio il personaggio principale del giocatore; non può essere {@null}
     * @param giornate la lista contenente esattamente 3 giornate; non può essere {@null}
     * @throws NullPointerException se {@code personaggio} o {@code giornate} sono {@null}
     * @throws IllegalArgumentException se la dimensione della lista {@code giornate} è diversa da 3
     */
    public Partita(@NonNull PersonaggioPrincipale personaggio, @NonNull List<Giornata> giornate) {
        if (giornate.size() != CostantiGioco.NUMERO_GIORNATE) {throw new IllegalArgumentException("Una partita deve avere esattamente"+CostantiGioco.NUMERO_GIORNATE+ " giornate, ricevute: " + giornate.size());}

        this.personaggio = personaggio;
        this.giornate = Collections.unmodifiableList(giornate);
        this.indiceGiornataCorrente = 0;
    }

    /**
     * Restituisce la giornata attualmente in corso.
     * @return la {@link Giornata} corrente
     * @throws IllegalStateException se la partita è già terminata
     */
    public Giornata getGiornataCorrente() {
        if (isFinita()) {throw new IllegalStateException("La partita è già terminata: non c'è una giornata corrente.");}
        return giornate.get(indiceGiornataCorrente);
    }

    /**
     * Verifica se la partita è terminata.
     * @return {@code true} se sono state completate tutte le giornate, {@code false} altrimenti
     */
    public boolean isFinita() {
        return indiceGiornataCorrente >= giornate.size();
    }

    /**
     * Avanza alla giornata successiva, aggiornando prima il livello del personaggio
     * in base al completamento della giornata corrente.
     * @throws IllegalStateException se la partita è già terminata
     */
    public void avanza() {
        if (isFinita()) {throw new IllegalStateException("La partita è già terminata: non si può avanzare oltre.");}
        personaggio.valutaLivello(getGiornataCorrente().getNumero());
        indiceGiornataCorrente++;
    }
}