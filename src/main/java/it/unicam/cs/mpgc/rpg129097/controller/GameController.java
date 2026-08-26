package it.unicam.cs.mpgc.rpg129097.controller;

import it.unicam.cs.mpgc.rpg129097.model.Giornata;
import it.unicam.cs.mpgc.rpg129097.model.NPC;
import it.unicam.cs.mpgc.rpg129097.model.Partita;
import it.unicam.cs.mpgc.rpg129097.model.PersonaggioPrincipale;
import it.unicam.cs.mpgc.rpg129097.utils.NavigazioneTastiera;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import it.unicam.cs.mpgc.rpg129097.utils.CostantiGioco;

import java.util.List;

/**
 * Controller principale della schermata di gioco.
 * <p>Gestisce l'interazione tra il giocatore e gli NPC durante
 * una giornata di gioco. Permette al giocatore di scegliere se
 * fidarsi o meno di un NPC, verificare l'esito della scelta e
 * passare successivamente all'NPC seguente.</p>
 *
 * @author Elia Magini
 */
public class GameController {


    @FXML
    private Label nomeNpcLabel;

    @FXML
    private Label professioneNpcLabel;

    @FXML
    private TextArea intuizioneArea;

    @FXML
    private ImageView npcImage;

    @FXML
    private Label giornataLabel;

    @FXML
    private Button fidatiButton;

    @FXML
    private Button nonFidartiButton;

    @FXML
    private Button verificaButton;

    @FXML
    private Button continuaButton;

    @FXML
    private Label risultatoLabel;

    @FXML
    private Label esperienzaLabel;

    @FXML
    private Label validazioniLabel;

    @FXML
    private Node root;


    private Partita partita;
    private PersonaggioPrincipale personaggio;
    private NPC npcCorrente;
    private Boolean esitoScelta;
    private int indiceNpc = 0;

    /**
     * Inizializza la navigazione da tastiera della schermata.
     * <p>Abilita la navigazione tramite tastiera sul pulsante di fidati,
     * non fidarti, verifica e continua della partita.</p>
     */
    public void initialize() {
        NavigazioneTastiera.abilita(root, List.of(fidatiButton, nonFidartiButton, verificaButton, continuaButton));
    }


    /**
     * Imposta la partita corrente e carica il primo NPC
     * della giornata.
     * @param partita partita corrente
     */
    public void setPartita(Partita partita) {
        this.partita = partita;
        this.personaggio = partita.getPersonaggio();
        indiceNpc = 0;
        caricaProssimoNpc();
    }


    /**
     * Carica e visualizza l'NPC corrispondente all'indice corrente.
     * <p>Il metodo recupera l'NPC della giornata corrente, aggiorna
     * le informazioni visualizzate e ripristina lo stato iniziale
     * dell'interfaccia.</p>
     */
    private void caricaProssimoNpc() {
        Giornata giornata = partita.getGiornataCorrente();

        npcCorrente = giornata.getNpc().get(indiceNpc);

        aggiornaInformazioniNpc(giornata);
        aggiornaPresentazioneNpc();
        aggiornaImmagineNpc();
        preparaScelta();
        resetRisultato();
        resetEsitoScelta();
        impostaFocusSuFidati();
    }


    /**
     * Aggiorna le informazioni testuali relative all'NPC corrente
     * e alla giornata in corso.
     * @param giornata giornata corrente della partita
     */
    private void aggiornaInformazioniNpc(Giornata giornata) {
        nomeNpcLabel.setText(npcCorrente.getNome());
        professioneNpcLabel.setText(npcCorrente.getProfessione());
        giornataLabel.setText("Giornata "+ giornata.getNumero()+ "/" + CostantiGioco.NUMERO_GIORNATE);
    }


    /**
     * Aggiorna il testo relativo alla presentazione dell'NPC corrente.
     */
    private void aggiornaPresentazioneNpc() {
        intuizioneArea.setText(npcCorrente.getPresentazione());
    }


    /**
     * Aggiorna l'immagine visualizzata dell'NPC corrente.
     */
    private void aggiornaImmagineNpc() {
        npcImage.setImage(new Image(CostantiGioco.NPC_IMAGES+ npcCorrente.getNome() + ".png"));
    }


