package it.unicam.cs.mpgc.rpg129097.utils;


/**
 * Contiene tutte le costanti e i resources path del gioco.
 * <p>La classe è dichiarata final per evitare di istanziare oggetti
 * modificabil. I campi sono pubblici e statici per essere
 * accessibili dall'esterno e non dipendere dalle singole istanze.</p>
 * @author Elia Magini
 */
public final class CostantiGioco {
    public static final int NPC_PER_GIORNATA = 3;
    public static final int NUMERO_GIORNATE = 3;
    public static final int NPC_TOTALI = NPC_PER_GIORNATA * NUMERO_GIORNATE;
    public static final String NPC_JSON = "npc.json";
    public static final String ARCHIVIO = "archivio_esiti.json";
    public static final String SCENA_NARRAZIONE = "narrazione.fxml";
    public static final String SCENA_START = "start.fxml";
    public static final String SCENA_GAME = "game.fxml";
    public static final String SCENA_FINE = "fine.fxml";
}