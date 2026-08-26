package it.unicam.cs.mpgc.rpg129097.utils;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

import java.util.List;

/**
 * Gestisce la navigazione da tastiera tra un insieme di pulsanti.
 *<p>Le frecce sinistra e destra permettono di spostare il focus
 * tra i pulsanti attualmente visibili e abilitati. Il tasto Invio
 * permette invece di attivare il pulsante attualmente selezionato.</p>
 *
 * @author Elia Magini
 */
public class NavigazioneTastiera {

    /**
     * Costruttore privato per impedire l'istanziazione della classe
     * di utilità.
     */
    private NavigazioneTastiera() {
    }

    /**
     * Abilita la navigazione da tastiera tra i pulsanti specificati.
     * <p>I pulsanti disabilitati o invisibili vengono automaticamente
     * esclusi dalla navigazione.</p>
     * <ul>
     *     <li>Freccia sinistra: sposta il focus al pulsante precedente.</li>
     *     <li>Freccia destra: sposta il focus al pulsante successivo.</li>
     *     <li>Invio: attiva il pulsante attualmente selezionato.</li>
     * </ul>
     * @param root nodo principale della schermata
     * @param bottoni lista dei pulsanti da utilizzare per la navigazione
     */
    public static void abilita(Node root, List<Button> bottoni){
        root.sceneProperty().addListener((obs, oldScene, newScene) -> {

                    if (newScene == null) {return;}

                    newScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> gestisciEvento(event, bottoni));

                    impostaFocusIniziale(bottoni);
                }
        );
    }

    /**
     * Gestisce un evento da tastiera utilizzando i pulsanti disponibili.
     * @param event evento da tastiera ricevuto
     * @param bottoni lista dei pulsanti utilizzati per la navigazione
     */
    private static void gestisciEvento(KeyEvent event, List<Button> bottoni){
        List<Button> disponibili = filtraBottoni(bottoni);
        if (disponibili.isEmpty()) {return;}
        int indiceAttuale = indiceFocus(disponibili);

        switch (event.getCode()) {
            case LEFT -> {
                vaiAlPrecedente(disponibili, indiceAttuale);
                event.consume();
            }
            case RIGHT -> {
                vaiAlSuccessivo(disponibili, indiceAttuale);
                event.consume();
            }
            case ENTER -> {
                attivaPulsante(disponibili, indiceAttuale);
                event.consume();
            }
            default -> {
                // ignora gli altri tasti
            }
        }
    }

    /**
     * Restituisce i pulsanti attualmente disponibili per la navigazione.
     * <p>Un pulsante viene considerato disponibile se è visibile
     * e non è disabilitato.</p>
     * @param bottoni lista completa dei pulsanti
     * @return lista dei pulsanti visibili e abilitati
     */
    private static List<Button> filtraBottoni(List<Button> bottoni) {
        return bottoni.stream()
                .filter(button -> !button.isDisabled())
                .filter(Button::isVisible)
                .toList();
    }

    /**
     * Sposta il focus sul pulsante precedente rispetto a quello corrente.
     * <p>Se il primo pulsante è attualmente selezionato,
     * la navigazione ricomincia dall'ultimo pulsante disponibile.</p>
     * @param bottoni pulsanti disponibili
     * @param indiceAttuale indice del pulsante attualmente selezionato
     */
    private static void vaiAlPrecedente(List<Button> bottoni, int indiceAttuale) {
        int nuovoIndice;
        if (indiceAttuale <= 0) {
            nuovoIndice = bottoni.size() - 1;
        } else {
            nuovoIndice = indiceAttuale - 1;
        }
        bottoni.get(nuovoIndice).requestFocus();
    }

    /**
     * Sposta il focus sul pulsante successivo rispetto a quello corrente.
     * <p>Se l'ultimo pulsante è attualmente selezionato o nessun pulsante
     * possiede il focus, la navigazione ricomincia dal primo pulsante.</p>
     * @param bottoni pulsanti disponibili
     * @param indiceAttuale indice del pulsante attualmente selezionato
     */
    private static void vaiAlSuccessivo(List<Button> bottoni, int indiceAttuale) {
        int nuovoIndice;
        if (indiceAttuale < 0 || indiceAttuale == bottoni.size() - 1) {
            nuovoIndice = 0;
        } else {
            nuovoIndice = indiceAttuale + 1;
        }
        bottoni.get(nuovoIndice).requestFocus();
    }


    /**
     * Attiva il pulsante attualmente selezionato.
     *<p>Se nessun pulsante possiede il focus, viene attivato
     * il primo pulsante disponibile.</p>
     * @param bottoni pulsanti disponibili
     * @param indiceAttuale indice del pulsante attualmente selezionato
     */
    private static void attivaPulsante(List<Button> bottoni, int indiceAttuale) {
        int indiceDaAttivare;
        if (indiceAttuale >= 0) {
            indiceDaAttivare = indiceAttuale;
        } else {
            indiceDaAttivare = 0;
        }
        bottoni.get(indiceDaAttivare).fire();
    }

    /**
     * Imposta il focus iniziale sul primo pulsante disponibile.
     * @param bottoni lista dei pulsanti da controllare
     */
    private static void impostaFocusIniziale(List<Button> bottoni) {
        for (Button button : bottoni) {
            if (!button.isDisabled() && button.isVisible()) {
                button.requestFocus();
                break;
            }
        }
    }

    /**
     * Determina l'indice del pulsante attualmente in focus.
     * @param bottoni lista dei pulsanti disponibili
     * @return indice del pulsante con il focus oppure {@code -1} se nessun pulsante possiede il focus
     */
    private static int indiceFocus(List<Button> bottoni) {
        for (int i = 0; i < bottoni.size(); i++) {
            if (bottoni.get(i).isFocused()) {
                return i;
            }
        }
        return -1;
    }
}