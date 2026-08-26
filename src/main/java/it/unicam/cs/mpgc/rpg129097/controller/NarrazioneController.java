package it.unicam.cs.mpgc.rpg129097.controller;

import it.unicam.cs.mpgc.rpg129097.model.Giornata;
import it.unicam.cs.mpgc.rpg129097.model.Partita;
import it.unicam.cs.mpgc.rpg129097.model.PersonaggioPrincipale;
import it.unicam.cs.mpgc.rpg129097.utils.NavigazioneTastiera;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javax.swing.plaf.basic.BasicButtonUI;
import java.util.List;
import it.unicam.cs.mpgc.rpg129097.utils.CostantiGioco;




/**
 * Controller della schermata dedicata alla narrazione del gioco.
 * <p>Gestisce la visualizzazione del testo narrativo relativo alla giornata
 * corrente e il passaggio alla schermata di gioco successiva.
 * Gestisce inoltre la narrazione introduttiva visualizzata prima
 * dell'inizio della prima giornata.</p>
 *
 * @author Elia Magini
 * */
public class NarrazioneController {

    @FXML
    private TextArea narrazioneArea;
    @FXML
    private Button continuaButton;
    @FXML
    private Node root;

    private Partita partita;

    /**
     * Indica se la schermata sta visualizzando la narrazione introduttiva
     * precedente all'inizio della prima giornata.
     * <p>Vale {@code true} durante la narrazione iniziale e {@code false}
     * durante le narrazioni delle giornate successive.</p>
     */
    private boolean modalitaIniziale;



    /**
     * Inizializza la navigazione da tastiera della schermata.
     * <p>Abilita la navigazione tramite tastiera sul pulsante di continua
     * della partita.</p>
     */
    public void initialize() {NavigazioneTastiera.abilita(root, List.of(continuaButton));}


    /**
     * Imposta la partita corrente e visualizza la narrazione
     * della giornata corrente.
     * <p>Questo metodo viene utilizzato per le narrazioni delle giornate
     * successive a quella introduttiva.</p>
     * @param partita partita corrente del gioco
     */
    public void setPartita(Partita partita) {
        this.partita = partita;
        this.modalitaIniziale = false;

        Giornata giornata = partita.getGiornataCorrente();
        narrazioneArea.setText(giornata.getNarrazione());
    }


    /**
     * Imposta la partita corrente e visualizza il testo della
     * narrazione introduttiva.
     * <p>Questo metodo viene utilizzato per visualizzare il testo
     * narrativo iniziale prima dell'inizio della prima giornata.</p>
     * @param partita partita corrente del gioco
     * @param testoIniziale testo della narrazione introduttiva
     */
    public void setPartita(Partita partita, String testoIniziale) {
        this.partita = partita;
        this.modalitaIniziale = true;
        narrazioneArea.setText(testoIniziale);
    }


    /**
     * Gestisce l'azione associata al pulsante "Continua".
     * <p>Se la schermata visualizza la narrazione introduttiva,
     * passa direttamente alla schermata di gioco.
     * In caso contrario, fa avanzare la partita alla giornata successiva
     * e verifica se il gioco è terminato. Se la partita è conclusa,
     * viene mostrata la schermata finale; altrimenti viene mostrata
     * la schermata di gioco.</p>
     */
    @FXML
    private void onContinua() {
        if (modalitaIniziale) {
            SceneController.cambia(CostantiGioco.SCENA_GAME, partita);
            return;
        }
        partita.avanza();

        if (partita.isFinita()) {
            SceneController.cambia(CostantiGioco.SCENA_FINE, partita);
        } else {
            SceneController.cambia(CostantiGioco.SCENA_GAME, partita);
        }
    }
}