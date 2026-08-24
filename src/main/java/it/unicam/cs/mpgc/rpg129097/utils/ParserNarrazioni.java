package it.unicam.cs.mpgc.rpg129097.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.unicam.cs.mpgc.rpg129097.model.ArchivioProposizioni;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;


/**
 * Rappresenta una classe di utilità per il parsing
 * e il caricamento dei testi della narrazione
 * da un file di risorsa JSON.
 * @author Elia Magini
 */
public class ParserNarrazioni {


    /**
     * Carica e analizza un file json contenente i testi della narrazione.
     *<p>La risorsa viene cercata nell classpath utilizzato il percorso passato come parametro.
     * Il contenuto del file viene poi convertito in una lista di oggetti {@link NarrazioneData},
     * tramite la libreria gson.</p>
     * @param resourcePath il percorso della risorsa JSON contenente i testi della narrazione
     * @return un'istanza di {@link NarrazioneData} contenente le proposizioni caricate
     * @throws RuntimeException se il file non viene trovato nel percorso specificato
     * o se si verifica un errore durante la lettura o deserializzazione del JSON
     */
    public List<NarrazioneData> parse(String resourcePath) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) throw new RuntimeException("Risorsa non trovata: " + resourcePath);

            Reader reader = new InputStreamReader(is);
            Gson gson = new Gson();

            Type type = new TypeToken<List<NarrazioneData>>(){}.getType();
            return gson.fromJson(reader, type);

        } catch (Exception e) {
            throw new RuntimeException("Errore nel caricamento delle narrazioni", e);
        }
    }
}