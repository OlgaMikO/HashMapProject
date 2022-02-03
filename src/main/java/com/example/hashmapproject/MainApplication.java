package com.example.hashmapproject;

/**
 @author Mikhailova Olga
 @version 1.0
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/** Главный класс */
public class MainApplication extends Application
{
    /** Главный метод start */
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("project-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("HashMap");
        stage.setScene(scene);
        stage.show();
    }

    /** Главный метод main */
    public static void main(String[] args) {
        launch();
    }
}