    /**
     * Prepara l'interfaccia per permettere al giocatore
     * di scegliere se fidarsi o meno dell'NPC.
     */
    private void preparaScelta() {
        abilitaPulsanteScelta(fidatiButton);
        abilitaPulsanteScelta(nonFidartiButton);

        verificaButton.setDisable(true);
        verificaButton.setVisible(true);
        verificaButton.setManaged(true);

        continuaButton.setVisible(false);
        continuaButton.setManaged(false);
    }


    /**
     * Abilita e rende visibile un pulsante utilizzato
     * per effettuare la scelta sull'NPC.
     * @param button pulsante da abilitare
     */
    private void abilitaPulsanteScelta(Button button) {
        button.setDisable(false);
        button.setVisible(true);
        button.setManaged(true);
    }


    /**
     * Ripristina il risultato visualizzato nella schermata.
     */
    private void resetRisultato() {risultatoLabel.setText("");}


    /**
     * Ripristina l'esito della scelta effettuata dal giocatore.
     */
    private void resetEsitoScelta() {esitoScelta = null;}


    /**
     * Imposta il focus sul pulsante "Fidati" dopo il caricamento
     * dell'NPC corrente.
     */
    private void impostaFocusSuFidati() {
        Platform.runLater(() -> {
            if (fidatiButton.isVisible() && !fidatiButton.isDisabled()) {
                fidatiButton.requestFocus();
            }
        });
    }


    /**
     * Gestisce la scelta del giocatore di fidarsi dell'NPC.
     */
    @FXML
    private void onFidati() {
        esitoScelta = personaggio.fidati(npcCorrente);
        mostraItemRicevuto();
    }


    /**
     * Gestisce la scelta del giocatore di non fidarsi dell'NPC.
     */
    @FXML
    private void onNonFidarti() {
        esitoScelta = personaggio.nonFidarti(npcCorrente);
        mostraItemRicevuto();
    }


    /**
     * Mostra l'oggetto ricevuto dal giocatore dopo la scelta.
     * <p>Disabilita i pulsanti relativi alla scelta e abilita
     * il pulsante per la verifica dell'esito.</p>
     */
    private void mostraItemRicevuto() {
        intuizioneArea.setText("Hai ricevuto: "+ npcCorrente.getItem().getNome()+ "\n"+ npcCorrente.getItem().getDescrizione());

        fidatiButton.setDisable(true);
        nonFidartiButton.setDisable(true);
        verificaButton.setDisable(false);

        Platform.runLater(() -> verificaButton.requestFocus());
    }


    /**
     * Mostra il risultato reale della scelta effettuata.
     * <p>Aggiorna le statistiche del giocatore, modifica la visibilità
     * dei pulsanti e imposta il focus sul pulsante "Continua".</p>
     */
    @FXML
    private void onVerifica() {
        mostraEsito();
        aggiornaStatistiche();
        preparaPassaggioSuccessivo();
        impostaFocusSuContinua();
    }


    /**
     * Mostra l'esito reale della scelta effettuata dal giocatore.
     */
    private void mostraEsito() {risultatoLabel.setText("Esito reale: " + esitoScelta);}


    /**
     * Aggiorna le statistiche del giocatore visualizzate nella schermata.
     */
    private void aggiornaStatistiche() {
        mostraEsperienza();
        mostraValidazioni();
    }

    /**
     * Mostra l'esperienza raggiunta ad ogni validazione.
     */
    private void mostraEsperienza(){esperienzaLabel.setText("Esperienza: "+ personaggio.getEsperienza());}

    /**
     * Mostra il counter delle validazioni.
     */
    private void mostraValidazioni(){validazioniLabel.setText("Validazioni: "+personaggio.getValidazioniEffettuate());}


    /**
     * Nasconde il pulsante "Verifica" e mostra il pulsante "Continua".
     */
    private void preparaPassaggioSuccessivo() {
        verificaButton.setVisible(false);
        verificaButton.setManaged(false);

        continuaButton.setVisible(true);
        continuaButton.setManaged(true);
    }


    /**
     * Imposta il focus sul pulsante "Continua" dopo la verifica.
     */
    private void impostaFocusSuContinua() {
        Platform.runLater(() -> continuaButton.requestFocus());
    }


    /**
     * Passa all'NPC successivo oppure, se la giornata è terminata,
     * passa alla schermata della narrazione.
     */
    @FXML
    private void onContinua() {
        indiceNpc++;
        if (indiceNpc >= partita.getGiornataCorrente().getNpc().size()) {
            SceneController.cambia(CostantiGioco.SCENA_NARRAZIONE, partita);
        } else {
            caricaProssimoNpc();
        }
    }
}