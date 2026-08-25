package it.unicam.cs.mpgc.rpg129097;



import it.unicam.cs.mpgc.rpg129097.controller.SceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SceneController.setStage(stage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/start.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setTitle("GOM");
        stage.setScene(scene);
        stage.setWidth(900);
        stage.setHeight(600);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
