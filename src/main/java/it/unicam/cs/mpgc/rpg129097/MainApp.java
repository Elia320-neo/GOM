package it.unicam.cs.mpgc.rpg129097;



import it.unicam.cs.mpgc.rpg129097.controller.SceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principale dell'applicazione JavaFX.
 * <p>Si occupa di inizializzare l'applicazione, configurare la finestra
 * principale e caricare la schermata iniziale del gioco.</p>
 *
 * @author Elia Magini
 */
public class MainApp extends Application {

    /**
     * Avvia l'applicazione JavaFX.
     * <p>Imposta il {@link Stage} principale nel {@link SceneController},
     * carica il file FXML della schermata iniziale e configura le
     * dimensioni e le proprietà della finestra.</p>
     * @param stage il {@link Stage} principale dell'applicazione
     * @throws Exception se si verifica un errore durante il caricamento del file FXML
     */
    @Override
    public void start(Stage stage) throws Exception {
        SceneController.setStage(stage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/start.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setTitle("GOM");
        stage.setScene(scene);
        stage.setWidth(1100);
        stage.setHeight(800);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Metodo principale dell'applicazione.
     * <p>Avvia il framework JavaFX tramite il metodo launch().</p>
     * @param args argomenti passati da riga di comando
     */
    public static void main(String[] args) {
        launch();
    }
}
