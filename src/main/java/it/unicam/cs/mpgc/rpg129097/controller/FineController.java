package it.unicam.cs.mpgc.rpg129097.controller;


import it.unicam.cs.mpgc.rpg129097.model.Partita;
import it.unicam.cs.mpgc.rpg129097.model.PersonaggioPrincipale;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import it.unicam.cs.mpgc.rpg129097.utils.CostantiGioco;
import java.util.List;

public class FineController {


    private Partita partita;

    @FXML
    private Label esperienzaLabel;
    @FXML
    private Label livelloLabel;
    @FXML
    private Label validazioniLabel;
    @FXML
    private Button chiudiButton;
    @FXML
    private Button giocaDiNuovoButton;

    public void setPartita(Partita partita) {
        this.partita = partita;

        PersonaggioPrincipale p = partita.getPersonaggio();
        mostraEsperienza(p);
        mostraLivello(p);
        mostraValidazioni(p);
    }

    @FXML
    private void onGiocaDiNuovo() {
        SceneController.cambia(CostantiGioco.SCENA_START, null);
    }

    @FXML
    private void onChiudi() {
        Stage stage = (Stage) chiudiButton.getScene().getWindow();
        stage.close();
    }

    public void mostraEsperienza(PersonaggioPrincipale p){
        esperienzaLabel.setText("Esperienza finale: " + p.getEsperienza());
    }

    public void mostraLivello(PersonaggioPrincipale p){
        livelloLabel.setText("Livello raggiunto: " + p.getLivello());
    }

    public void mostraValidazioni(PersonaggioPrincipale p){
        validazioniLabel.setText("Validazioni effettuate: " + p.getValidazioniEffettuate());
    }
}
