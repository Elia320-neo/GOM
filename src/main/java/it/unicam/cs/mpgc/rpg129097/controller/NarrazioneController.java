package it.unicam.cs.mpgc.rpg129097.controller;

import it.unicam.cs.mpgc.rpg129097.model.Giornata;
import it.unicam.cs.mpgc.rpg129097.model.Partita;
import it.unicam.cs.mpgc.rpg129097.model.PersonaggioPrincipale;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javax.swing.plaf.basic.BasicButtonUI;
import java.util.List;
import it.unicam.cs.mpgc.rpg129097.utils.CostantiGioco;
public class NarrazioneController {

    @FXML
    private TextArea narrazioneArea;
    @FXML
    private Button continuaButton;

    private Partita partita;

    /** true se questa è la narrazione introduttiva (prima della giornata 1)*/
    private boolean modalitaIniziale;


    public void setPartita(Partita partita) {
        this.partita = partita;
        this.modalitaIniziale = false;

        Giornata giornata = partita.getGiornataCorrente();
        narrazioneArea.setText(giornata.getNarrazione());
    }

    public void setPartita(Partita partita, String testoIniziale) {
        this.partita = partita;
        this.modalitaIniziale = true;
        narrazioneArea.setText(testoIniziale);
    }

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