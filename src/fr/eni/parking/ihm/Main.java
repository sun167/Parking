package fr.eni.parking.ihm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Gestion Parking");
        primaryStage.setScene(new Scene(root, 641.0, 444));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
