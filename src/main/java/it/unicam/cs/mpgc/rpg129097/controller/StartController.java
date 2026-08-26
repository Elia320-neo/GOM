package it.unicam.cs.mpgc.rpg129097.controller;

import it.unicam.cs.mpgc.rpg129097.model.*;
import it.unicam.cs.mpgc.rpg129097.utils.NavigazioneTastiera;
import it.unicam.cs.mpgc.rpg129097.utils.ParserProposizioni;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import  it.unicam.cs.mpgc.rpg129097.utils.CostantiGioco;
import java.util.List;



/**
 * Controller della schermata iniziale del gioco.
 * <p>Gestisce l'inserimento del nome del personaggio da parte dell'utente,
 * la generazione di una nuova partita e il passaggio alla schermata
 * di narrazione.</p>
 *
 * @author Elia Magini
 */
public class StartController {

    @FXML
    private TextField nomeField;
    @FXML
    private Button iniziaButton;

    @FXML
    private Node root;

    /**
     * Inizializza la navigazione da tastiera della schermata.
     * <p>Abilita la navigazione tramite tastiera sul pulsante di avvio
     * della partita.</p>
     */
    public void initialize() {
        NavigazioneTastiera.abilita(root, List.of(iniziaButton));
    }

    /**
     * Gestisce l'evento di avvio della partita.
     * <p>Recupera il nome inserito dall'utente, rimuove gli spazi
     * iniziali e finali e verifica che non sia vuoto. Se il nome
     * è valido, genera una nuova partita e cambia la scena.</p>
     */
    @FXML
    private void onInizia() {
        String nomePersonaggio = nomeField.getText().trim();
        if (nomePersonaggio.isEmpty()) {
            nomeField.setPromptText("Inserisci un nome valido!");
            return;
        }
        Partita partita = generaPartita(nomePersonaggio, getArchivioProposizioni());
        cambiaScena(partita);
    }


    /**
     * Carica l'archivio delle proposizioni di gioco.
     * <p>Utilizza un {@link ParserProposizioni} per effettuare il parsing
     * del file contenente le proposizioni, il cui percorso è definito
     * in {@link CostantiGioco#ARCHIVIO}.</p>
     * @return l'archivio contenente le proposizioni del gioco
     */
    public ArchivioProposizioni getArchivioProposizioni() {
        ParserProposizioni parser = new ParserProposizioni();
        ArchivioProposizioni archivio = parser.parse(CostantiGioco.ARCHIVIO);
        return archivio;
    }


    /**
     * Genera una nuova partita utilizzando il nome del personaggio
     * e l'archivio delle proposizioni fornito.
     * @param nomePersonaggio il nome scelto dal giocatore
     * @param archivio l'archivio contenente le proposizioni del gioco
     * @return la nuova {@link Partita} generata
     */
    public Partita generaPartita(String nomePersonaggio, ArchivioProposizioni archivio) {
        GeneratorePartita generatore = new GeneratorePartita(archivio);
        return generatore.genera(nomePersonaggio, archivio);
    }



    /**
     * Cambia la scena corrente passando alla schermata di narrazione
     * della partita.
     * <p>Visualizza inoltre un messaggio di benvenuto contenente
     * il nome del personaggio appena creato.</p>
     * @param partita la partita da passare alla nuova scena
     */
    public void cambiaScena(Partita partita) {
        SceneController.cambiaConTesto(CostantiGioco.SCENA_NARRAZIONE, partita,
                "Ciao " + partita.getPersonaggio().getNome() + " benvenuto nel mondo di GOM");
    }




}