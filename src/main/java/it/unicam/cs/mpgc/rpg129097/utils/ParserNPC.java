package it.unicam.cs.mpgc.rpg129097.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Rappresenta una classe di utilità per il parsing
 * e il caricamento dei dati degli NPC da un file di risorsa JSON.
 * @author Elia Magini
 */
public class ParserNPC {

    /**
     * Carica e analizza un file JSON contenente i dati degli NPC.
     * <p>La risorsa viene cercata nel classpath utilizzando il percorso
     * specificato. Il contenuto del file viene successivamente convertito
     * in una lista di oggetti {@link NPCData} tramite Gson.</p>
     * @param resourcePath il percorso della risorsa JSON contenente i dati degli NPC
     * @return una lista di oggetti {@link NPCData} ottenuta dal parsing del file JSON
     * @throws RuntimeException se la risorsa non viene trovata oppure si verifica un errore durante
     * il parsing del file
     */
    public List<NPCData> parse(String resourcePath) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) throw new RuntimeException("Risorsa non trovata: " + resourcePath);

            Reader reader = new InputStreamReader(is);
            Gson gson = new Gson();

            Type type = new TypeToken<List<NPCData>>(){}.getType();
            return gson.fromJson(reader, type);

        } catch (Exception e) {
            throw new RuntimeException("Errore nel caricamento degli NPC", e);
        }
    }
}