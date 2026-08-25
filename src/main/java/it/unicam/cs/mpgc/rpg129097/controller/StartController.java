package it.unicam.cs.mpgc.rpg129097.controller;

import it.unicam.cs.mpgc.rpg129097.model.*;
import it.unicam.cs.mpgc.rpg129097.utils.ParserProposizioni;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import  it.unicam.cs.mpgc.rpg129097.utils.CostantiGioco;
import java.util.List;

public class StartController {


    @FXML
    private TextField nomeField;
    @FXML
    private Button iniziaButton;


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

    public ArchivioProposizioni getArchivioProposizioni() {
        ParserProposizioni parser = new ParserProposizioni();
        ArchivioProposizioni archivio = parser.parse(CostantiGioco.ARCHIVIO);
        return archivio;
    }

    public Partita generaPartita(String nomePersonaggio, ArchivioProposizioni archivio) {
        GeneratorePartita generatore = new GeneratorePartita(archivio);
        return generatore.genera(nomePersonaggio, archivio);
    }

    public void cambiaScena(Partita partita) {
        SceneController.cambiaConTesto(CostantiGioco.SCENA_NARRAZIONE, partita,
                "Ciao " + partita.getPersonaggio().getNome() + " benvenuto nel mondo di GOM");
    }




}