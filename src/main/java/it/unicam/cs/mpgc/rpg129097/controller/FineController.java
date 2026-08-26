package it.unicam.cs.mpgc.rpg129097.controller;


import it.unicam.cs.mpgc.rpg129097.model.Partita;
import it.unicam.cs.mpgc.rpg129097.model.PersonaggioPrincipale;
import it.unicam.cs.mpgc.rpg129097.utils.NavigazioneTastiera;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import it.unicam.cs.mpgc.rpg129097.utils.CostantiGioco;
import java.util.List;


/**
 * Controller della schermata finale del gioco.
 * <p>Gestisce la chiusura della partita,la chiusura della
 * finestra principale e il passaggio alla schermata d'inizio,
 * per giocare una nuova partita.</p>
 *
 * @author Elia Magini
 */
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
    @FXML
    private Node root;


    /**
     * Inizializza la navigazione da tastiera della schermata.
     * <p>Abilita la navigazione tramite tastiera sul pulsante di chiudi e gioca di nuovo
     * della partita.</p>
     */
    public void initialize() {
        NavigazioneTastiera.abilita(root, List.of(chiudiButton,giocaDiNuovoButton));
    }



    /**
     * Imposta la partita terminata e aggiorna le statistiche
     * visualizzate nella schermata finale.
     * @param partita partita terminata da visualizzare
     */
    public void setPartita(Partita partita) {
        this.partita = partita;

        PersonaggioPrincipale p = partita.getPersonaggio();
        mostraEsperienza(p);
        mostraLivello(p);
        mostraValidazioni(p);
    }


    /**
     * Gestisce l'azione associata al pulsante "Gioca di nuovo".
     * <p>Riporta l'utente alla schermata iniziale del gioco,
     * permettendo di iniziare una nuova partita.</p>
     */
    @FXML
    private void onGiocaDiNuovo() {
        SceneController.cambia(CostantiGioco.SCENA_START, null);
    }


    /**
     * Gestisce l'azione associata al pulsante "Chiudi".
     * <p>Recupera la finestra attualmente visualizzata e la chiude,
     * terminando la sessione di gioco.</p>
     */
    @FXML
    private void onChiudi() {
        Stage stage = (Stage) chiudiButton.getScene().getWindow();
        stage.close();
    }


    /**
     * Visualizza l'esperienza finale ottenuta dal personaggio principale.
     * @param p personaggio principale di cui visualizzare l'esperienza
     */
    public void mostraEsperienza(PersonaggioPrincipale p){
        esperienzaLabel.setText("Esperienza finale: " + p.getEsperienza());
    }


    /**
     *Visualizza il livello finale raggiunto dal personaggio principale.
     * @param p personaggio principale di cui visualizzare il livello
     */
    public void mostraLivello(PersonaggioPrincipale p){
        livelloLabel.setText("Livello raggiunto: " + p.getLivello());
    }



    /**
     * Visualizza il numero di validazioni effettuate dal personaggio principale durante la partita.
     * @param p personaggio principale di cui visualizzare il numero di validazioni effettuate
     */
    public void mostraValidazioni(PersonaggioPrincipale p){
        validazioniLabel.setText("Validazioni effettuate: " + p.getValidazioniEffettuate());
    }
}
