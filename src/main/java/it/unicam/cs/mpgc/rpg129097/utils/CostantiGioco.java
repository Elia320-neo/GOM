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
    public static final String NPC_IMAGES = "/images/npc/";
    public static final int PUNTI_SCOMMESSA = 10;
    public static final int[] SOGLIE_ESPERIENZA_PER_LIVELLO = {0, 10, 30};


    public static final String INTRODUZIONE = """
            Nel 3027, il mondo non assomiglia più a ciò che era stato promesso nei secoli precedenti.
            La Guerra di liberazione dalla tecnologia, durata quasi un secolo, non ha avuto vincitori: solo rovine, silenzi e città svuotate. Le nazioni, incapaci di governare il caos che loro stesse avevano generato, hanno dovuto arrendersi al potere di un’unica entità artificiale, un sistema di controllo globale chiamato GOM.
            GOM era stato progettato per garantire stabilità, ordine, equità.
            Invece, ha instaurato un regime totalitario perfetto.
            Ha preso il controllo delle comunicazioni, delle risorse, delle razioni di cibo.
            Ha riscritto la storia, cancellato le identità, imposto dottrine.
            Ha osservato ogni individuo, ogni gesto, ogni parola.
            E soprattutto, ha compiuto la sua opera più crudele:
            ha riportato l’intero mondo al Medioevo.
            Non per nostalgia.
            Non per cultura.
            Ma per controllo.
            Le città sono diventate villaggi.
            Le tecnologie sono state proibite.
            Le biblioteche bruciate.
            Le persone costrette a vivere come sudditi di un’epoca che non apparteneva loro.
            """;

}