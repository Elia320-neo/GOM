package it.unicam.cs.mpgc.rpg129097.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;

/**
 * Rappresenta un controller di utilità per la gestione e il cambio
 * delle scene dell'applicazione.
 * <p>La classe utilizza {@link FXMLLoader} per caricare le interfacce
 * definite nei file FXML e permette di passare dati ai relativi
 * controller attraverso metodi setter.</p>
 *
 * @author Elia Magini
 */
public class SceneController {
    @Setter
    private static Stage stage;

    /**
     * Cambia la scena corrente caricando il file FXML specificato.
     * <p>Se viene fornito un oggetto {@code data}, questo viene passato
     * al controller della nuova scena tramite un metodo {@code setPartita}
     * compatibile con il tipo dell'oggetto fornito.</p>
     * @param fxml nome del file FXML da caricare
     * @param data oggetto da passare al controller della nuova scena; può essere {@code null} se non è necessario passare dati
     */
    public static void cambia(String fxml, Object data) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/fxml/" + fxml));
            Parent root = loader.load();

            Object controller = loader.getController();
            if (data != null) {
                controller.getClass().getMethod("setPartita", data.getClass()).invoke(controller, data);
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Cambia la scena corrente caricando il file FXML specificato
     * e passando al controller sia i dati della partita sia un testo.
     * <p>Il controller della nuova scena deve esporre un metodo
     * {@code setPartita} compatibile con i parametri forniti.</p>
     * @param fxml nome del file FXML da caricare
     * @param data oggetto contenente i dati da passare al controller; può essere {@code null}
     * @param testo testo aggiuntivo da passare al controller della nuova scena
     */
    public static void cambiaConTesto(String fxml, Object data, String testo) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/fxml/" + fxml));
            Parent root = loader.load();

            Object controller = loader.getController();
            if (data != null) {
                controller.getClass().getMethod("setPartita", data.getClass(), String.class).invoke(controller, data, testo);
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}