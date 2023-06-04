package com.example.dresscasualrents;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.IOException;

public class DressAppRents extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(DressAppRents.class.getResource("dress-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 780, 550);


        stage.setTitle("Dress App");

        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}