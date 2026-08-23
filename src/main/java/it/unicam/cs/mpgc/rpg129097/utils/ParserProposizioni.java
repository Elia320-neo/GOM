package it.unicam.cs.mpgc.rpg129097.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.unicam.cs.mpgc.rpg129097.model.ArchivioProposizioni;
import it.unicam.cs.mpgc.rpg129097.interfaces.Proposizione;
import it.unicam.cs.mpgc.rpg129097.model.Informazione;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class ParserProposizioni {

    public ArchivioProposizioni parse(String resourcePath) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {throw new RuntimeException("Risorsa non trovata: " + resourcePath);}

            Reader reader = new InputStreamReader(is);
            Gson gson = new Gson();

            Type type = new TypeToken<List<Informazione>>(){}.getType();
            Collection<Proposizione> props = gson.fromJson(reader, type);

            return new ArchivioProposizioni(props);

        } catch (Exception e) {
            throw new RuntimeException("Errore nel caricamento dell'archivio degli esiti", e);
        }
    }
}