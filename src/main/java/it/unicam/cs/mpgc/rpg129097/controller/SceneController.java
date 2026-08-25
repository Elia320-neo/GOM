package it.unicam.cs.mpgc.rpg129097.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;

public class SceneController {
    @Setter
    private static Stage stage;

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